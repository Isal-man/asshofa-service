package com.asshofa.controller;

import com.asshofa.model.pojo.SpesialisasiPojo;
import com.asshofa.model.response.DataResponse;
import com.asshofa.service.SpesialisasiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/spesialisasi")
@CrossOrigin(origins = "*")
@Tag(name = "Spesialisasi Service", description = "API Collections For Spesialisasi")
public class SpesialisasiController {

    private final SpesialisasiService spesialisasiService;

    public SpesialisasiController(SpesialisasiService spesialisasiService) {
        this.spesialisasiService = spesialisasiService;
    }

    @GetMapping("get-list-spesialisasi")
    @Operation(summary = "Get All Spesialisasi", description = "Fetches all spesialisasi from data source")
    public ResponseEntity<DataResponse<List<SpesialisasiPojo>>> getDataSpesialisasi() {
        DataResponse<List<SpesialisasiPojo>> response = spesialisasiService.getDataSpesialisasi();
        return ResponseEntity.ok(response);
    }
}
