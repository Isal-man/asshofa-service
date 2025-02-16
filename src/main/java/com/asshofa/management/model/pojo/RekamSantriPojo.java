package com.asshofa.management.model.pojo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@Builder
public class RekamSantriPojo {
    @NotBlank(message = "Nama lengkap tidak boleh kosong!")
    private String namaLengkap;
    @NotBlank(message = "Tempat lahir tidak boleh kosong!")
    private String tempatLahir;
    @NotNull(message = "Tanggal lahir tidak boleh kosong!")
    private LocalDate tanggalLahir;
    @NotBlank(message = "Jenis kelamin tidak boleh kosong!")
    private String jenisKelamin;
    private String alamat;
    @NotBlank(message = "Wali santri tidak boleh kosong!")
    private String idWali;
    private String gambar;
}
