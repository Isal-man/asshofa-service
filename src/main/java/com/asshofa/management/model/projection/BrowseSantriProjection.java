package com.asshofa.management.model.projection;

import java.time.LocalDate;

public interface BrowseSantriProjection {
    Short getId();
    String getNamaLengkap();
    String getTempatLahir();
    LocalDate getTanggalLahir();
    String getJenisKelamin();
    LocalDate getCreatedAt();
    Short getIdWali();
    String getNamaWali();
}
