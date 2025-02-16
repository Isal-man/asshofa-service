package com.asshofa.management.service;

import com.asshofa.management.model.param.BrowseJadwalPengajaranParam;
import com.asshofa.management.model.pojo.BrowseJadwalPengajaranPojo;
import com.asshofa.management.model.pojo.DetailJadwalPengajaranPojo;
import com.asshofa.management.model.pojo.RekamJadwalPengajaranPojo;
import com.asshofa.management.model.response.DataResponse;
import com.asshofa.management.model.response.DatatableResponse;
import com.asshofa.management.model.response.DefaultResponse;

public interface JadwalPengajaranService {
    DatatableResponse<BrowseJadwalPengajaranPojo> browseJadwalPengajaran(BrowseJadwalPengajaranParam param);
    DataResponse<DetailJadwalPengajaranPojo> getDetailJadwalPengajaran(String id);
    DataResponse<DetailJadwalPengajaranPojo> create(RekamJadwalPengajaranPojo rekam);
    DataResponse<DetailJadwalPengajaranPojo> update(RekamJadwalPengajaranPojo rekam, String id);
    DefaultResponse delete(String id);
}
