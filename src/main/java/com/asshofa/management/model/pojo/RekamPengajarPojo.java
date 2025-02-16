package com.asshofa.management.model.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class RekamPengajarPojo {
    @NotBlank(message = "Nama lengkap tidak boleh kosong!")
    private String namaLengkap;
    private String noTelepon;
    private String alamat;
    @NotBlank(message = "Spesialisasi tidak boleh kosong")
    private String spesialisasi;
    private String gambar;
}
