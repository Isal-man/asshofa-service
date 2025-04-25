package com.asshofa.management.service.impl;

import com.asshofa.management.exception.custom.NotFoundException;
import com.asshofa.management.model.entity.JadwalPengajaran;
import com.asshofa.management.model.entity.Pengajar;
import com.asshofa.management.model.param.BrowsePengajarParam;
import com.asshofa.management.model.pojo.*;
import com.asshofa.management.model.projection.BrowsePengajarProjection;
import com.asshofa.management.model.response.*;
import com.asshofa.management.repository.JadwalPengajaranRepository;
import com.asshofa.management.repository.PengajarRepository;
import com.asshofa.management.service.PengajarService;
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
public class PengajarServiceImpl implements PengajarService {

    private final PengajarRepository pengajarRepository;
    private final JadwalPengajaranRepository jadwalPengajaranRepository;
    private final LoggingHolder loggingHolder;
    private final HeaderHolder headerHolder;

    private static final Logger logger = LogManager.getLogger(PengajarServiceImpl.class);


    @Override
    public DatatableResponse<BrowsePengajarPojo> browsePengajar(BrowsePengajarParam param) {
        try {
            new CheckRole(headerHolder).checkRoles();

            int page = param.getPage() - 1;
            int limit = param.getLimit();
            Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");

            Pageable pageable = PageRequest.of(page, limit, sort);

            Page<BrowsePengajarProjection> result = pengajarRepository.browsePengajar(param, pageable);

            return toDatatablePengajar(result, param.getPage());
        } catch (Exception e) {
            logger.error("error when browse pengajar", e);
            throw e;
        }
    }

    @Override
    public DataResponse<DetailPengajarPojo> getDetailPengajar(String id) {
        try {
            new CheckRole(headerHolder).checkRoles();

            Optional<Pengajar> pengajar = pengajarRepository.findById(EncryptionUtil.decrypt(id));
            if (!pengajar.isPresent()) throw new NotFoundException(ResponseMessage.DATA_NOT_FOUND);

            return new DataResponse<>(Constant.VAR_SUCCESS, ResponseMessage.DATA_FETCHED, toPojoDetailPengajar(pengajar.get()), loggingHolder);
        } catch (Exception e) {
            logger.error("error when detail pengajar", e);
            throw e;
        }
    }

    @Override
    public DataResponse<DetailPengajarPojo> create(RekamPengajarPojo rekam) {
        try {
            new CheckRole(headerHolder).checkRoleCRD();

            Pengajar newPengajar = new Pengajar();
            newPengajar.setCreatedAt(new Timestamp(System.currentTimeMillis()).toLocalDateTime());
            newPengajar.setGambar(rekam.getGambar() != null ? rekam.getGambar() : Constant.DEFAULT_IMAGE);

            Pengajar pengajar = pengajarRepository.save(toEntity(rekam, newPengajar));

            return new DataResponse<>(Constant.VAR_SUCCESS, ResponseMessage.DATA_CREATED, toPojoDetailPengajar(pengajar), loggingHolder);
        } catch (Exception e) {
            logger.error("error when create pengajar", e);
            throw e;
        }
    }

    @Override
    public DataResponse<DetailPengajarPojo> update(RekamPengajarPojo rekam, String id) {
        try {
            new CheckRole(headerHolder).checkRoleCRD();

            Optional<Pengajar> updatePengajar = pengajarRepository.findById(EncryptionUtil.decrypt(id));
            if (!updatePengajar.isPresent()) throw new NotFoundException(ResponseMessage.DATA_NOT_FOUND);
            updatePengajar.get().setUpdatedAt(new Timestamp(System.currentTimeMillis()).toLocalDateTime());
            updatePengajar.get().setGambar(rekam.getGambar() != null ? rekam.getGambar() : updatePengajar.get().getGambar());

            Pengajar pengajar = pengajarRepository.save(toEntity(rekam, updatePengajar.get()));

            return new DataResponse<>(Constant.VAR_SUCCESS, ResponseMessage.DATA_UPDATED, toPojoDetailPengajar(pengajar), loggingHolder);
        } catch (Exception e) {
            logger.error("error when update pengajar", e);
            throw e;
        }
    }

