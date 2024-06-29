package com.asshofa.controller;

import com.asshofa.model.pojo.ListSantriPojo;
import com.asshofa.model.pojo.SantriPojo;
import com.asshofa.model.response.DataResponse;
import com.asshofa.model.response.DatatableResponse;
import com.asshofa.model.response.DefaultResponse;
import com.asshofa.service.SantriService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/santri")
@CrossOrigin(origins = "*")
@Tag(name = "Santri Service", description = "API Collections For Santri")
public class SantriController {

    private final SantriService santriService;

    public SantriController(SantriService santriService) {
        this.santriService = santriService;
    }

    @GetMapping("get-list-santri")
    @Operation(summary = "Get All Santri", description = "Fetches all santri from data source")
    public ResponseEntity<DatatableResponse<ListSantriPojo>> getDataSantri(@RequestParam(value = "nama", required = false) String nama, @RequestParam(value = "bulan", required = false) String bulan, @RequestParam(value = "tahun", required = false) String tahun, @RequestParam(value = "alamat", required = false) String alamat, @RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "limit", defaultValue = "10") Integer limit) {
        DatatableResponse<ListSantriPojo> response = santriService.getDataSantri(nama, bulan, tahun, alamat, page, limit);
        return ResponseEntity.ok(response);
    }

    @GetMapping("get-select-santri")
    @Operation(summary = "Get Select Santri", description = "Fetch selected santri from data source")
    public ResponseEntity<DataResponse<List<SantriPojo>>> getDataSelectSantri(@RequestParam(value = "nama", required = false) String nama) {
        DataResponse<List<SantriPojo>> response = santriService.getDataSelectSantri(nama);
        return ResponseEntity.ok(response);
    }

    @GetMapping("get-santri")
    @Operation(summary = "Get Santri", description = "Fetch santri from data source")
    public ResponseEntity<DataResponse<SantriPojo>> getSantri(@RequestParam("id") String id) {
        DataResponse<SantriPojo> response = santriService.getSantri(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("get-santri-by-nama")
    @Operation(summary = "Get Santri by Nama", description = "Fetch santri by nama from data source")
    public ResponseEntity<DataResponse<SantriPojo>> getSantriByNama(@RequestParam("nama") String nama) {
        DataResponse<SantriPojo> response = santriService.getSantriByNama(nama);
        return ResponseEntity.ok(response);
    }

    @PostMapping("create")
    @Operation(summary = "Create Data Santri", description = "Create data santri and save to data source")
    public ResponseEntity<DataResponse<SantriPojo>> create(@RequestBody SantriPojo data) {
        DataResponse<SantriPojo> response = santriService.create(data);
        return ResponseEntity.ok(response);
    }

    @PutMapping("update")
    @Operation(summary = "Update Data Santri", description = "Update data santri and save to data source")
    public ResponseEntity<DataResponse<SantriPojo>> update(@RequestParam("id") String id, @RequestBody SantriPojo data) {
        DataResponse<SantriPojo> response = santriService.update(id, data);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("delete")
    @Operation(summary = "Delete Data Santri", description = "Delete data santri from data source")
    public ResponseEntity<DefaultResponse> delete(@RequestParam String id) {
        DefaultResponse response = santriService.delete(id);
        return ResponseEntity.ok(response);
    }
}
