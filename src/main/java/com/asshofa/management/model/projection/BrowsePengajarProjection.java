package com.asshofa.management.model.projection;

import java.time.LocalDateTime;

public interface BrowsePengajarProjection {
    Short getId();
    String getNamaLengkap();
    String getNoTelepon();
    String getSpesialisasi();
    String getGambar();
    LocalDateTime getCreatedAt();
}
