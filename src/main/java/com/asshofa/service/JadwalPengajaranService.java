package com.asshofa.service;

import com.asshofa.model.pojo.JadwalPengajaranPojo;
import com.asshofa.model.response.DataResponse;
import com.asshofa.model.response.DatatableResponse;
import com.asshofa.model.response.DefaultResponse;


public interface JadwalPengajaranService {
    DatatableResponse<JadwalPengajaranPojo> getDataJadwalPengajaran(String hari, String mataPelajaran, Integer page, Integer limit);

    DataResponse<JadwalPengajaranPojo> getJadwalPengajaran(String id);

    DataResponse<JadwalPengajaranPojo> create(JadwalPengajaranPojo data);

    DataResponse<JadwalPengajaranPojo> update(String id, JadwalPengajaranPojo data);

    DefaultResponse delete(String id);
}
