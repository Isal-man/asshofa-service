package com.asshofa.management.model.param;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class BrowseJadwalPengajaranParam {
    private String hari;
    private String mataPelajaran;
    private String namaPengajar;
    private Integer page;
    private Integer limit;
}
