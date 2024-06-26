package com.asshofa.service.impl;

import com.asshofa.mapper.MataPelajaranMapper;
import com.asshofa.model.pojo.MataPelajaranPojo;
import com.asshofa.model.response.DataResponse;
import com.asshofa.model.response.ResponseMessage;
import com.asshofa.service.MataPelajaranService;
import com.asshofa.util.interceptor.LoggingHolder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MataPelajaranServiceImpl implements MataPelajaranService {

    private final MataPelajaranMapper mataPelajaranMapper;
    private final LoggingHolder holder;

    private static final Logger log = LogManager.getLogger(MataPelajaranServiceImpl.class);

    public MataPelajaranServiceImpl(MataPelajaranMapper mataPelajaranMapper, LoggingHolder holder) {
        this.mataPelajaranMapper = mataPelajaranMapper;
        this.holder = holder;
    }

    @Override
    public DataResponse<List<MataPelajaranPojo>> getDataMataPelajaran() {
        try {
            List<MataPelajaranPojo> data = mataPelajaranMapper.getDataMataPelajaran();
            return new DataResponse<>(ResponseMessage.DATA_FETCHED, data, holder);
        } catch (Exception e) {
            log.error("Error when get data mata pelajaran", e);
            throw e;
        }
    }
}
