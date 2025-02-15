package com.asshofa.management.service;

import com.asshofa.management.model.pojo.BrowseSantriParam;
import com.asshofa.management.model.pojo.BrowseSantriPojo;
import com.asshofa.management.model.pojo.DetailSantriPojo;
import com.asshofa.management.model.pojo.RekamSantriPojo;
import com.asshofa.management.model.response.DataResponse;
import com.asshofa.management.model.response.DatatableResponse;
import com.asshofa.management.model.response.DefaultResponse;

public interface SantriService {
    DatatableResponse<BrowseSantriPojo> browseSantri(BrowseSantriParam param);
    DataResponse<DetailSantriPojo> getDetailSantri(String id);
    DataResponse<DetailSantriPojo> create(RekamSantriPojo rekam);
    DataResponse<DetailSantriPojo> update(RekamSantriPojo rekam, String id);
    DefaultResponse delete(String id);
}
