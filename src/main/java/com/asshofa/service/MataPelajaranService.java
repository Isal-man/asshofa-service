package com.asshofa.service;

import com.asshofa.model.pojo.MataPelajaranPojo;
import com.asshofa.model.response.DataResponse;

import java.util.List;

public interface MataPelajaranService {
    DataResponse<List<MataPelajaranPojo>> getDataMataPelajaran();
}
