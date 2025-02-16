package com.asshofa.management.service.impl;

import com.asshofa.management.exception.custom.NotFoundException;
import com.asshofa.management.model.entity.JadwalPengajaran;
import com.asshofa.management.model.entity.Pengajar;
import com.asshofa.management.model.param.BrowseJadwalPengajaranParam;
import com.asshofa.management.model.pojo.BrowseJadwalPengajaranPojo;
import com.asshofa.management.model.pojo.DetailJadwalPengajaranPojo;
import com.asshofa.management.model.pojo.RekamJadwalPengajaranPojo;
import com.asshofa.management.model.projection.BrowseJadwalPengajaranProjection;
import com.asshofa.management.model.response.*;
import com.asshofa.management.repository.JadwalPengajaranRepository;
import com.asshofa.management.repository.PengajarRepository;
import com.asshofa.management.service.JadwalPengajaranService;
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
public class JadwalPengajaranServiceImpl implements JadwalPengajaranService {

    private final JadwalPengajaranRepository jadwalPengajaranRepository;
    private final PengajarRepository pengajarRepository;
    private final LoggingHolder loggingHolder;
    private final HeaderHolder headerHolder;

    private final Logger logger = LogManager.getLogger(JadwalPengajaranServiceImpl.class);

    @Override
    public DatatableResponse<BrowseJadwalPengajaranPojo> browseJadwalPengajaran(BrowseJadwalPengajaranParam param) {
        try {
            new CheckRole(headerHolder).checkRoles();

            int page = param.getPage() - 1;
            int limit = param.getLimit();
            Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");

            Pageable pageable = PageRequest.of(page, limit, sort);

            Page<BrowseJadwalPengajaranProjection> result = jadwalPengajaranRepository.browseJadwalPengajaran(param, pageable);

            return toDatatableJadwalPengajaran(result, page);
        } catch (Exception e) {
            logger.error("error when browse jadwal pengajaran.", e);
            throw e;
        }
    }

    @Override
    public DataResponse<DetailJadwalPengajaranPojo> getDetailJadwalPengajaran(String id) {
        try {
            new CheckRole(headerHolder).checkRoleDetailJadwalPengajaran();

            Optional<JadwalPengajaran> jadwalPengajaran = jadwalPengajaranRepository.findById(EncryptionUtil.decrypt(id));
            if (!jadwalPengajaran.isPresent()) throw new NotFoundException(ResponseMessage.DATA_NOT_FOUND);

            return new DataResponse<>(Constant.VAR_SUCCESS, ResponseMessage.DATA_FETCHED, toPojoDetailJadwalPengajaran(jadwalPengajaran.get()), loggingHolder);
        } catch (Exception e) {
            logger.error("error when get detail jadwal pengajaran.", e);
            throw e;
        }
    }

    @Override
    public DataResponse<DetailJadwalPengajaranPojo> create(RekamJadwalPengajaranPojo rekam) {
        try {
            new CheckRole(headerHolder).checkRoleCRD();

            Optional<Pengajar> pengajar = pengajarRepository.findById(EncryptionUtil.decrypt(rekam.getIdPengajar()));
            if (!pengajar.isPresent()) throw new NotFoundException(ResponseMessage.DATA_NOT_FOUND);

            JadwalPengajaran jadwalPengajaran = new JadwalPengajaran();
            jadwalPengajaran.setCreatedAt(new Timestamp(System.currentTimeMillis()).toLocalDateTime());
            jadwalPengajaran.setPengajar(pengajar.get());

            JadwalPengajaran newJadwalPengajaran = jadwalPengajaranRepository.save(toEntity(rekam, jadwalPengajaran));

            return new DataResponse<>(Constant.VAR_SUCCESS, ResponseMessage.DATA_CREATED, toPojoDetailJadwalPengajaran(newJadwalPengajaran), loggingHolder);
        } catch (Exception e) {
            logger.error("error when create jadwal pengajaran.", e);
            throw e;
        }
    }

