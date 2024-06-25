package com.asshofa.service.impl;

import com.asshofa.mapper.SpesialisasiMapper;
import com.asshofa.model.pojo.SpesialisasiPojo;
import com.asshofa.model.response.DataResponse;
import com.asshofa.model.response.ResponseMessage;
import com.asshofa.service.SpesialisasiService;
import com.asshofa.util.interceptor.LoggingHolder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpesialisasiServiceImpl implements SpesialisasiService {

    private final SpesialisasiMapper spesialisasiMapper;
    private final LoggingHolder holder;

    private static final Logger log = LogManager.getLogger(SpesialisasiServiceImpl.class);

    public SpesialisasiServiceImpl(SpesialisasiMapper spesialisasiMapper, LoggingHolder holder) {
        this.spesialisasiMapper = spesialisasiMapper;
        this.holder = holder;
    }

    @Override
    public DataResponse<List<SpesialisasiPojo>> getDataSpesialisasi() {
        try {
            List<SpesialisasiPojo> data = spesialisasiMapper.getDataSpesialisasi();
            return new DataResponse<>(ResponseMessage.DATA_FETCHED, data, holder);
        } catch (Exception e) {
            log.error("Error when get data spesialisasi", e);
            throw e;
        }
    }
}
