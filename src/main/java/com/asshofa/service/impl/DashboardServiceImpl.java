package com.asshofa.service.impl;

import com.asshofa.mapper.DashboardMapper;
import com.asshofa.model.pojo.JumlahSantriByBulanPojo;
import com.asshofa.model.pojo.JumlahSantriByTahunPojo;
import com.asshofa.model.pojo.JumlahSantriPojo;
import com.asshofa.model.pojo.RataRataNilaiPojo;
import com.asshofa.model.response.DataResponse;
import com.asshofa.model.response.ResponseMessage;
import com.asshofa.service.DashboardService;
import com.asshofa.util.interceptor.LoggingHolder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardServiceImpl implements DashboardService {

    private final DashboardMapper dashboardMapper;
    private final LoggingHolder holder;

    private static final Logger log = LogManager.getLogger(DashboardServiceImpl.class);

    public DashboardServiceImpl(DashboardMapper dashboardMapper, LoggingHolder holder) {
        this.dashboardMapper = dashboardMapper;
        this.holder = holder;
    }

    @Override
    public DataResponse<JumlahSantriPojo> dashboardJumlahSantri() {
        try {
            Integer jumlahSantri = dashboardMapper.jumlahSantri();
            List<JumlahSantriByTahunPojo> jumlahSantriByTahunPojo = dashboardMapper.jumlahSantriPerTahun();
            JumlahSantriPojo data = new JumlahSantriPojo();
            data.setTotalSantri(jumlahSantri);
            data.setData(jumlahSantriByTahunPojo);

            return new DataResponse<>(ResponseMessage.DATA_FETCHED, data, holder);
        } catch (Exception e) {
            log.error("error when get dashboard jumlah santri", e);
            throw e;
        }
    }

    @Override
    public DataResponse<List<JumlahSantriByBulanPojo>> dashboardJumlahSantriByBulan() {
        try {
            List<JumlahSantriByBulanPojo> data = dashboardMapper.jumlahSantriPerBulan();
            return new DataResponse<>(ResponseMessage.DATA_FETCHED, data, holder);
        } catch (Exception e) {
            log.error("error when get dashboard rata rata nilai", e);
            throw e;
        }
    }

    @Override
    public DataResponse<List<RataRataNilaiPojo>> dashboardRataRataNilai() {
        try {
            List<RataRataNilaiPojo> data = dashboardMapper.rataRataNilai();
            return new DataResponse<>(ResponseMessage.DATA_FETCHED, data, holder);
        } catch (Exception e) {
            log.error("error when get dashboard rata rata nilai", e);
            throw e;
        }
    }
}
