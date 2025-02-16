package com.asshofa.management.model.pojo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class DetailPengajarPojo {
    private String id;
    private String namaLengkap;
    private String noTelepon;
    private String alamat;
    private String spesialisasi;
    private List<DetailJadwalPengajaranPojo> jadwalPengajaranList;
}
