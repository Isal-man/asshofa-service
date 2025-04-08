package com.asshofa.management.model.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class DetailSantriPojo {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String id;
    private String namaLengkap;
    private String tempatLahir;
    private LocalDate tanggalLahir;
    private String jenisKelamin;
    private String alamat;
    private String gambar;
    private String idWali;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String namaWali;
    private String gambarWali;
}
