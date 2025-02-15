package com.asshofa.management.model.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BrowseWaliSantriPojo {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String id;
    private String namaLengkap;
    private String noTelepon;
    private String hubunganDenganSantri;
}
