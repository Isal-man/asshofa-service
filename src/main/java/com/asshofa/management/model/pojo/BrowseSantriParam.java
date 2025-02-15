package com.asshofa.management.model.pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BrowseSantriParam {
    private String nama;
    private String namaWali;
    private String tempatLahir;
    private String tanggalLahir;
    private String jenisKelamin;
    private Integer page;
    private Integer limit;
}
