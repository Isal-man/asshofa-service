package com.asshofa.service;

import com.asshofa.model.pojo.ListSantriPojo;
import com.asshofa.model.pojo.SantriPojo;
import com.asshofa.model.response.DataResponse;
import com.asshofa.model.response.DatatableResponse;
import com.asshofa.model.response.DefaultResponse;

import java.util.List;

public interface SantriService {
    DatatableResponse<ListSantriPojo> getDataSantri(String nama, String bulan, String tahun, String alamat, Integer page, Integer limit);

    DataResponse<List<SantriPojo>> getDataSelectSantri(String nama);

    DataResponse<SantriPojo> getSantri(String id);

    DataResponse<SantriPojo> getSantriByNama(String nama);

    DataResponse<SantriPojo> create(SantriPojo data);

    DataResponse<SantriPojo> update(String id, SantriPojo data);

    DefaultResponse delete(String id);
}