    @Override
    public DefaultResponse delete(String id) {
        try {
            new CheckRole(headerHolder).checkRoleCRD();

            Optional<Pengajar> pengajar = pengajarRepository.findById(EncryptionUtil.decrypt(id));
            if (!pengajar.isPresent()) throw new NotFoundException(ResponseMessage.DATA_NOT_FOUND);

            pengajarRepository.delete(pengajar.get());

            return new DefaultResponse(Constant.VAR_SUCCESS, ResponseMessage.DATA_DELETED, HttpStatus.OK.value(), loggingHolder);
        } catch (Exception e) {
            logger.error("error when delete pengajar", e);
            throw e;
        }
    }

    private DatatableResponse<BrowsePengajarPojo> toDatatablePengajar(Page<BrowsePengajarProjection> result, int page) {
        List<BrowsePengajarPojo> list = result.getContent().stream().map(this::toPojoBrowsePengajar).collect(Collectors.toList());
        PageDataResponse<BrowsePengajarPojo> data = new PageDataResponse<>(page, result.getSize(), (int) result.getTotalElements(), list);

        return new DatatableResponse<>(Constant.VAR_SUCCESS, ResponseMessage.DATA_FETCHED, data, loggingHolder);
    }

    private BrowsePengajarPojo toPojoBrowsePengajar(BrowsePengajarProjection data) {
        return BrowsePengajarPojo.builder()
                .id(EncryptionUtil.encrypt(data.getId()))
                .namaLengkap(data.getNamaLengkap())
                .spesialisasi(data.getSpesialisasi())
                .noTelepon(data.getNoTelepon())
                .build();
    }

    private DetailPengajarPojo toPojoDetailPengajar(Pengajar pengajar) {
        List<JadwalPengajaran> jadwalPengajaranList = jadwalPengajaranRepository.findByPengajar(pengajar);

        return DetailPengajarPojo.builder()
                .id(EncryptionUtil.encrypt(pengajar.getId()))
                .namaLengkap(pengajar.getNamaLengkap())
                .alamat(pengajar.getAlamat())
                .jadwalPengajaranList(toPojoDetailJadwalPengajaran(jadwalPengajaranList))
                .noTelepon(pengajar.getNoTelepon())
                .spesialisasi(pengajar.getSpesialisasi())
                .gambar(pengajar.getGambar())
                .build();
    }

    private List<DetailJadwalPengajaranPojo> toPojoDetailJadwalPengajaran(List<JadwalPengajaran> list) {
        List<DetailJadwalPengajaranPojo> detailJadwalPengajaranList = new ArrayList<>();

        if (list != null) {
            list.forEach(detailJadwalPengajaran -> detailJadwalPengajaranList.add(DetailJadwalPengajaranPojo.builder()
                    .id(EncryptionUtil.encrypt(detailJadwalPengajaran.getId()))
                    .hari(detailJadwalPengajaran.getHari())
                    .idPengajar(EncryptionUtil.encrypt(detailJadwalPengajaran.getPengajar().getId()))
                    .namaPengajar(detailJadwalPengajaran.getPengajar().getNamaLengkap())
                    .mataPelajaran(detailJadwalPengajaran.getMataPelajaran())
                    .jamMulai(detailJadwalPengajaran.getJamMulai())
                    .jamSelesai(detailJadwalPengajaran.getJamSelesai())
                    .build()
            ));
        }

        return detailJadwalPengajaranList;
    }

    private Pengajar toEntity(RekamPengajarPojo source, Pengajar destination) {
        destination.setAlamat(source.getAlamat());
        destination.setNamaLengkap(source.getNamaLengkap());
        destination.setNoTelepon(source.getNoTelepon());
        destination.setSpesialisasi(source.getSpesialisasi());

        return destination;
    }

}
