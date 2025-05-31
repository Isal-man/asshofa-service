package com.asshofa.management.service.impl;

import com.asshofa.management.exception.custom.NotFoundException;
import com.asshofa.management.model.entity.JadwalPengajaran;
import com.asshofa.management.model.entity.NilaiSantri;
import com.asshofa.management.model.entity.Santri;
import com.asshofa.management.model.pojo.DetailNilaiSantriPojo;
import com.asshofa.management.model.pojo.RekamNilaiSantriPojo;
import com.asshofa.management.model.projection.DetailNilaiSantriProjection;
import com.asshofa.management.model.response.*;
import com.asshofa.management.repository.JadwalPengajaranRepository;
import com.asshofa.management.repository.NilaiSantriRepository;
import com.asshofa.management.repository.SantriRepository;
import com.asshofa.management.service.NilaiSantriService;
import com.asshofa.management.util.CheckRole;
import com.asshofa.management.util.Constant;
import com.asshofa.management.util.EncryptionUtil;
import com.asshofa.management.util.interceptor.HeaderHolder;
import com.asshofa.management.util.interceptor.LoggingHolder;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NilaiSantriServiceImpl implements NilaiSantriService {

    private final NilaiSantriRepository nilaiSantriRepository;
    private final SantriRepository santriRepository;
    private final JadwalPengajaranRepository jadwalPengajaranRepository;
    private final LoggingHolder loggingHolder;
    private final HeaderHolder headerHolder;

    private static final Logger logger = LogManager.getLogger(NilaiSantriServiceImpl.class);

    @Override
    public DataResponse<DetailNilaiSantriPojo> create(RekamNilaiSantriPojo rekam) {
        try {
            new CheckRole(headerHolder).checkRoleAdminAndPengajar();

            Santri santri = getSantri(rekam);

            JadwalPengajaran jadwalPengajaran = getJadwalPengajaran(rekam);

            NilaiSantri nilaiSantri = new NilaiSantri();
            nilaiSantri.setCreatedAt(new Timestamp(System.currentTimeMillis()).toLocalDateTime());
            nilaiSantri.setSantri(santri);
            nilaiSantri.setJadwalPengajaran(jadwalPengajaran);
            
            NilaiSantri newNilaiSantri = nilaiSantriRepository.save(toEntity(rekam, nilaiSantri));

            List<DetailNilaiSantriProjection> data = nilaiSantriRepository.getDetailNilaiSantri(newNilaiSantri.getId(), null);
            
            return new DataResponse<>(Constant.VAR_SUCCESS, ResponseMessage.DATA_CREATED, toPojoDetailNilaiSantri(data), loggingHolder);
        } catch (Exception e) {
            logger.error("error when create nilai santri", e);
            throw e;
        }
    }

    @Override
    public DataResponse<DetailNilaiSantriPojo> update(RekamNilaiSantriPojo rekam, String id) {
        try {
            new CheckRole(headerHolder).checkRoleAdminAndPengajar();

            Santri santri = getSantri(rekam);

            JadwalPengajaran jadwalPengajaran = getJadwalPengajaran(rekam);

            Optional<NilaiSantri> nilaiSantri = nilaiSantriRepository.findById(EncryptionUtil.decrypt(id));
            if (!nilaiSantri.isPresent()) throw new NotFoundException(ResponseMessage.DATA_NOT_FOUND);
            
            nilaiSantri.get().setUpdatedAt(new Timestamp(System.currentTimeMillis()).toLocalDateTime());
            nilaiSantri.get().setSantri(santri);
            nilaiSantri.get().setJadwalPengajaran(jadwalPengajaran);

            NilaiSantri newNilaiSantri = nilaiSantriRepository.save(toEntity(rekam, nilaiSantri.get()));

            List<DetailNilaiSantriProjection> data = nilaiSantriRepository.getDetailNilaiSantri(newNilaiSantri.getId(), null);

            return new DataResponse<>(Constant.VAR_SUCCESS, ResponseMessage.DATA_CREATED, toPojoDetailNilaiSantri(data), loggingHolder);
        } catch (Exception e) {
            logger.error("error when update nilai santri", e);
            throw e;
        }
    }

    private DetailNilaiSantriPojo toPojoDetailNilaiSantri(List<DetailNilaiSantriProjection> data) {
        return DetailNilaiSantriPojo.builder()
                .namaSantri(data.get(0).getNamaSantri())
                .id(EncryptionUtil.encrypt(data.get(0).getId()))
                .idSantri(EncryptionUtil.encrypt(data.get(0).getIdSantri()))
                .keterangan(data.get(0).getKeterangan())
                .nilai(data.get(0).getNilai())
                .tanggalPenilaian(data.get(0).getTanggalPenilaian())
                .build();
    }

    private NilaiSantri toEntity(RekamNilaiSantriPojo source, NilaiSantri destinantion) {
        destinantion.setNilai(source.getNilai());
        destinantion.setKeterangan(source.getKeterangan());
        destinantion.setTanggalPenilaian(source.getTanggalPenilaian());
        
        return destinantion;
    }

    private Santri getSantri(RekamNilaiSantriPojo rekam) {
        Optional<Santri> santri = santriRepository.findById(EncryptionUtil.decrypt(rekam.getIdSantri()));
        if (!santri.isPresent()) throw new NotFoundException(ResponseMessage.DATA_NOT_FOUND);
        return santri.get();
    }

    private JadwalPengajaran getJadwalPengajaran(RekamNilaiSantriPojo rekam) {
        Optional<JadwalPengajaran> jadwalPengajaran = jadwalPengajaranRepository.findById(EncryptionUtil.decrypt(rekam.getIdJadwal()));
        if (!jadwalPengajaran.isPresent()) throw new NotFoundException(ResponseMessage.DATA_NOT_FOUND);
        return jadwalPengajaran.get();
    }

}
