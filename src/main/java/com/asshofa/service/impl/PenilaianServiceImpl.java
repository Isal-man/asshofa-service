package com.asshofa.service.impl;

import com.asshofa.mapper.PenilaianMapper;
import com.asshofa.model.pojo.PenilaianPojo;
import com.asshofa.model.response.*;
import com.asshofa.service.PenilaianService;
import com.asshofa.util.interceptor.LoggingHolder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Service
public class PenilaianServiceImpl implements PenilaianService {

    private final PenilaianMapper penilaianMapper;
    private final LoggingHolder holder;

    private static final Logger log = LogManager.getLogger(PenilaianServiceImpl.class);

    public PenilaianServiceImpl(PenilaianMapper penilaianMapper, LoggingHolder holder) {
        this.penilaianMapper = penilaianMapper;
        this.holder = holder;
    }

    @Override
    public DatatableResponse<PenilaianPojo> getDataPenilaian(String idJadwal, String namaPelajar, Integer page, Integer limit) {
        try {
            Integer offset = (page - 1) * limit;

            List<PenilaianPojo> pageResult = penilaianMapper.getDataPenilaian(idJadwal, namaPelajar, offset, limit);
            Integer countResult = penilaianMapper.countPenilaian(idJadwal, namaPelajar);

            PageDataResponse<PenilaianPojo> data = new PageDataResponse<>(page, limit, countResult, pageResult);

            return new DatatableResponse<>(ResponseMessage.DATA_FETCHED, data, holder);
        } catch (Exception e) {
            log.error("Error when get data penilaian", e);
            throw e;
        }
    }

    @Override
    public DataResponse<PenilaianPojo> getPenilaian(String id) {
        try {
            PenilaianPojo data = penilaianMapper.getPenilaianById(id);
            return new DataResponse<>(ResponseMessage.DATA_FETCHED, data, holder);
        } catch (Exception e) {
            log.error("Error when get penilaian", e);
            throw e;
        }
    }

    @Override
    public DataResponse<PenilaianPojo> create(PenilaianPojo data) {
        try {
            data.setId(UUID.randomUUID().toString());
            data.setCreatedAt(getTimestamp());
            penilaianMapper.create(data);

            PenilaianPojo penilaian = penilaianMapper.getPenilaianById(data.getId());
            return new DataResponse<>(ResponseMessage.DATA_CREATED, penilaian, holder);
        } catch (Exception e) {
            log.error("Error when create penilaian", e);
            throw e;
        }
    }

    @Override
    public DataResponse<PenilaianPojo> update(String id, PenilaianPojo data) {
        try {
            data.setId(id);
            data.setUpdatedAt(getTimestamp());
            penilaianMapper.update(data);

            PenilaianPojo penilaian = penilaianMapper.getPenilaianById(data.getId());
            return new DataResponse<>(ResponseMessage.DATA_UPDATED, penilaian, holder);
        } catch (Exception e) {
            log.error("Error when update penilaian", e);
            throw e;
        }
    }

    @Override
    public DefaultResponse delete(String id) {
        try {
            penilaianMapper.delete(id);

            return new DefaultResponse(ResponseMessage.DATA_DELETED, holder);
        } catch (Exception e) {
            log.error("Error when delete penilaian", e);
            throw e;
        }
    }

    public Timestamp getTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }
}
