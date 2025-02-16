package com.asshofa.management.model.projection;

import java.time.LocalTime;

public interface DetailJadwalPengajaranProjection {
    Short getId();
    String getMataPelajaran();
    String getHari();
    LocalTime getJamMulai();
    LocalTime getJamSelesai();
    Short getIdPengajar();
    String getNamaPengajar();
}
