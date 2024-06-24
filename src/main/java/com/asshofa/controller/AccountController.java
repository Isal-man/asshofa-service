package com.asshofa.controller;

import com.asshofa.model.pojo.AccountPojo;
import com.asshofa.model.response.DataResponse;
import com.asshofa.model.response.DefaultResponse;
import com.asshofa.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/account")
@CrossOrigin(origins = "*")
@Tag(name = "Account Service", description = "API Collections for Account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/get-account")
    @Operation(
            summary = "Get Account",
            description = "Fetch account from data source"
    )
    public ResponseEntity<DataResponse<AccountPojo>> getAccount(@RequestParam("username") String username) {
        DataResponse<AccountPojo> response = accountService.getAccount(username);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update")
    @Operation(
            summary = "Update Account",
            description = "Update account and save to data source"
    )
    public ResponseEntity<DataResponse<AccountPojo>> updateAccount(@Valid @RequestBody AccountPojo account) {
        DataResponse<AccountPojo> response = accountService.update(account);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete")
    @Operation(
            summary = "Delete Account",
            description = "Delete account from data source"
    )
    public ResponseEntity<DefaultResponse> deleteAccount(@RequestParam("username") String username) {
        DefaultResponse response = accountService.delete(username);
        return ResponseEntity.ok(response);
    }

}
