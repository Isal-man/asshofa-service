package com.asshofa.management.service.impl;

import com.asshofa.management.exception.custom.NotFoundException;
import com.asshofa.management.model.entity.JadwalPengajaran;
import com.asshofa.management.model.entity.NilaiSantri;
import com.asshofa.management.model.entity.Santri;
import com.asshofa.management.model.param.BrowseNilaiSantriParam;
import com.asshofa.management.model.pojo.BrowseNilaiSantriPojo;
import com.asshofa.management.model.pojo.DetailNilaiSantriPojo;
import com.asshofa.management.model.pojo.RekamNilaiSantriPojo;
import com.asshofa.management.model.projection.BrowseNilaiSantriProjection;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public DatatableResponse<BrowseNilaiSantriPojo> browseNilaiSantri(BrowseNilaiSantriParam param) {
        try {
            new CheckRole(headerHolder).checkRoles();

            int page = param.getPage() - 1;
            int limit = param.getLimit();
            Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");

            Pageable pageable = PageRequest.of(page, limit, sort);

            Page<BrowseNilaiSantriProjection> result = nilaiSantriRepository.browseNilaiSantri(param, pageable);

            return toDatatableNilaiSantri(result, page);
        } catch (Exception e) {
            logger.error("error when browse nilai santri", e);
            throw e;
        }
    }

    @Override
    public DataResponse<DetailNilaiSantriPojo> getDetailNilaiSantri(String id) {
        try {
            new CheckRole(headerHolder).checkRoleDetailJadwalPengajaran();

            Optional<NilaiSantri> nilaiSantri = nilaiSantriRepository.findById(EncryptionUtil.decrypt(id));
            if (!nilaiSantri.isPresent()) throw new NotFoundException(ResponseMessage.DATA_NOT_FOUND);

            DetailNilaiSantriProjection data = nilaiSantriRepository.getDetailNilaiSantri(nilaiSantri.get().getId());
            
            return new DataResponse<>(Constant.VAR_SUCCESS, ResponseMessage.DATA_FETCHED, toPojoDetailNilaiSantri(data), loggingHolder);
        } catch (Exception e) {
            logger.error("error when get detail nilai santri", e);
            throw e;
        }
    }

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

            DetailNilaiSantriProjection data = nilaiSantriRepository.getDetailNilaiSantri(newNilaiSantri.getId());
            
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

            DetailNilaiSantriProjection data = nilaiSantriRepository.getDetailNilaiSantri(newNilaiSantri.getId());

            return new DataResponse<>(Constant.VAR_SUCCESS, ResponseMessage.DATA_CREATED, toPojoDetailNilaiSantri(data), loggingHolder);
        } catch (Exception e) {
            logger.error("error when update nilai santri", e);
            throw e;
        }
    }

    @Override
    public DefaultResponse delete(String id) {
        try {
            new CheckRole(headerHolder).checkRoleAdminAndPengajar();

            Optional<NilaiSantri> nilaiSantri = nilaiSantriRepository.findById(EncryptionUtil.decrypt(id));
            if (!nilaiSantri.isPresent()) throw new NotFoundException(ResponseMessage.DATA_NOT_FOUND);

            nilaiSantriRepository.delete(nilaiSantri.get());
            return new DefaultResponse(Constant.VAR_SUCCESS, ResponseMessage.DATA_DELETED, HttpStatus.OK.value(), loggingHolder);
        } catch (Exception e) {
            logger.error("error when delete nilai santri", e);
            throw e;
        }
    }

    private DatatableResponse<BrowseNilaiSantriPojo> toDatatableNilaiSantri(Page<BrowseNilaiSantriProjection> result, int page) {
        List<BrowseNilaiSantriPojo> list = result.getContent().stream().map(this::toPojoBrowseNilaiSantri).collect(Collectors.toList());
        PageDataResponse<BrowseNilaiSantriPojo> dataPage = new PageDataResponse<>(page, result.getSize(), (int) result.getTotalElements(), list);

        return new DatatableResponse<>(Constant.VAR_SUCCESS, ResponseMessage.DATA_FETCHED, dataPage, loggingHolder);
    }

    private BrowseNilaiSantriPojo toPojoBrowseNilaiSantri(BrowseNilaiSantriProjection data) {
        return BrowseNilaiSantriPojo.builder()
                .nilai(data.getNilai())
                .namaSantri(data.getNamaSantri())
                .idSantri(EncryptionUtil.encrypt(data.getIdSantri()))
                .keterangan(data.getKeterangan())
                .tanggalPenilaian(data.getTanggalPenilaian())
                .id(EncryptionUtil.encrypt(data.getId()))
                .build();
    }

    private DetailNilaiSantriPojo toPojoDetailNilaiSantri(DetailNilaiSantriProjection data) {
        return DetailNilaiSantriPojo.builder()
                .mataPelajaran(data.getMataPelajaran())
                .idJadwal(EncryptionUtil.encrypt(data.getIdJadwal()))
                .idPengajar(EncryptionUtil.encrypt(data.getIdPengajar()))
                .namaSantri(data.getNamaSantri())
                .namaPengajar(data.getNamaPengajar())
                .id(EncryptionUtil.encrypt(data.getId()))
                .idSantri(EncryptionUtil.encrypt(data.getIdSantri()))
                .keterangan(data.getKeterangan())
                .nilai(data.getNilai())
                .tanggalPenilaian(data.getTanggalPenilaian())
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
