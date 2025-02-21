package com.asshofa.management.model.projection;

import java.time.LocalDateTime;

public interface SantriTerbaruProjection {
    Short getId();
    String getNamaLengkap();
    LocalDateTime getCreatedAt();
}
