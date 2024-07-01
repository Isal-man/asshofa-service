package com.asshofa.controller;

import com.asshofa.model.pojo.JumlahSantriByBulanPojo;
import com.asshofa.model.pojo.JumlahSantriPojo;
import com.asshofa.model.pojo.RataRataNilaiPojo;
import com.asshofa.model.response.DataResponse;
import com.asshofa.service.DashboardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dashboard")
@CrossOrigin(origins = "*")
@Tag(name = "Dashboard Service", description = "API Collections For Dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("dashboard-jumlah-santri")
    @Operation(summary = "Get Dashboard Jumlah Santri", description = "Fetches jumlah santri from data source")
    public ResponseEntity<DataResponse<JumlahSantriPojo>> getDashboardJumlahSantri() {
        DataResponse<JumlahSantriPojo> response = dashboardService.dashboardJumlahSantri();
        return ResponseEntity.ok(response);
    }

    @GetMapping("dashboard-bulan-santri")
    @Operation(summary = "Get Dashboard Jumlah Santri by Bulan", description = "Fetches jumlah santri by bulan from data source")
    public ResponseEntity<DataResponse<List<JumlahSantriByBulanPojo>>> getDashboardJumlahSantriByBulan() {
        DataResponse<List<JumlahSantriByBulanPojo>> response = dashboardService.dashboardJumlahSantriByBulan();
        return ResponseEntity.ok(response);
    }

    @GetMapping("dashboard-rata-rata-nilai")
    @Operation(summary = "Get Dashboard Rata Rata Nilai", description = "Fetches rata rata nilai from data source")
    public ResponseEntity<DataResponse<List<RataRataNilaiPojo>>> getDashboardRataRataNilai() {
        DataResponse<List<RataRataNilaiPojo>> response = dashboardService.dashboardRataRataNilai();
        return ResponseEntity.ok(response);
    }

}
