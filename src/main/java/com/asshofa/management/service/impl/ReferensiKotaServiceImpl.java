package com.asshofa.management.service.impl;

import com.asshofa.management.model.entity.ReferensiKota;
import com.asshofa.management.model.response.DataResponse;
import com.asshofa.management.model.response.ResponseMessage;
import com.asshofa.management.repository.ReferensiKotaRepository;
import com.asshofa.management.service.ReferensiKotaService;
import com.asshofa.management.util.CheckRole;
import com.asshofa.management.util.Constant;
import com.asshofa.management.util.interceptor.HeaderHolder;
import com.asshofa.management.util.interceptor.LoggingHolder;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReferensiKotaServiceImpl implements ReferensiKotaService {
    
    private final ReferensiKotaRepository referensiKotaRepository;
    private final LoggingHolder loggingHolder;
    private final HeaderHolder headerHolder;
    
    private static final Logger logger = LogManager.getLogger(ReferensiKotaServiceImpl.class);
    
    @Override
    public DataResponse<List<ReferensiKota>> getDataReferensiKota(String namaKota) {
        try {
            new CheckRole(headerHolder).checkRoles();

            List<ReferensiKota> refs;
            if (namaKota != null && !namaKota.isEmpty()) {
                refs = referensiKotaRepository.findByNamaKotaContainingIgnoreCase((namaKota));
            } else {
                refs = referensiKotaRepository.findAll().stream().limit(10).collect(Collectors.toList());
            }
            return new DataResponse<>(Constant.VAR_SUCCESS, ResponseMessage.DATA_FETCHED, refs, loggingHolder);
        } catch (Exception e) {
            logger.error("error when get data referensi kota.", e);
            throw e;
        }
    }
}
