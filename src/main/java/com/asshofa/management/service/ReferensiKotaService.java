package com.asshofa.management.service;

import com.asshofa.management.model.entity.ReferensiKota;
import com.asshofa.management.model.response.DataResponse;

import java.util.List;

public interface ReferensiKotaService {
    DataResponse<List<ReferensiKota>> getDataReferensiKota(String namaKota);
}
