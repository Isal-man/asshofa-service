package com.asshofa.management.model.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class DetailWaliSantriPojo {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String id;
    private String namaLengkap;
    private String noTelepon;
    private String alamat;
    private String hubunganDenganSantri;
    private List<DetailSantriPojo> santriList;
}
