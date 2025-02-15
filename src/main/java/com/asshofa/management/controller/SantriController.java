package com.asshofa.management.controller;

import com.asshofa.management.model.pojo.BrowseSantriParam;
import com.asshofa.management.model.pojo.BrowseSantriPojo;
import com.asshofa.management.model.pojo.DetailSantriPojo;
import com.asshofa.management.model.pojo.RekamSantriPojo;
import com.asshofa.management.model.response.DataResponse;
import com.asshofa.management.model.response.DatatableResponse;
import com.asshofa.management.model.response.DefaultResponse;
import com.asshofa.management.service.SantriService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/santri")
@Tag(name = "Santri Service", description = "API Collections for Santri")
public class SantriController {

    private final SantriService santriService;

    @PostMapping
    @Operation(
            summary = "Create Santri",
            description = "Merekam data santri, kemudian disimpan ke database"
    )
    public ResponseEntity<DataResponse<DetailSantriPojo>> create(@RequestBody @Valid RekamSantriPojo rekamSantriPojo) {
        DataResponse<DetailSantriPojo> response = santriService.create(rekamSantriPojo);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get Santri By ID",
            description = "Mengambil data santri berdasarkan ID"
    )
    public ResponseEntity<DataResponse<DetailSantriPojo>> getDetailSantriPojo(@PathVariable("id") String id) {
        DataResponse<DetailSantriPojo> response = santriService.getDetailSantri(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/data")
    @Operation(
            summary = "Browse Santri",
            description = "Menampilkan semua data santri"
    )
    public ResponseEntity<DatatableResponse<BrowseSantriPojo>> browseSantri(@RequestBody BrowseSantriParam param) {
        DatatableResponse<BrowseSantriPojo> response = santriService.browseSantri(param);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Update Santri",
            description = "Mengubah data santri dan disimpan ke database"
    )
    public ResponseEntity<DataResponse<DetailSantriPojo>> update(@PathVariable("id") String id, @RequestBody @Valid RekamSantriPojo rekamSantriPojo) {
        DataResponse<DetailSantriPojo> response = santriService.update(rekamSantriPojo, id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete Santri",
            description = "Menghapus data santri dari database berdasarkan ID"
    )
    public ResponseEntity<DefaultResponse> delete(@PathVariable("id") String id) {
        DefaultResponse response = santriService.delete(id);
        return ResponseEntity.ok(response);
    }

}
