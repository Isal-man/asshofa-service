package com.asshofa.service.impl;

import com.asshofa.mapper.PengajarMapper;
import com.asshofa.model.pojo.PengajarPojo;
import com.asshofa.model.response.*;
import com.asshofa.service.PengajarService;
import com.asshofa.util.interceptor.LoggingHolder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Service
public class PengajarServiceImpl implements PengajarService {

    private final PengajarMapper pengajarMapper;
    private final LoggingHolder holder;

    private static final Logger log = LogManager.getLogger(PengajarServiceImpl.class);

    public PengajarServiceImpl(PengajarMapper pengajarMapper, LoggingHolder holder) {
        this.pengajarMapper = pengajarMapper;
        this.holder = holder;
    }

    @Override
    public DatatableResponse<PengajarPojo> getDataPengajar(String nama, String telepon, String spesialisasi, Integer page, Integer limit) {
        try {
            Integer offset = (page - 1) * limit;

            List<PengajarPojo> pageResult = pengajarMapper.getDataPengajar(nama, telepon, spesialisasi, offset, limit);
            Integer countResult = pengajarMapper.countPengajar(nama, telepon, spesialisasi);

            PageDataResponse<PengajarPojo> data = new PageDataResponse<>(page, limit, countResult, pageResult);

            return new DatatableResponse<>(ResponseMessage.DATA_FETCHED, data, holder);
        } catch (Exception e) {
            log.error("Error when get data pengajar", e);
            throw e;
        }
    }

    @Override
    public DataResponse<PengajarPojo> getPengajar(String id) {
        try {
            PengajarPojo data = pengajarMapper.getPengajarById(id);
            return new DataResponse<>(ResponseMessage.DATA_FETCHED, data, holder);
        } catch (Exception e) {
            log.error("Error when get pengajar", e);
            throw e;
        }
    }

    @Override
    public DataResponse<PengajarPojo> create(PengajarPojo data) {
        try {
            data.setId(UUID.randomUUID().toString());
            data.setCreatedAt(getTimestamp());
            pengajarMapper.create(data);

            PengajarPojo pengajar = pengajarMapper.getPengajarById(data.getId());
            return new DataResponse<>(ResponseMessage.DATA_CREATED, pengajar, holder);
        } catch (Exception e) {
            log.error("Error when create pengajar", e);
            throw e;
        }
    }

    @Override
    public DataResponse<PengajarPojo> update(String id, PengajarPojo data) {
        try {
            data.setId(id);
            data.setUpdatedAt(getTimestamp());
            pengajarMapper.update(data);

            PengajarPojo pengajar = pengajarMapper.getPengajarById(data.getId());
            return new DataResponse<>(ResponseMessage.DATA_UPDATED, pengajar, holder);
        } catch (Exception e) {
            log.error("Error when update pengajar", e);
            throw e;
        }
    }

    @Override
    public DefaultResponse delete(String id) {
        try {
            pengajarMapper.delete(id);

            return new DefaultResponse(ResponseMessage.DATA_DELETED, holder);
        } catch (Exception e) {
            log.error("Error when delete pengajar", e);
            throw e;
        }
    }

    public Timestamp getTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }
}
