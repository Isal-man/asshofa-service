package com.asshofa.management.model.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class RekamSantriPojo {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String id;
    private String namaLengkap;
    private String tempatLahir;
    private LocalDate tanggalLahir;
    private String jenisKelamin;
    private String alamat;
    private String idWali;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime createdAt;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime updatedAt;
}
