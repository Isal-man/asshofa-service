package com.asshofa.management.model.pojo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@Builder
public class DetailJadwalPengajaranPojo {
    private String id;
    private String hari;
    private LocalTime jamMulai;
    private LocalTime jamSelesai;
    private String mataPelajaran;
    private String idPengajar;
    private String namaPengajar;
    private String gambarPengajar;
    private List<DetailNilaiSantriPojo> nilaiSantriList;
}
