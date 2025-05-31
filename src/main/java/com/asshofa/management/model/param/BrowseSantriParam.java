package com.asshofa.management.model.param;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BrowseSantriParam {
    private String nama;
    private String namaWali;
    private String tempatLahir;
    private Date tanggalLahir;
    private String jenisKelamin;
    private String idJadwalPengajaran;
    private Integer page;
    private Integer limit;
}
