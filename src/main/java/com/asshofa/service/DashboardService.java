package com.asshofa.service;

import com.asshofa.model.pojo.JumlahSantriByBulanPojo;
import com.asshofa.model.pojo.JumlahSantriPojo;
import com.asshofa.model.pojo.RataRataNilaiPojo;
import com.asshofa.model.response.DataResponse;

import java.util.List;

public interface DashboardService {

    DataResponse<JumlahSantriPojo> dashboardJumlahSantri();

    DataResponse<List<JumlahSantriByBulanPojo>> dashboardJumlahSantriByBulan();

    DataResponse<List<RataRataNilaiPojo>> dashboardRataRataNilai();

}
