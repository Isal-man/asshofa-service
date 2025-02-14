package com.asshofa.management.service;

import com.asshofa.management.model.pojo.EntitasPojo;
import com.asshofa.management.model.response.DataResponse;
import com.asshofa.management.model.response.DatatableResponse;
import com.asshofa.management.model.response.DefaultResponse;

public interface EntitasService {
    DatatableResponse<EntitasPojo> getDatatable(int page, int limit, String sortField, String sortOrder);
    DataResponse<EntitasPojo> findOne(String idEntitas);
    DataResponse<EntitasPojo> create(EntitasPojo entitas);
    DataResponse<EntitasPojo> update(String idEntitas, EntitasPojo entitas);
    DefaultResponse delete(String idEntitas);
}
