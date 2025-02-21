package com.asshofa.management.model.pojo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TrenPendaftaranPojo {
    private String bulan;
    private Integer jumlah;
}
