package com.asshofa.management.service;

import com.asshofa.management.model.param.BrowseNilaiSantriParam;
import com.asshofa.management.model.pojo.BrowseNilaiSantriPojo;
import com.asshofa.management.model.pojo.DetailNilaiSantriPojo;
import com.asshofa.management.model.pojo.RekamNilaiSantriPojo;
import com.asshofa.management.model.response.DataResponse;
import com.asshofa.management.model.response.DatatableResponse;
import com.asshofa.management.model.response.DefaultResponse;

public interface NilaiSantriService {
    DatatableResponse<BrowseNilaiSantriPojo> browseNilaiSantri(BrowseNilaiSantriParam param);
    DataResponse<DetailNilaiSantriPojo> getDetailNilaiSantri(String id);
    DataResponse<DetailNilaiSantriPojo> create(RekamNilaiSantriPojo rekam);
    DataResponse<DetailNilaiSantriPojo> update(RekamNilaiSantriPojo rekam, String id);
    DefaultResponse delete(String id);
}
