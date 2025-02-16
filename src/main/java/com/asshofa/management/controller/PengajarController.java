package com.asshofa.management.controller;

import com.asshofa.management.model.param.BrowsePengajarParam;
import com.asshofa.management.model.pojo.BrowsePengajarPojo;
import com.asshofa.management.model.pojo.DetailPengajarPojo;
import com.asshofa.management.model.pojo.RekamPengajarPojo;
import com.asshofa.management.model.response.DataResponse;
import com.asshofa.management.model.response.DatatableResponse;
import com.asshofa.management.model.response.DefaultResponse;
import com.asshofa.management.service.PengajarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pengajar")
@Tag(name = "Pengajar Service", description = "API Collections for Pengajar")
public class PengajarController {

    private final PengajarService pengajarService;

    @PostMapping
    @Operation(
            summary = "Create Pengajar",
            description = "Merekam data pengajar, kemudian disimpan ke database"
    )
    public ResponseEntity<DataResponse<DetailPengajarPojo>> create(@RequestBody @Valid RekamPengajarPojo rekamPengajarPojo) {
        DataResponse<DetailPengajarPojo> response = pengajarService.create(rekamPengajarPojo);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get Pengajar By ID",
            description = "Mengambil data pengajar berdasarkan ID"
    )
    public ResponseEntity<DataResponse<DetailPengajarPojo>> getDetailPengajarPojo(@PathVariable("id") String id) {
        DataResponse<DetailPengajarPojo> response = pengajarService.getDetailPengajar(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/data")
    @Operation(
            summary = "Browse Pengajar",
            description = "Menampilkan semua data pengajar"
    )
    public ResponseEntity<DatatableResponse<BrowsePengajarPojo>> browsePengajar(@RequestBody BrowsePengajarParam param) {
        DatatableResponse<BrowsePengajarPojo> response = pengajarService.browsePengajar(param);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Update Pengajar",
            description = "Mengubah data pengajar dan disimpan ke database"
    )
    public ResponseEntity<DataResponse<DetailPengajarPojo>> update(@PathVariable("id") String id, @RequestBody @Valid RekamPengajarPojo rekamPengajarPojo) {
        DataResponse<DetailPengajarPojo> response = pengajarService.update(rekamPengajarPojo, id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete Pengajar",
            description = "Menghapus data pengajar dari database berdasarkan ID"
    )
    public ResponseEntity<DefaultResponse> delete(@PathVariable("id") String id) {
        DefaultResponse response = pengajarService.delete(id);
        return ResponseEntity.ok(response);
    }

}
