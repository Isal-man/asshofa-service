package com.asshofa.management.model.pojo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BrowsePengajarPojo {
    private String id;
    private String namaLengkap;
    private String noTelepon;
    private String spesialisasi;
}