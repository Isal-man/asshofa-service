package com.asshofa.management.model.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class RekamWaliSantriPojo {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String id;
    @NotBlank(message = "Nama lengkap tidak boleh kosong.")
    private String namaLengkap;
    private String noTelepon;
    private String alamat;
    @NotBlank(message = "Hubungan dengan santri tidak boleh kosong.")
    private String hubunganDenganSantri;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime createdAt;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime updatedAt;
}
