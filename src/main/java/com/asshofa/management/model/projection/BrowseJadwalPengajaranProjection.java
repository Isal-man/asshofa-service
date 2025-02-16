package com.asshofa.management.model.projection;

import java.time.LocalDateTime;
import java.time.LocalTime;

public interface BrowseJadwalPengajaranProjection {
    Short getId();
    String getHari();
    LocalTime getJamMulai();
    LocalTime getJamSelesai();
    String getMataPelajaran();
    Short getIdPengajar();
    String getNamaPengajar();
    LocalDateTime getCreatedAt();
}
