package com.asshofa.management.model.pojo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
public class RegisterUserPojo {

    @NotBlank(message = "Username tidak boleh kosong")
    @Size(min = 4, max = 20, message = "Username minimal terdiri dari 4 karakter dan maksimal 20 karakter")
    private String username;

    @NotBlank(message = "Password tidak boleh kosong")
    private String password;

    @NotBlank(message = "Role tidak boleh kosong")
    private String role;

    private String gambar;
}
