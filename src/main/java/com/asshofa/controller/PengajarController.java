package com.asshofa.controller;

import com.asshofa.model.pojo.PengajarPojo;
import com.asshofa.model.response.DataResponse;
import com.asshofa.model.response.DatatableResponse;
import com.asshofa.model.response.DefaultResponse;
import com.asshofa.service.PengajarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pengajar")
@CrossOrigin(origins = "*")
@Tag(name = "Pengajar Service", description = "API Collections For Pengajar")
public class PengajarController {

    private final PengajarService pengajarService;

    public PengajarController(PengajarService pengajarService) {
        this.pengajarService = pengajarService;
    }

    @GetMapping("get-list-pengajar")
    @Operation(summary = "Get All Pengajar", description = "Fetches all pengajar from data source")
    public ResponseEntity<DatatableResponse<PengajarPojo>> getDataPengajar(@RequestParam(value = "nama", required = false) String nama, @RequestParam(value = "telepon", required = false) String telepon, @RequestParam(value = "spesialisasi", required = false) String spesialisasi, @RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "limit", defaultValue = "10") Integer limit) {
        DatatableResponse<PengajarPojo> response = pengajarService.getDataPengajar(nama, telepon, spesialisasi, page, limit);
        return ResponseEntity.ok(response);
    }

    @GetMapping("get-pengajar-by-spesialisasi")
    @Operation(summary = "Get Pengajar by Spesialisasi", description = "Fetches all pengajar by spesialisasi from data source")
    public ResponseEntity<DataResponse<List<PengajarPojo>>> getPengajarBySpesialisasi(@RequestParam("idSpesialisasi") String idSpesialisasi) {
        DataResponse<List<PengajarPojo>> response = pengajarService.getPengajarBySpesialisasi(idSpesialisasi);
        return ResponseEntity.ok(response);
    }

    @GetMapping("get-pengajar")
    @Operation(summary = "Get Pengajar", description = "Fetch pengajar from data source")
    public ResponseEntity<DataResponse<PengajarPojo>> getPengajar(@RequestParam("id") String id) {
        DataResponse<PengajarPojo> response = pengajarService.getPengajar(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("create")
    @Operation(summary = "Create Data Pengajar", description = "Create data pengajar and save to data source")
    public ResponseEntity<DataResponse<PengajarPojo>> create(@RequestBody PengajarPojo data) {
        DataResponse<PengajarPojo> response = pengajarService.create(data);
        return ResponseEntity.ok(response);
    }

    @PutMapping("update")
    @Operation(summary = "Update Data Pengajar", description = "Update data pengajar and save to data source")
    public ResponseEntity<DataResponse<PengajarPojo>> update(@RequestParam("id") String id, @RequestBody PengajarPojo data) {
        DataResponse<PengajarPojo> response = pengajarService.update(id, data);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("delete")
    @Operation(summary = "Delete Data Pengajar", description = "Delete data pengajar from data source")
    public ResponseEntity<DefaultResponse> delete(@RequestParam String id) {
        DefaultResponse response = pengajarService.delete(id);
        return ResponseEntity.ok(response);
    }
}
