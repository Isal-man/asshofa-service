package com.asshofa.management.model.pojo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@Builder
public class DashboardPojo {
    private Integer totalSantri;
    private Integer totalPengajar;
    private Integer totalWaliSantri;
    private Integer totalMataPelajaran;
    private JumlahSantriPojo jumlahSantriPojo;
    private List<Map<String, Object>> jumlahSantriPerKelas;
    private List<Map<String, Object>> nilaiAveragePerKelas;
    private List<Map<String, Object>> distribusiNilaiSantri;
    private List<TrenPendaftaranPojo> trenPendaftaranPojos;
    private List<SantriTerbaruPojo> santriTerbaruList;
}
