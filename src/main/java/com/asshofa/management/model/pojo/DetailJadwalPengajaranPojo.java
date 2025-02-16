package com.asshofa.management.model.pojo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

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
}
