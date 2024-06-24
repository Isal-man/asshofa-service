package com.asshofa.service;

import com.asshofa.model.pojo.WaliSantriPojo;
import com.asshofa.model.response.DataResponse;
import com.asshofa.model.response.DatatableResponse;
import com.asshofa.model.response.DefaultResponse;

public interface WaliSantriService {
    DatatableResponse<WaliSantriPojo> getDataWaliSantri(String nama, String telepon, String alamat, Integer page, Integer limit);

    DatatableResponse<WaliSantriPojo> getAllWaliSantri();

    DataResponse<WaliSantriPojo> getWaliSantri(String id);

    DataResponse<WaliSantriPojo> create(WaliSantriPojo data);

    DataResponse<WaliSantriPojo> update(String id, WaliSantriPojo data);

    DefaultResponse delete(String id);
}
