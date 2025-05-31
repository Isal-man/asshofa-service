package com.asshofa.management.model.pojo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class DetailNilaiSantriPojo {
    private String id;
    private String keterangan;
    private Integer nilai;
    private LocalDate tanggalPenilaian;
    private String idSantri;
    private String namaSantri;
}
