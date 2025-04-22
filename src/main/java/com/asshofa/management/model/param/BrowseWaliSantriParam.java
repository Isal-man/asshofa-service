package com.asshofa.management.model.param;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BrowseWaliSantriParam {
    private String namaLengkap;
    private String noTelepon;
    private String hubunganDenganSantri;
    private Integer page;
    private Integer limit;
}
