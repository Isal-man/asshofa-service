package com.asshofa.service.impl;

import com.asshofa.mapper.SantriMapper;
import com.asshofa.model.pojo.SantriPojo;
import com.asshofa.model.response.*;
import com.asshofa.service.SantriService;
import com.asshofa.util.interceptor.LoggingHolder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Service
public class SantriServiceImpl implements SantriService {

    private final SantriMapper santriMapper;
    private final LoggingHolder holder;

    private static final Logger log = LogManager.getLogger(SantriServiceImpl.class);

    public SantriServiceImpl(SantriMapper santriMapper, LoggingHolder holder) {
        this.santriMapper = santriMapper;
        this.holder = holder;
    }

    @Override
    public DatatableResponse<SantriPojo> getDataSantri(String nama, String bulan, String tahun, String alamat, Integer page, Integer limit) {
        try {
            Integer offset = (page - 1) * limit;

            List<SantriPojo> pageResult = santriMapper.getDataSantri(nama, bulan, tahun, alamat, offset, limit);
            Integer countResult = santriMapper.countSantri(nama, bulan, tahun, alamat);

            PageDataResponse<SantriPojo> data = new PageDataResponse<>(page, limit, countResult, pageResult);

            return new DatatableResponse<>(ResponseMessage.DATA_FETCHED, data, holder);
        } catch (Exception e) {
            log.error("Error when get data santri", e);
            throw e;
        }
    }

    @Override
    public DataResponse<SantriPojo> getSantri(String id) {
        try {
            SantriPojo data = santriMapper.getSantriById(id);
            return new DataResponse<>(ResponseMessage.DATA_FETCHED, data, holder);
        } catch (Exception e) {
            log.error("Error when get santri", e);
            throw e;
        }
    }

    @Override
    public DataResponse<SantriPojo> create(SantriPojo data) {
        try {
            data.setId(UUID.randomUUID().toString());
            data.setCreatedAt(getTimestamp());
            santriMapper.create(data);

            SantriPojo santri = santriMapper.getSantriById(data.getId());
            return new DataResponse<>(ResponseMessage.DATA_CREATED, santri, holder);
        } catch (Exception e) {
            log.error("Error when create santri", e);
            throw e;
        }
    }

    @Override
    public DataResponse<SantriPojo> update(String id, SantriPojo data) {
        try {
            data.setId(id);
            data.setUpdatedAt(getTimestamp());
            santriMapper.update(data);

            SantriPojo santri = santriMapper.getSantriById(data.getId());
            return new DataResponse<>(ResponseMessage.DATA_UPDATED, santri, holder);
        } catch (Exception e) {
            log.error("Error when update santri", e);
            throw e;
        }
    }

    @Override
    public DefaultResponse delete(String id) {
        try {
            santriMapper.delete(id);

            return new DefaultResponse(ResponseMessage.DATA_DELETED, holder);
        } catch (Exception e) {
            log.error("Error when delete santri", e);
            throw e;
        }
    }

    public Timestamp getTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }
}
