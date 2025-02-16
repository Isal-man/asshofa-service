package com.asshofa.management.model.param;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class BrowseNilaiSantriParam {
    private LocalDate tanggalPenilaian;
    private String namaSantri;
    private Integer page;
    private Integer limit;
}
