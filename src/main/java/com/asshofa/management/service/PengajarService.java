package com.asshofa.management.service;

import com.asshofa.management.model.pojo.BrowsePengajarParam;
import com.asshofa.management.model.pojo.BrowsePengajarPojo;
import com.asshofa.management.model.pojo.DetailPengajarPojo;
import com.asshofa.management.model.pojo.RekamPengajarPojo;
import com.asshofa.management.model.response.DataResponse;
import com.asshofa.management.model.response.DatatableResponse;
import com.asshofa.management.model.response.DefaultResponse;

public interface PengajarService {
    DatatableResponse<BrowsePengajarPojo> browsePengajar(BrowsePengajarParam param);
    DataResponse<DetailPengajarPojo> getDetailPengajar(String id);
    DataResponse<DetailPengajarPojo> create(RekamPengajarPojo rekam);
    DataResponse<DetailPengajarPojo> update(RekamPengajarPojo rekam, String id);
    DefaultResponse delete(String id);
}
