package com.asshofa.management.model.pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BrowsePengajarParam {
    private String namaLengkap;
    private String noTelepon;
    private String spesialisasi;
    private Integer page;
    private Integer limit;
}
