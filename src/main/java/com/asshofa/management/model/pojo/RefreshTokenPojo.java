package com.asshofa.management.model.pojo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
public class RefreshTokenPojo {

    @NotBlank(message = "Refresh token tidak boleh kosong")
    private String refreshToken;

}
