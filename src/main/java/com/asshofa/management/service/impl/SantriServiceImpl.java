package com.asshofa.management.service.impl;

import com.asshofa.management.exception.custom.NotFoundException;
import com.asshofa.management.model.entity.Santri;
import com.asshofa.management.model.entity.WaliSantri;
import com.asshofa.management.model.pojo.BrowseSantriParam;
import com.asshofa.management.model.pojo.BrowseSantriPojo;
import com.asshofa.management.model.pojo.DetailSantriPojo;
import com.asshofa.management.model.pojo.RekamSantriPojo;
import com.asshofa.management.model.projection.BrowseSantriProjection;
import com.asshofa.management.model.response.*;
import com.asshofa.management.repository.SantriRepository;
import com.asshofa.management.repository.WaliSantriRepository;
import com.asshofa.management.service.SantriService;
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
public class SantriServiceImpl implements SantriService {

    private final SantriRepository santriRepository;
    private final WaliSantriRepository waliSantriRepository;
    private final LoggingHolder loggingHolder;
    private final HeaderHolder headerHolder;

    private static final Logger logger = LogManager.getLogger(SantriServiceImpl.class);


    @Override
    public DatatableResponse<BrowseSantriPojo> browseSantri(BrowseSantriParam param) {
        try {
            new CheckRole(headerHolder).checkRoleBrowseSantri();

            int page = param.getPage() - 1;
            int limit = param.getLimit();
            Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
            Pageable pageable = PageRequest.of(page, limit, sort);

            Page<BrowseSantriProjection> result = santriRepository.browseSantri(param, pageable);

            return toDatatableSantri(result, page);
        } catch (Exception e) {
            logger.error("error when get all santri", e);
            throw e;
        }
    }

    @Override
    public DataResponse<DetailSantriPojo> getDetailSantri(String id) {
        try {
            new CheckRole(headerHolder).checkRoleDetailWaliSantri();

            Optional<Santri> santri = santriRepository.findById(EncryptionUtil.decrypt(id));
            if (!santri.isPresent()) throw new NotFoundException(ResponseMessage.DATA_NOT_FOUND);

            return new DataResponse<>(Constant.VAR_SUCCESS, ResponseMessage.DATA_FETCHED, toPojoDetailSantri(santri.get()), loggingHolder);
        } catch (Exception e) {
            logger.error("error when get detail santri", e);
            throw e;
        }
    }

    @Override
    public DataResponse<DetailSantriPojo> create(RekamSantriPojo rekam) {
        try {
            new CheckRole(headerHolder).checkRoleCRD();

            rekam.setCreatedAt(new Timestamp(System.currentTimeMillis()).toLocalDateTime());

            Optional<WaliSantri> waliSantri = waliSantriRepository.findById(EncryptionUtil.decrypt(rekam.getIdWali()));
            if (!waliSantri.isPresent()) throw new NotFoundException(ResponseMessage.DATA_NOT_FOUND);

            Santri newSantri = new Santri();
            newSantri.setWaliSantri(waliSantri.get());
            Santri santri = santriRepository.save(toEntity(rekam, newSantri));

            return new DataResponse<>(Constant.VAR_SUCCESS, ResponseMessage.DATA_CREATED, toPojoDetailSantri(santri), loggingHolder);
        } catch (Exception e) {
            logger.error("error when create santri", e);
            throw e;
        }
    }

    @Override
    public DataResponse<DetailSantriPojo> update(RekamSantriPojo rekam, String id) {
        try {
            new CheckRole(headerHolder).checkRoleCRD();

            rekam.setUpdatedAt(new Timestamp(System.currentTimeMillis()).toLocalDateTime());

            Optional<Santri> santri = santriRepository.findById(EncryptionUtil.decrypt(id));
            if (!santri.isPresent()) throw new NotFoundException(ResponseMessage.DATA_NOT_FOUND);

            Optional<WaliSantri> waliSantri = waliSantriRepository.findById(EncryptionUtil.decrypt(rekam.getIdWali()));
            if (!waliSantri.isPresent()) throw new NotFoundException(ResponseMessage.DATA_NOT_FOUND);

            santri.get().setWaliSantri(waliSantri.get());
            Santri updateSantri = santriRepository.save(toEntity(rekam, santri.get()));

            return new DataResponse<>(Constant.VAR_SUCCESS, ResponseMessage.DATA_UPDATED, toPojoDetailSantri(updateSantri), loggingHolder);
        } catch (Exception e) {
            logger.error("error when update santri", e);
            throw e;
        }
    }

    @Override
    public DefaultResponse delete(String id) {
        try {
            new CheckRole(headerHolder).checkRoleCRD();

            Optional<Santri> santri = santriRepository.findById(EncryptionUtil.decrypt(id));
            if (!santri.isPresent()) throw new NotFoundException(ResponseMessage.DATA_NOT_FOUND);

            santriRepository.delete(santri.get());
            return new DefaultResponse(Constant.VAR_SUCCESS, ResponseMessage.DATA_DELETED, HttpStatus.OK.value(), loggingHolder);
        } catch (Exception e) {
            logger.error("error when delete santri", e);
            throw e;
        }
    }

    private DatatableResponse<BrowseSantriPojo> toDatatableSantri(Page<BrowseSantriProjection> result, int page) {
        List<BrowseSantriPojo> list = result.getContent().stream().map(this::toPojoBrowseSantri).collect(Collectors.toList());
        PageDataResponse<BrowseSantriPojo> data = new PageDataResponse<>(page, result.getSize(), (int) result.getTotalElements(), list);

        return new DatatableResponse<>(Constant.VAR_SUCCESS, ResponseMessage.DATA_FETCHED, data, loggingHolder);
    }

    private BrowseSantriPojo toPojoBrowseSantri(BrowseSantriProjection data) {
        return BrowseSantriPojo.builder()
                .id(EncryptionUtil.encrypt(data.getId()))
                .namaLengkap(data.getNamaLengkap())
                .tempatLahir(data.getTempatLahir())
                .tanggalLahir(data.getTanggalLahir())
                .jenisKelamin(data.getJenisKelamin())
                .idWali(EncryptionUtil.encrypt(data.getIdWali()))
                .namaWali(data.getNamaWali())
                .build();
    }

    private DetailSantriPojo toPojoDetailSantri(Santri santri) {
        Optional<WaliSantri> waliSantri = waliSantriRepository.findById(santri.getWaliSantri().getId());
        if (!waliSantri.isPresent()) throw new NotFoundException(ResponseMessage.DATA_NOT_FOUND);

        return DetailSantriPojo.builder()
                .id(EncryptionUtil.encrypt(santri.getId()))
                .namaLengkap(santri.getNamaLengkap())
                .tempatLahir(santri.getTempatLahir())
                .tanggalLahir(santri.getTanggalLahir())
                .jenisKelamin(santri.getJenisKelamin())
                .alamat(santri.getAlamat())
                .idWali(EncryptionUtil.encrypt(santri.getWaliSantri().getId()))
                .namaWali(santri.getWaliSantri().getNamaLengkap())
                .build();
    }

    private Santri toEntity(RekamSantriPojo source, Santri destination) {
        destination.setNamaLengkap(source.getNamaLengkap());
        destination.setTempatLahir(source.getTempatLahir());
        destination.setTanggalLahir(source.getTanggalLahir());
        destination.setAlamat(source.getAlamat());
        destination.setJenisKelamin(source.getJenisKelamin());
        destination.setWaliSantri(destination.getWaliSantri());
        destination.setCreatedAt(source.getCreatedAt());
        destination.setUpdatedAt(source.getUpdatedAt());

        return destination;
    }

}
