package com.asshofa.management.service.impl;

import com.asshofa.management.model.entity.ReferensiWaliSantri;
import com.asshofa.management.model.response.DataResponse;
import com.asshofa.management.model.response.ResponseMessage;
import com.asshofa.management.repository.ReferensiWaliSantriRepository;
import com.asshofa.management.service.ReferensiWaliSantriService;
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
public class ReferensiWaliServiceImpl implements ReferensiWaliSantriService {

    private final ReferensiWaliSantriRepository repository;
    private final LoggingHolder loggingHolder;
    private final HeaderHolder headerHolder;

    private static final Logger logger = LogManager.getLogger(ReferensiWaliServiceImpl.class);

    @Override
    public DataResponse<List<ReferensiWaliSantri>> getDataRefWaliSantri() {
        try {
            new CheckRole(headerHolder).checkRoles();

            return new DataResponse<>(Constant.VAR_SUCCESS,ResponseMessage.DATA_FETCHED, repository.findAll(), loggingHolder);
        } catch (Exception e) {
            logger.error("error when get data referensi wali santri.", e);
            throw e;
        }
    }
}
