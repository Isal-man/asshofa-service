package com.asshofa.management.controller;

import com.asshofa.management.model.pojo.DetailNilaiSantriPojo;
import com.asshofa.management.model.pojo.RekamNilaiSantriPojo;
import com.asshofa.management.model.response.DataResponse;
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

    @PutMapping("/{id}")
    @Operation(
            summary = "Update NilaiSantri",
            description = "Mengubah data nilaiSantri dan disimpan ke database"
    )
    public ResponseEntity<DataResponse<DetailNilaiSantriPojo>> update(@PathVariable("id") String id, @RequestBody @Valid RekamNilaiSantriPojo rekamNilaiSantriPojo) {
        DataResponse<DetailNilaiSantriPojo> response = nilaiSantriService.update(rekamNilaiSantriPojo, id);
        return ResponseEntity.ok(response);
    }

}
