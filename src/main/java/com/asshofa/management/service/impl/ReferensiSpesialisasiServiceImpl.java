package com.asshofa.management.service.impl;

import com.asshofa.management.model.entity.ReferensiSpesialisasi;
import com.asshofa.management.model.response.DataResponse;
import com.asshofa.management.model.response.ResponseMessage;
import com.asshofa.management.repository.ReferensiSpesialisasiRepository;
import com.asshofa.management.service.ReferensiSpesialisasiService;
import com.asshofa.management.util.CheckRole;
import com.asshofa.management.util.Constant;
import com.asshofa.management.util.interceptor.HeaderHolder;
import com.asshofa.management.util.interceptor.LoggingHolder;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReferensiSpesialisasiServiceImpl implements ReferensiSpesialisasiService {

    private final ReferensiSpesialisasiRepository referensiSpesialisasiRepository;
    private final LoggingHolder loggingHolder;
    private final HeaderHolder headerHolder;

    private static final Logger logger = LogManager.getLogger(ReferensiSpesialisasiServiceImpl.class);

    @Override
    public DataResponse<List<ReferensiSpesialisasi>> getDataReferensiSpesialisasi() {
        try {
            new CheckRole(headerHolder).checkRoles();

            List<ReferensiSpesialisasi> list = referensiSpesialisasiRepository.findAll();

            return new DataResponse<>(Constant.VAR_SUCCESS, ResponseMessage.DATA_FETCHED, list, loggingHolder);
        } catch (Exception e) {
            logger.error("error when get data referensi spesialisasi", e);
            throw e;
        }
    }
}
