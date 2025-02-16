package com.asshofa.management.model.pojo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class RekamSantriPojo {
    private String namaLengkap;
    private String tempatLahir;
    private LocalDate tanggalLahir;
    private String jenisKelamin;
    private String alamat;
    private String idWali;
}
