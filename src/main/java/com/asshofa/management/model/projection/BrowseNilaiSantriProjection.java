package com.asshofa.management.model.projection;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface BrowseNilaiSantriProjection {
    Short getId();
    String getKeterangan();
    Integer getNilai();
    LocalDate getTanggalPenilaian();
    Short getIdSantri();
    String getNamaSantri();
    LocalDateTime getCreatedAt();
}
