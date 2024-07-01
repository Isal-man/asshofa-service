package com.asshofa.model.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class JumlahSantriPojo {

    private Integer totalSantri;
    private List<JumlahSantriByTahunPojo> data;

}
