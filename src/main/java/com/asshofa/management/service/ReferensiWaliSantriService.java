package com.asshofa.management.service;

import com.asshofa.management.model.entity.ReferensiWaliSantri;
import com.asshofa.management.model.response.DataResponse;

import java.util.List;

public interface ReferensiWaliSantriService {
    DataResponse<List<ReferensiWaliSantri>> getDataRefWaliSantri();
}
