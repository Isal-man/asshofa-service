package com.asshofa.management.controller;

import com.asshofa.management.model.param.BrowseWaliSantriParam;
import com.asshofa.management.model.pojo.BrowseWaliSantriPojo;
import com.asshofa.management.model.pojo.DetailWaliSantriPojo;
import com.asshofa.management.model.pojo.RekamWaliSantriPojo;
import com.asshofa.management.model.response.DataResponse;
import com.asshofa.management.model.response.DatatableResponse;
import com.asshofa.management.model.response.DefaultResponse;
import com.asshofa.management.service.WaliSantriService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/wali-santri")
@Tag(name = "Wali Santri Service", description = "API Collections for Wali Santri")
public class WaliSantriController {

    private final WaliSantriService waliSantriService;

    @PostMapping
    @Operation(
            summary = "Create Wali Santri",
            description = "Merekam data wali santri, kemudian disimpan ke database"
    )
    public ResponseEntity<DataResponse<DetailWaliSantriPojo>> create(@RequestBody @Valid RekamWaliSantriPojo rekamWaliSantriPojo) {
        DataResponse<DetailWaliSantriPojo> response = waliSantriService.create(rekamWaliSantriPojo);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get Wali Santri By ID",
            description = "Mengambil data wali santri berdasarkan ID"
    )
    public ResponseEntity<DataResponse<DetailWaliSantriPojo>> getDetailWaliSantriPojo(@PathVariable("id") String id) {
        DataResponse<DetailWaliSantriPojo> response = waliSantriService.getDetailWaliSantri(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/data")
    @Operation(
            summary = "Browse Wali Santri",
            description = "Menampilkan semua data wali santri"
    )
    public ResponseEntity<DatatableResponse<BrowseWaliSantriPojo>> browseWaliSantri(@RequestBody BrowseWaliSantriParam param) {
        DatatableResponse<BrowseWaliSantriPojo> response = waliSantriService.browseWaliSantri(param);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Update Wali Santri",
            description = "Mengubah data wali santri dan disimpan ke database"
    )
    public ResponseEntity<DataResponse<DetailWaliSantriPojo>> update(@PathVariable("id") String id, @RequestBody @Valid RekamWaliSantriPojo rekamWaliSantriPojo) {
        DataResponse<DetailWaliSantriPojo> response = waliSantriService.update(rekamWaliSantriPojo, id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete Wali Santri",
            description = "Menghapus data wali santri dari database berdasarkan ID"
    )
    public ResponseEntity<DefaultResponse> delete(@PathVariable("id") String id) {
        DefaultResponse response = waliSantriService.delete(id);
        return ResponseEntity.ok(response);
    }

}
