package com.asshofa.service;

import com.asshofa.model.pojo.PenilaianPojo;
import com.asshofa.model.response.DataResponse;
import com.asshofa.model.response.DatatableResponse;
import com.asshofa.model.response.DefaultResponse;


public interface PenilaianService {
    DatatableResponse<PenilaianPojo> getDataPenilaian(String idJadwal, String namaPelajar, Integer page, Integer limit);

    DataResponse<PenilaianPojo> getPenilaian(String id);

    DataResponse<PenilaianPojo> create(PenilaianPojo data);

    DataResponse<PenilaianPojo> update(String id, PenilaianPojo data);

    DefaultResponse delete(String id);
}
