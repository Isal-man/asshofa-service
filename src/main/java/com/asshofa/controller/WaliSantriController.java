package com.asshofa.controller;

import com.asshofa.model.pojo.WaliSantriPojo;
import com.asshofa.model.response.DataResponse;
import com.asshofa.model.response.DatatableResponse;
import com.asshofa.model.response.DefaultResponse;
import com.asshofa.service.WaliSantriService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wali-santri")
@CrossOrigin(origins = "*")
@Tag(name = "Wali Santri Service", description = "API Collections For Wali Santri")
public class WaliSantriController {

    private final WaliSantriService waliSantriService;

    public WaliSantriController(WaliSantriService waliSantriService) {
        this.waliSantriService = waliSantriService;
    }

    @GetMapping("get-list-wali-santri")
    @Operation(summary = "Get All Wali Santri With Parameter", description = "Fetches all santri from data source")
    public ResponseEntity<DatatableResponse<WaliSantriPojo>> getDataWaliSantri(@RequestParam(value = "nama", required = false) String nama, @RequestParam(value = "telepon", required = false) String telepon, @RequestParam(value = "alamat", required = false) String alamat, @RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "limit", defaultValue = "10") Integer limit) {
        DatatableResponse<WaliSantriPojo> response = waliSantriService.getDataWaliSantri(nama, telepon, alamat, page, limit);
        return ResponseEntity.ok(response);
    }

    @GetMapping("get-all-wali-santri")
    @Operation(summary = "Get All Wali Santri Without Parameter", description = "Fetches all santri from data source")
    public ResponseEntity<DatatableResponse<WaliSantriPojo>> getAllWaliSantri() {
        DatatableResponse<WaliSantriPojo> response = waliSantriService.getAllWaliSantri();
        return ResponseEntity.ok(response);
    }

    @GetMapping("get-wali-santri")
    @Operation(summary = "Get Santri", description = "Fetch santri from data source")
    public ResponseEntity<DataResponse<WaliSantriPojo>> getWaliSantri(@RequestParam("id") String id) {
        DataResponse<WaliSantriPojo> response = waliSantriService.getWaliSantri(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("create")
    @Operation(summary = "Create Data Santri", description = "Create data santri and save to data source")
    public ResponseEntity<DataResponse<WaliSantriPojo>> create(@RequestBody WaliSantriPojo data) {
        DataResponse<WaliSantriPojo> response = waliSantriService.create(data);
        return ResponseEntity.ok(response);
    }

    @PutMapping("update")
    @Operation(summary = "Update Data Santri", description = "Update data santri and save to data source")
    public ResponseEntity<DataResponse<WaliSantriPojo>> update(@RequestParam("id") String id, @RequestBody WaliSantriPojo data) {
        DataResponse<WaliSantriPojo> response = waliSantriService.update(id, data);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("delete")
    @Operation(summary = "Delete Data Santri", description = "Delete data santri from data source")
    public ResponseEntity<DefaultResponse> delete(@RequestParam String id) {
        DefaultResponse response = waliSantriService.delete(id);
        return ResponseEntity.ok(response);
    }
}
