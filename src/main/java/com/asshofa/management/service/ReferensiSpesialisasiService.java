package com.asshofa.management.service;

import com.asshofa.management.model.entity.ReferensiSpesialisasi;
import com.asshofa.management.model.response.DataResponse;

import java.util.List;

public interface ReferensiSpesialisasiService {
    DataResponse<List<ReferensiSpesialisasi>> getDataReferensiSpesialisasi();
}
