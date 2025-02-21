package com.asshofa.management.service.impl;

import com.asshofa.management.model.pojo.DashboardPojo;
import com.asshofa.management.model.pojo.JumlahSantriPojo;
import com.asshofa.management.model.pojo.SantriTerbaruPojo;
import com.asshofa.management.model.pojo.TrenPendaftaranPojo;
import com.asshofa.management.model.projection.SantriTerbaruProjection;
import com.asshofa.management.model.projection.DistribusiNilaiProjection;
import com.asshofa.management.model.projection.PerKelasProjection;
import com.asshofa.management.model.projection.TrenPendaftaranProjection;
import com.asshofa.management.model.response.DataResponse;
import com.asshofa.management.model.response.ResponseMessage;
import com.asshofa.management.repository.*;
import com.asshofa.management.service.DashboardService;
import com.asshofa.management.util.CheckRole;
import com.asshofa.management.util.Constant;
import com.asshofa.management.util.EncryptionUtil;
import com.asshofa.management.util.interceptor.HeaderHolder;
import com.asshofa.management.util.interceptor.LoggingHolder;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final SantriRepository santriRepository;
    private final PengajarRepository pengajarRepository;
    private final WaliSantriRepository waliSantriRepository;
    private final JadwalPengajaranRepository jadwalPengajaranRepository;
    private final NilaiSantriRepository nilaiSantriRepository;
    private final LoggingHolder loggingHolder;
    private final HeaderHolder headerHolder;

    private static final Logger logger = LogManager.getLogger(DashboardServiceImpl.class);

    @Override
    public DataResponse<DashboardPojo> getDashboard() {
        try {
            new CheckRole(headerHolder).checkRoleAdminAndPengajar();

            DashboardPojo dashboardPojo = DashboardPojo.builder()
                    .distribusiNilaiSantri(toDistribusiNilaiSantri())
                    .jumlahSantriPerKelas(toJumlahSantriPerkelas())
                    .jumlahSantriPojo(toJumlahSantriPojo())
                    .nilaiAveragePerKelas(toNilaiAveragePerKelas())
                    .santriTerbaruList(toSantriTerbaru())
                    .totalMataPelajaran((int) jadwalPengajaranRepository.count())
                    .totalPengajar((int) pengajarRepository.count())
                    .totalSantri((int) santriRepository.count())
                    .totalWaliSantri((int) waliSantriRepository.count())
                    .trenPendaftaranPojos(toTrenPendaftaran())
                    .build();
            return new DataResponse<>(Constant.VAR_SUCCESS, ResponseMessage.DATA_FETCHED, dashboardPojo, loggingHolder);
        } catch (Exception e) {
            logger.error("error when get dashboard", e);
            throw e;
        }
    }

    private List<TrenPendaftaranPojo> toTrenPendaftaran() {
        List<TrenPendaftaranProjection> list = santriRepository.getTrenPendaftaran();
        return list.stream().map(this::toTrenPendaftaranPojo).collect(Collectors.toList());
    }

    private TrenPendaftaranPojo toTrenPendaftaranPojo(TrenPendaftaranProjection data) {
        return TrenPendaftaranPojo.builder()
                .bulan(data.getBulan())
                .jumlah(data.getJumlah())
                .build();
    }

    private List<SantriTerbaruPojo> toSantriTerbaru() {
        List<SantriTerbaruProjection> list = santriRepository.getSantriTerbaru();
        return list.stream().map(this::toSantriTerbaruPojo).collect(Collectors.toList());
    }

    private SantriTerbaruPojo toSantriTerbaruPojo(SantriTerbaruProjection data) {
        return SantriTerbaruPojo.builder()
                .id(EncryptionUtil.encrypt(data.getId()))
                .createdAt(data.getCreatedAt())
                .namaLengkap(data.getNamaLengkap())
                .build();
    }

    private List<Map<String, Object>> toNilaiAveragePerKelas() {
        List<PerKelasProjection> nilaiAverage = nilaiSantriRepository.getNilaiAveragePerKelas();

        return nilaiAverage.stream().map(kelas -> {
            Map<String, Object> map = new HashMap<>();
            map.put(kelas.getNamaKelas(), kelas.getJumlah());
            return map;
        }).collect(Collectors.toList());
    }

    private JumlahSantriPojo toJumlahSantriPojo() {
        Integer lakiLaki = santriRepository.countByJenisKelaminIgnoreCase("laki-laki");
        Integer perempuan = santriRepository.countByJenisKelaminIgnoreCase("perempuan");

        return JumlahSantriPojo.builder()
                .lakiLaki(lakiLaki)
                .perempuan(perempuan)
                .build();
    }

    private List<Map<String, Object>> toJumlahSantriPerkelas() {
        List<PerKelasProjection> perKelas = nilaiSantriRepository.getJumlahSantriPerKelas();

        return perKelas.stream().map(kelas -> {
            Map<String, Object> map = new HashMap<>();
            map.put(kelas.getNamaKelas(), kelas.getJumlah());
            return map;
        }).collect(Collectors.toList());
    }

    private List<Map<String, Object>> toDistribusiNilaiSantri() {
        List<DistribusiNilaiProjection> distribusiNilai = nilaiSantriRepository.getDistribusiNilai();

        return distribusiNilai.stream()
                .map(distribusi -> {
                    Map<String, Object> distribusiNilaiMap = new HashMap<>();
                    distribusiNilaiMap.put(distribusi.getRentangNilai(), distribusi.getJumlahSantri());
                    return distribusiNilaiMap;
                })
                .collect(Collectors.toList());
    }

}
