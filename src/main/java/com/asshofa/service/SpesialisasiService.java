package com.asshofa.service;

import com.asshofa.model.pojo.SpesialisasiPojo;
import com.asshofa.model.response.DataResponse;

import java.util.List;

public interface SpesialisasiService {
    DataResponse<List<SpesialisasiPojo>> getDataSpesialisasi();
}
