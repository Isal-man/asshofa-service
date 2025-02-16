package com.asshofa.management.model.pojo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Builder
@Getter
@Setter
public class BrowseJadwalPengajaranPojo {
    private String id;
    private String hari;
    private LocalTime jamMulai;
    private LocalTime jamSelesai;
    private String mataPelajaran;
    private String idPengajar;
    private String namaPengajar;
}
