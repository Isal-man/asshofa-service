package com.asshofa.management.controller;

import com.asshofa.management.model.pojo.DashboardPojo;
import com.asshofa.management.model.response.DataResponse;
import com.asshofa.management.service.DashboardService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dashboard-view")
@Tag(name = "Dashboard Service", description = "API Collections for Dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping
    public ResponseEntity<DataResponse<DashboardPojo>> getDashboard() {
        return ResponseEntity.ok(dashboardService.getDashboard());
    }

}
