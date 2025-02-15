package com.asshofa.management.service;

import com.asshofa.management.model.pojo.BrowseWaliSantriParam;
import com.asshofa.management.model.pojo.BrowseWaliSantriPojo;
import com.asshofa.management.model.pojo.DetailWaliSantriPojo;
import com.asshofa.management.model.pojo.RekamWaliSantriPojo;
import com.asshofa.management.model.response.DataResponse;
import com.asshofa.management.model.response.DatatableResponse;
import com.asshofa.management.model.response.DefaultResponse;

public interface WaliSantriService {
    DataResponse<DetailWaliSantriPojo> create(RekamWaliSantriPojo rekam);
    DataResponse<DetailWaliSantriPojo> update(RekamWaliSantriPojo rekam, String id);
    DefaultResponse delete(String id);
    DatatableResponse<BrowseWaliSantriPojo> browseWaliSantri(BrowseWaliSantriParam param);
    DataResponse<DetailWaliSantriPojo> getDetailWaliSantri(String id);
}
