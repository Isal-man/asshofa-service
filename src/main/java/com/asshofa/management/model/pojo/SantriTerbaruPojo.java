package com.asshofa.management.model.pojo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class SantriTerbaruPojo {
    private String id;
    private String namaLengkap;
    private LocalDateTime createdAt;
}
