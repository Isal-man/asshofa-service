package com.asshofa.management.service;

import com.asshofa.management.model.pojo.DetailNilaiSantriPojo;
import com.asshofa.management.model.pojo.RekamNilaiSantriPojo;
import com.asshofa.management.model.response.DataResponse;

public interface NilaiSantriService {
    DataResponse<DetailNilaiSantriPojo> create(RekamNilaiSantriPojo rekam);
    DataResponse<DetailNilaiSantriPojo> update(RekamNilaiSantriPojo rekam, String id);
}
