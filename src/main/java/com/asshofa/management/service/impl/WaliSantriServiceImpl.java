package com.asshofa.management.service.impl;

import com.asshofa.management.exception.custom.NotFoundException;
import com.asshofa.management.model.entity.Santri;
import com.asshofa.management.model.entity.WaliSantri;
import com.asshofa.management.model.param.BrowseWaliSantriParam;
import com.asshofa.management.model.pojo.*;
import com.asshofa.management.model.projection.BrowseWaliSantriProjection;
import com.asshofa.management.model.response.*;
import com.asshofa.management.repository.SantriRepository;
import com.asshofa.management.repository.WaliSantriRepository;
import com.asshofa.management.service.WaliSantriService;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WaliSantriServiceImpl implements WaliSantriService {

    private final WaliSantriRepository waliSantriRepository;
    private final SantriRepository santriRepository;
    private final LoggingHolder loggingHolder;
    private final HeaderHolder headerHolder;

    private static final Logger logger = LogManager.getLogger(WaliSantriServiceImpl.class);


    @Override
    public DataResponse<DetailWaliSantriPojo> create(RekamWaliSantriPojo rekam) {
        try {
            new CheckRole(headerHolder).checkRoleCRD();

            WaliSantri newWaliSantri = new WaliSantri();
            newWaliSantri.setCreatedAt(new Timestamp(System.currentTimeMillis()).toLocalDateTime());
            WaliSantri waliSantri = waliSantriRepository.save(toEntity(rekam, newWaliSantri));
            waliSantri.setGambar(rekam.getGambar() != null ? rekam.getGambar() : Constant.DEFAULT_IMAGE);

            return new DataResponse<>(Constant.VAR_SUCCESS, ResponseMessage.DATA_CREATED, toPojoDetailWaliSantri(waliSantri), loggingHolder);
        } catch (Exception e) {
            logger.error("error when creating rekam wali santri", e);
            throw e;
        }
    }

    @Override
    public DataResponse<DetailWaliSantriPojo> update(RekamWaliSantriPojo rekam, String id) {
        try {
            new CheckRole(headerHolder).checkRoleCRD();

            Optional<WaliSantri> waliSantri = waliSantriRepository.findById(EncryptionUtil.decrypt(id));
            if (!waliSantri.isPresent()) throw new NotFoundException(ResponseMessage.DATA_NOT_FOUND);
            waliSantri.get().setUpdatedAt(new Timestamp(System.currentTimeMillis()).toLocalDateTime());
            waliSantri.get().setGambar(rekam.getGambar() != null ? rekam.getGambar() : waliSantri.get().getGambar());

            WaliSantri updateWaliSantri = waliSantriRepository.save(toEntity(rekam, waliSantri.get()));
            return new DataResponse<>(Constant.VAR_SUCCESS, ResponseMessage.DATA_UPDATED, toPojoDetailWaliSantri(updateWaliSantri), loggingHolder);
        } catch (Exception e) {
            logger.error("error when update wali santri.", e);
            throw e;
        }
    }

    @Override
    public DefaultResponse delete(String id) {
         try {
             new CheckRole(headerHolder).checkRoleCRD();

             Optional<WaliSantri> waliSantri = waliSantriRepository.findById(EncryptionUtil.decrypt(id));
             if (!waliSantri.isPresent()) throw new NotFoundException(ResponseMessage.DATA_NOT_FOUND);

             waliSantriRepository.delete(waliSantri.get());
             return new DefaultResponse(Constant.VAR_SUCCESS, ResponseMessage.DATA_DELETED, HttpStatus.OK.value(), loggingHolder);
         } catch (Exception e) {
             logger.error("error when delete wali santri", e);
             throw e;
         }
    }

    @Override
    public DatatableResponse<BrowseWaliSantriPojo> browseWaliSantri(BrowseWaliSantriParam param) {
        try {
            new CheckRole(headerHolder).checkRoleBrowseWaliSantri();

            int page = param.getPage() - 1;
            int limit = param.getLimit();
            Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");

            Pageable pageable = PageRequest.of(page, limit, sort);

            Page<BrowseWaliSantriProjection> browseWaliSantriProjections = waliSantriRepository.browseWaliSantri(param, pageable);

            return toDatatableWaliSantri(browseWaliSantriProjections, page);
        } catch (Exception e) {
            logger.error("error when browse wali santri", e);
            throw e;
        }
    }

    @Override
    public DataResponse<DetailWaliSantriPojo> getDetailWaliSantri(String id) {
        try {
            new CheckRole(headerHolder).checkRoleAdminAndPengajar();

            Optional<WaliSantri> waliSantri = waliSantriRepository.findById(EncryptionUtil.decrypt(id));
            if (!waliSantri.isPresent()) throw new NotFoundException(ResponseMessage.DATA_NOT_FOUND);

            return new DataResponse<>(Constant.VAR_SUCCESS, ResponseMessage.DATA_FETCHED, toPojoDetailWaliSantri(waliSantri.get()), loggingHolder);
        } catch (Exception e) {
            logger.error("error when get detail wali santri", e);
            throw e;
        }
    }

    @Override
    public DataResponse<DetailWaliSantriPojo> getDetailWaliSantriByName(String name) {
        try {
            new CheckRole(headerHolder).checkRoleAdminAndPengajar();

            Optional<WaliSantri> waliSantri = waliSantriRepository.findFirstByNamaLengkapEqualsIgnoreCase(name);
            if (!waliSantri.isPresent()) throw new NotFoundException(ResponseMessage.DATA_NOT_FOUND);

            return new DataResponse<>(Constant.VAR_SUCCESS, ResponseMessage.DATA_FETCHED, toPojoDetailWaliSantri(waliSantri.get()), loggingHolder);
        } catch (Exception e) {
            logger.error("error when get detail wali santri", e);
            throw e;
        }
    }

    private WaliSantri toEntity(RekamWaliSantriPojo source, WaliSantri destination) {
        destination.setNamaLengkap(source.getNamaLengkap());
        destination.setNoTelepon(source.getNoTelepon());
        destination.setAlamat(source.getAlamat());
        destination.setHubunganDenganSantri(source.getHubunganDenganSantri());

        return destination;
    }

    private DatatableResponse<BrowseWaliSantriPojo> toDatatableWaliSantri(Page<BrowseWaliSantriProjection> datas, int page) {
        List<BrowseWaliSantriPojo> list = datas.getContent().stream().map(this::toPojoBrowseWaliSantri).collect(Collectors.toList());
        PageDataResponse<BrowseWaliSantriPojo> dataPage = new PageDataResponse<>(page, datas.getSize(), (int) datas.getTotalElements(), list);

        return new DatatableResponse<>(Constant.VAR_SUCCESS, ResponseMessage.DATA_FETCHED, dataPage, loggingHolder);
    }

    private BrowseWaliSantriPojo toPojoBrowseWaliSantri(BrowseWaliSantriProjection data) {
        return BrowseWaliSantriPojo.builder()
                .id(data.getId() != null ? EncryptionUtil.encrypt(data.getId()) : null)
                .namaLengkap(data.getNamaLengkap())
                .noTelepon(data.getNoTelepon())
                .hubunganDenganSantri(data.getHubunganDenganSantri())
                .build();
    }

    private DetailWaliSantriPojo toPojoDetailWaliSantri(WaliSantri waliSantri) {
        List<Santri> santriList = santriRepository.findByWaliSantri(waliSantri);

        return DetailWaliSantriPojo.builder()
                .id(EncryptionUtil.encrypt(waliSantri.getId()))
                .namaLengkap(waliSantri.getNamaLengkap())
                .noTelepon(waliSantri.getNoTelepon())
                .alamat(waliSantri.getAlamat())
                .hubunganDenganSantri(waliSantri.getHubunganDenganSantri())
                .santriList(toPojoDetailSantri(santriList, waliSantri.getGambar()))
                .build();
    }

    private List<DetailSantriPojo> toPojoDetailSantri(List<Santri> santriList, String gambar) {
        List<DetailSantriPojo> detailSantriPojoList = new ArrayList<>();

        if (!santriList.isEmpty()) {
            santriList.forEach(santri -> detailSantriPojoList.add(
                    DetailSantriPojo.builder()
                            .id(EncryptionUtil.encrypt(santri.getId()))
                            .namaLengkap(santri.getNamaLengkap())
                            .tempatLahir(santri.getTempatLahir())
                            .tanggalLahir(santri.getTanggalLahir())
                            .idWali(EncryptionUtil.encrypt(santri.getWaliSantri().getId()))
                            .namaWali(santri.getWaliSantri().getNamaLengkap())
                            .alamat(santri.getAlamat())
                            .jenisKelamin(santri.getJenisKelamin())
                            .gambarWali(gambar)
                            .gambar(santri.getGambar())
                            .build()
            ));
        }

        return detailSantriPojoList;
    }

}
