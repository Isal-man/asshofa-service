package com.asshofa.model.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
public class LoginPojo {
    @NotBlank(message = "username is required")
    @Size(max = 15, message = "username max length is 15")
    private String username;
    @NotBlank(message = "Password cannot be empty")
    @Size(min = 5, max = 15, message = "Password must have at least 5 characters and a maximum of 15 characters")
    private String password;
}
