package com.asshofa.controller;

import com.asshofa.model.pojo.AccountPojo;
import com.asshofa.model.pojo.LoginPojo;
import com.asshofa.model.response.DataResponse;
import com.asshofa.model.response.LoginResponse;
import com.asshofa.service.AuthenticationService;
import com.asshofa.service.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
@Tag(name = "Authentication Service", description = "API Collections for Authentication")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final JwtService jwtService;

    public AuthenticationController(AuthenticationService authenticationService, JwtService jwtService) {
        this.authenticationService = authenticationService;
        this.jwtService = jwtService;
    }

    @PostMapping("sign-up")
    @Operation(
            summary = "User Sign Up",
            description = "This is for user sign up and save data to data source"
    )
    public ResponseEntity<DataResponse<AccountPojo>> createAccount(@Valid @RequestBody AccountPojo account) {
        DataResponse<AccountPojo> response = authenticationService.signUp(account);
        return ResponseEntity.ok(response);
    }

    @PostMapping("sign-in")
    @Operation(
            summary = "User Sign In",
            description = "This is for user login"
    )
    public ResponseEntity<LoginResponse> userLogin(@Valid  @RequestBody LoginPojo account) {
        AccountPojo data = authenticationService.authenticate(account);
        String token = jwtService.generateToken(data);
        LoginResponse response = new LoginResponse();
        response.setToken(token);
        response.setExpired(jwtService.getExpirationTime());
        return ResponseEntity.ok(response);
    }

}
