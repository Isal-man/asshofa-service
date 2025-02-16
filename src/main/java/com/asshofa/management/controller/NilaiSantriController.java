package com.asshofa.management.controller;

import com.asshofa.management.model.param.BrowseNilaiSantriParam;
import com.asshofa.management.model.pojo.BrowseNilaiSantriPojo;
import com.asshofa.management.model.pojo.DetailNilaiSantriPojo;
import com.asshofa.management.model.pojo.RekamNilaiSantriPojo;
import com.asshofa.management.model.response.DataResponse;
import com.asshofa.management.model.response.DatatableResponse;
import com.asshofa.management.model.response.DefaultResponse;
import com.asshofa.management.service.NilaiSantriService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/nilai-santri")
@Tag(name = "Nilai Santri Service", description = "API Collections for NilaiSantri")
public class NilaiSantriController {

    private final NilaiSantriService nilaiSantriService;

    @PostMapping
    @Operation(
            summary = "Create NilaiSantri",
            description = "Merekam data nilaiSantri, kemudian disimpan ke database"
    )
    public ResponseEntity<DataResponse<DetailNilaiSantriPojo>> create(@RequestBody @Valid RekamNilaiSantriPojo rekamNilaiSantriPojo) {
        DataResponse<DetailNilaiSantriPojo> response = nilaiSantriService.create(rekamNilaiSantriPojo);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get NilaiSantri By ID",
            description = "Mengambil data nilaiSantri berdasarkan ID"
    )
    public ResponseEntity<DataResponse<DetailNilaiSantriPojo>> getDetailNilaiSantriPojo(@PathVariable("id") String id) {
        DataResponse<DetailNilaiSantriPojo> response = nilaiSantriService.getDetailNilaiSantri(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/data")
    @Operation(
            summary = "Browse NilaiSantri",
            description = "Menampilkan semua data nilaiSantri"
    )
    public ResponseEntity<DatatableResponse<BrowseNilaiSantriPojo>> browseNilaiSantri(@RequestBody BrowseNilaiSantriParam param) {
        DatatableResponse<BrowseNilaiSantriPojo> response = nilaiSantriService.browseNilaiSantri(param);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Update NilaiSantri",
            description = "Mengubah data nilaiSantri dan disimpan ke database"
    )
    public ResponseEntity<DataResponse<DetailNilaiSantriPojo>> update(@PathVariable("id") String id, @RequestBody @Valid RekamNilaiSantriPojo rekamNilaiSantriPojo) {
        DataResponse<DetailNilaiSantriPojo> response = nilaiSantriService.update(rekamNilaiSantriPojo, id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete NilaiSantri",
            description = "Menghapus data nilaiSantri dari database berdasarkan ID"
    )
    public ResponseEntity<DefaultResponse> delete(@PathVariable("id") String id) {
        DefaultResponse response = nilaiSantriService.delete(id);
        return ResponseEntity.ok(response);
    }

}
