package com.asshofa.service.impl;

import com.asshofa.mapper.JadwalPengajaranMapper;
import com.asshofa.model.pojo.JadwalPengajaranPojo;
import com.asshofa.model.response.*;
import com.asshofa.service.JadwalPengajaranService;
import com.asshofa.util.interceptor.LoggingHolder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Service
public class JadwalPengajaranServiceImpl implements JadwalPengajaranService {

    private final JadwalPengajaranMapper jadwalPengajaranMapper;
    private final LoggingHolder holder;

    private static final Logger log = LogManager.getLogger(JadwalPengajaranServiceImpl.class);

    public JadwalPengajaranServiceImpl(JadwalPengajaranMapper jadwalPengajaranMapper, LoggingHolder holder) {
        this.jadwalPengajaranMapper = jadwalPengajaranMapper;
        this.holder = holder;
    }

    @Override
    public DatatableResponse<JadwalPengajaranPojo> getDataJadwalPengajaran(String hari, String mataPelajaran, Integer page, Integer limit) {
        try {
            Integer offset = (page - 1) * limit;

            List<JadwalPengajaranPojo> pageResult = jadwalPengajaranMapper.getDataJadwalPengajaran(hari, mataPelajaran, offset, limit);
            Integer countResult = jadwalPengajaranMapper.countJadwalPengajaran(hari, mataPelajaran);

            PageDataResponse<JadwalPengajaranPojo> data = new PageDataResponse<>(page, limit, countResult, pageResult);

            return new DatatableResponse<>(ResponseMessage.DATA_FETCHED, data, holder);
        } catch (Exception e) {
            log.error("Error when get data jadwalPengajaran", e);
            throw e;
        }
    }

    @Override
    public DataResponse<JadwalPengajaranPojo> getJadwalPengajaran(String id) {
        try {
            JadwalPengajaranPojo data = jadwalPengajaranMapper.getJadwalPengajaranById(id);
            return new DataResponse<>(ResponseMessage.DATA_FETCHED, data, holder);
        } catch (Exception e) {
            log.error("Error when get jadwalPengajaran", e);
            throw e;
        }
    }

    @Override
    public DataResponse<JadwalPengajaranPojo> create(JadwalPengajaranPojo data) {
        try {
            data.setId(UUID.randomUUID().toString());
            data.setCreatedAt(getTimestamp());
            jadwalPengajaranMapper.create(data);

            JadwalPengajaranPojo jadwalPengajaran = jadwalPengajaranMapper.getJadwalPengajaranById(data.getId());
            return new DataResponse<>(ResponseMessage.DATA_CREATED, jadwalPengajaran, holder);
        } catch (Exception e) {
            log.error("Error when create jadwalPengajaran", e);
            throw e;
        }
    }

    @Override
    public DataResponse<JadwalPengajaranPojo> update(String id, JadwalPengajaranPojo data) {
        try {
            data.setId(id);
            data.setUpdatedAt(getTimestamp());
            jadwalPengajaranMapper.update(data);

            JadwalPengajaranPojo jadwalPengajaran = jadwalPengajaranMapper.getJadwalPengajaranById(data.getId());
            return new DataResponse<>(ResponseMessage.DATA_UPDATED, jadwalPengajaran, holder);
        } catch (Exception e) {
            log.error("Error when update jadwalPengajaran", e);
            throw e;
        }
    }

    @Override
    public DefaultResponse delete(String id) {
        try {
            jadwalPengajaranMapper.delete(id);

            return new DefaultResponse(ResponseMessage.DATA_DELETED, holder);
        } catch (Exception e) {
            log.error("Error when delete jadwalPengajaran", e);
            throw e;
        }
    }

    public Timestamp getTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }
}
