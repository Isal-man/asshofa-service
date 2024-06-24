package com.asshofa.service.impl;

import com.asshofa.mapper.WaliSantriMapper;
import com.asshofa.model.pojo.WaliSantriPojo;
import com.asshofa.model.response.*;
import com.asshofa.service.WaliSantriService;
import com.asshofa.util.interceptor.LoggingHolder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Service
public class WaliSantriServiceImpl implements WaliSantriService {

    private final WaliSantriMapper waliSantriMapper;
    private final LoggingHolder holder;

    private static final Logger log = LogManager.getLogger(WaliSantriServiceImpl.class);

    public WaliSantriServiceImpl(WaliSantriMapper waliSantriMapper, LoggingHolder holder) {
        this.waliSantriMapper = waliSantriMapper;
        this.holder = holder;
    }

    @Override
    public DatatableResponse<WaliSantriPojo> getDataWaliSantri(String nama, String telepon, String alamat, Integer page, Integer limit) {
        try {
            Integer offset = (page - 1) * limit;

            List<WaliSantriPojo> pageResult = waliSantriMapper.getDataWaliSantri(nama, telepon, alamat, offset, limit);
            Integer countResult = waliSantriMapper.countWaliSantri(nama, telepon, alamat);

            PageDataResponse<WaliSantriPojo> data = new PageDataResponse<>(page, limit, countResult, pageResult);

            return new DatatableResponse<>(ResponseMessage.DATA_FETCHED, data, holder);
        } catch (Exception e) {
            log.error("Error when get data santri", e);
            throw e;
        }
    }

    @Override
    public DatatableResponse<WaliSantriPojo> getAllWaliSantri() {
        try {
            List<WaliSantriPojo> result = waliSantriMapper.getAllWaliSantri();

            PageDataResponse<WaliSantriPojo> data = new PageDataResponse<>(0, 0, 0, result);

            return new DatatableResponse<>(ResponseMessage.DATA_FETCHED, data, holder);
        } catch (Exception e) {
            log.error("Error when get all wali santri", e);
            throw e;
        }
    }

    @Override
    public DataResponse<WaliSantriPojo> getWaliSantri(String id) {
        try {
            WaliSantriPojo data = waliSantriMapper.getWaliSantriById(id);
            return new DataResponse<>(ResponseMessage.DATA_FETCHED, data, holder);
        } catch (Exception e) {
            log.error("Error when get santri", e);
            throw e;
        }
    }

    @Override
    public DataResponse<WaliSantriPojo> create(WaliSantriPojo data) {
        try {
            data.setId(UUID.randomUUID().toString());
            data.setCreatedAt(getTimestamp());
            waliSantriMapper.create(data);

            WaliSantriPojo santri = waliSantriMapper.getWaliSantriById(data.getId());
            return new DataResponse<>(ResponseMessage.DATA_CREATED, santri, holder);
        } catch (Exception e) {
            log.error("Error when create santri", e);
            throw e;
        }
    }

    @Override
    public DataResponse<WaliSantriPojo> update(String id, WaliSantriPojo data) {
        try {
            data.setId(id);
            data.setUpdatedAt(getTimestamp());
            waliSantriMapper.update(data);

            WaliSantriPojo santri = waliSantriMapper.getWaliSantriById(data.getId());
            return new DataResponse<>(ResponseMessage.DATA_UPDATED, santri, holder);
        } catch (Exception e) {
            log.error("Error when update santri", e);
            throw e;
        }
    }

    @Override
    public DefaultResponse delete(String id) {
        try {
            waliSantriMapper.delete(id);

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