    @Override
    public DataResponse<DetailJadwalPengajaranPojo> update(RekamJadwalPengajaranPojo rekam, String id) {
        try {
            new CheckRole(headerHolder).checkRoleCRD();

            Optional<Pengajar> pengajar = pengajarRepository.findById(EncryptionUtil.decrypt(rekam.getIdPengajar()));
            if (!pengajar.isPresent()) throw new NotFoundException(ResponseMessage.DATA_NOT_FOUND);

            Optional<JadwalPengajaran> jadwalPengajaran = jadwalPengajaranRepository.findById(EncryptionUtil.decrypt(id));
            if (!jadwalPengajaran.isPresent()) throw new NotFoundException(ResponseMessage.DATA_NOT_FOUND);

            jadwalPengajaran.get().setUpdatedAt(new Timestamp(System.currentTimeMillis()).toLocalDateTime());
            jadwalPengajaran.get().setPengajar(pengajar.get());

            JadwalPengajaran newJadwalPengajaran = jadwalPengajaranRepository.save(toEntity(rekam, jadwalPengajaran.get()));

            return new DataResponse<>(Constant.VAR_SUCCESS, ResponseMessage.DATA_UPDATED, toPojoDetailJadwalPengajaran(newJadwalPengajaran), loggingHolder);
        } catch (Exception e) {
            logger.error("error when update jadwal pengajaran.", e);
            throw e;
        }
    }

    @Override
    public DefaultResponse delete(String id) {
        try {
            new CheckRole(headerHolder).checkRoleCRD();

            Optional<JadwalPengajaran> jadwalPengajaran = jadwalPengajaranRepository.findById(EncryptionUtil.decrypt(id));
            if (!jadwalPengajaran.isPresent()) throw new NotFoundException(ResponseMessage.DATA_NOT_FOUND);

            jadwalPengajaranRepository.delete(jadwalPengajaran.get());

            return new DefaultResponse(Constant.VAR_SUCCESS, ResponseMessage.DATA_DELETED, HttpStatus.OK.value(), loggingHolder);
        } catch (Exception e) {
            logger.error("error when delete jadwal pengajaran.", e);
            throw e;
        }
    }

    private DatatableResponse<BrowseJadwalPengajaranPojo> toDatatableJadwalPengajaran(Page<BrowseJadwalPengajaranProjection> result, int page) {
        List<BrowseJadwalPengajaranPojo> list = result.getContent().stream().map(this::toPojoBrowseJadwalPengajaran).collect(Collectors.toList());
        PageDataResponse<BrowseJadwalPengajaranPojo> data = new PageDataResponse<>(page, result.getSize(), (int) result.getTotalElements(), list);

        return new DatatableResponse<>(Constant.VAR_SUCCESS, ResponseMessage.DATA_FETCHED, data, loggingHolder);
    }

    private BrowseJadwalPengajaranPojo toPojoBrowseJadwalPengajaran(BrowseJadwalPengajaranProjection data) {
        return BrowseJadwalPengajaranPojo.builder()
                .hari(data.getHari())
                .id(EncryptionUtil.encrypt(data.getId()))
                .idPengajar(EncryptionUtil.encrypt(data.getIdPengajar()))
                .jamMulai(data.getJamMulai())
                .jamSelesai(data.getJamSelesai())
                .mataPelajaran(data.getMataPelajaran())
                .namaPengajar(data.getNamaPengajar())
                .build();
    }

    private DetailJadwalPengajaranPojo toPojoDetailJadwalPengajaran(JadwalPengajaran jadwalPengajaran) {
        return DetailJadwalPengajaranPojo.builder()
                .id(EncryptionUtil.encrypt(jadwalPengajaran.getId()))
                .hari(jadwalPengajaran.getHari())
                .idPengajar(EncryptionUtil.encrypt(jadwalPengajaran.getPengajar().getId()))
                .namaPengajar(jadwalPengajaran.getPengajar().getNamaLengkap())
                .mataPelajaran(jadwalPengajaran.getMataPelajaran())
                .jamMulai(jadwalPengajaran.getJamMulai())
                .jamSelesai(jadwalPengajaran.getJamSelesai())
                .build();
    }

    private JadwalPengajaran toEntity(RekamJadwalPengajaranPojo rekam, JadwalPengajaran jadwalPengajaran) {
        jadwalPengajaran.setHari(rekam.getHari());
        jadwalPengajaran.setJamMulai(rekam.getJamMulai());
        jadwalPengajaran.setJamSelesai(rekam.getJamSelesai());
        jadwalPengajaran.setMataPelajaran(rekam.getMataPelajaran());

        return jadwalPengajaran;
    }

}
