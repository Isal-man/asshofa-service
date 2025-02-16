package com.asshofa.management.model.pojo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
public class RekamWaliSantriPojo {
    @NotBlank(message = "Nama lengkap tidak boleh kosong.")
    private String namaLengkap;
    private String noTelepon;
    private String alamat;
    @NotBlank(message = "Hubungan dengan santri tidak boleh kosong.")
    private String hubunganDenganSantri;
    private String gambar;
}
