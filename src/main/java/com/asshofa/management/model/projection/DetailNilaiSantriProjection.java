package com.asshofa.management.model.projection;

import java.time.LocalDate;

public interface DetailNilaiSantriProjection {
    Short getId();
    String getKeterangan();
    Integer getNilai();
    LocalDate getTanggalPenilaian();
    Short getIdSantri();
    String getNamaSantri();
    Short getIdJadwal();
    String getMataPelajaran();
    Short getIdPengajar();
    String getNamaPengajar();
}
