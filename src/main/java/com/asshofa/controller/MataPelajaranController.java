package com.asshofa.controller;

import com.asshofa.model.pojo.MataPelajaranPojo;
import com.asshofa.model.response.DataResponse;
import com.asshofa.service.MataPelajaranService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/mata-pelajaran")
@CrossOrigin(origins = "*")
@Tag(name = "Mata Pelajaran Service", description = "API Collections For Mata Pelajaran")
public class MataPelajaranController {

    private final MataPelajaranService mataPelajaranService;

    public MataPelajaranController(MataPelajaranService mataPelajaranService) {
        this.mataPelajaranService = mataPelajaranService;
    }

    @GetMapping("get-list-mata-pelajaran")
    @Operation(summary = "Get All Mata Pelajaran", description = "Fetches all mata pelajaran from data source")
    public ResponseEntity<DataResponse<List<MataPelajaranPojo>>> getDataMataPelajaran() {
        DataResponse<List<MataPelajaranPojo>> response = mataPelajaranService.getDataMataPelajaran();
        return ResponseEntity.ok(response);
    }
}
