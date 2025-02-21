package com.asshofa.management.service;

import com.asshofa.management.model.pojo.DashboardPojo;
import com.asshofa.management.model.response.DataResponse;

public interface DashboardService {
    DataResponse<DashboardPojo> getDashboard();
}
