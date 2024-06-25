package com.asshofa.service;

import com.asshofa.model.pojo.PengajarPojo;
import com.asshofa.model.response.DataResponse;
import com.asshofa.model.response.DatatableResponse;
import com.asshofa.model.response.DefaultResponse;

public interface PengajarService {
    DatatableResponse<PengajarPojo> getDataPengajar(String nama, String telepon, String spesialisasi, Integer page, Integer limit);

    DataResponse<PengajarPojo> getPengajar(String id);

    DataResponse<PengajarPojo> create(PengajarPojo data);

    DataResponse<PengajarPojo> update(String id, PengajarPojo data);

    DefaultResponse delete(String id);
}
