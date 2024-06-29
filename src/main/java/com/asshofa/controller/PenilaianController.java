package com.asshofa.controller;

import com.asshofa.model.pojo.PenilaianPojo;
import com.asshofa.model.response.DataResponse;
import com.asshofa.model.response.DatatableResponse;
import com.asshofa.model.response.DefaultResponse;
import com.asshofa.service.PenilaianService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/penilaian")
@CrossOrigin(origins = "*")
@Tag(name = "Penilaian Service", description = "API Collections For Penilaian")
public class PenilaianController {

    private final PenilaianService penilaianService;

    public PenilaianController(PenilaianService penilaianService) {
        this.penilaianService = penilaianService;
    }

    @GetMapping("get-list-penilaian")
    @Operation(summary = "Get All Penilaian", description = "Fetches all penilaian from data source")
    public ResponseEntity<DatatableResponse<PenilaianPojo>> getDataPenilaian(@RequestParam("idJadwal") String idJadwal, @RequestParam(value = "namaPelajar", required = false) String namaPelajar, @RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "limit", defaultValue = "10") Integer limit) {
        DatatableResponse<PenilaianPojo> response = penilaianService.getDataPenilaian(idJadwal, namaPelajar, page, limit);
        return ResponseEntity.ok(response);
    }

    @GetMapping("get-penilaian")
    @Operation(summary = "Get Penilaian", description = "Fetch penilaian from data source")
    public ResponseEntity<DataResponse<PenilaianPojo>> getPenilaian(@RequestParam("id") String id) {
        DataResponse<PenilaianPojo> response = penilaianService.getPenilaian(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("create")
    @Operation(summary = "Create Data Penilaian", description = "Create data penilaian and save to data source")
    public ResponseEntity<DataResponse<PenilaianPojo>> create(@RequestBody PenilaianPojo data) {
        DataResponse<PenilaianPojo> response = penilaianService.create(data);
        return ResponseEntity.ok(response);
    }

    @PutMapping("update")
    @Operation(summary = "Update Data Penilaian", description = "Update data penilaian and save to data source")
    public ResponseEntity<DataResponse<PenilaianPojo>> update(@RequestParam("id") String id, @RequestBody PenilaianPojo data) {
        DataResponse<PenilaianPojo> response = penilaianService.update(id, data);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("delete")
    @Operation(summary = "Delete Data Penilaian", description = "Delete data penilaian from data source")
    public ResponseEntity<DefaultResponse> delete(@RequestParam String id) {
        DefaultResponse response = penilaianService.delete(id);
        return ResponseEntity.ok(response);
    }
}
