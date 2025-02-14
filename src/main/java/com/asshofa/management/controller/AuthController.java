package com.asshofa.management.controller;

import com.asshofa.management.model.entity.Users;
import com.asshofa.management.model.pojo.LoginUserPojo;
import com.asshofa.management.model.pojo.RegisterUserPojo;
import com.asshofa.management.model.response.DataResponse;
import com.asshofa.management.model.response.JwtResponse;
import com.asshofa.management.service.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Tag(name = "Authentication Service", description = "API Collections for Authentication")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginUserPojo loginUserPojo) {
        JwtResponse response = authService.authenticateUser(loginUserPojo);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<DataResponse<Users>> register(@RequestBody RegisterUserPojo registerUserPojo) {
        DataResponse<Users> response = authService.registerUser(registerUserPojo);
        return ResponseEntity.ok(response);
    }

}
