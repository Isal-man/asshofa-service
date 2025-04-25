package com.asshofa.management.controller;

import com.asshofa.management.model.entity.ReferensiKota;
import com.asshofa.management.model.entity.ReferensiSpesialisasi;
import com.asshofa.management.model.entity.ReferensiWaliSantri;
import com.asshofa.management.model.response.DataResponse;
import com.asshofa.management.service.ReferensiKotaService;
import com.asshofa.management.service.ReferensiSpesialisasiService;
import com.asshofa.management.service.ReferensiWaliSantriService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/referensi")
@Tag(name = "Referensi Service", description = "API Collections for Referensi")
public class ReferensiController {

    private final ReferensiKotaService referensiKotaService;
    private final ReferensiWaliSantriService referensiWaliSantriService;
    private final ReferensiSpesialisasiService referensiSpesialisasiService;

    @GetMapping("/kota")
    @Operation(
            summary = "Get all referensi kota",
            description = "Mendapatkan semua data referensi kota"
    )
    public ResponseEntity<DataResponse<List<ReferensiKota>>> getDataReferensiKota(@RequestParam(value = "nama", required = false) String nama) {
        return ResponseEntity.ok(referensiKotaService.getDataReferensiKota(nama));
    }

    @GetMapping("/wali-santri")
    @Operation(
            summary = "Get all referensi wali santri",
            description = "Mendapatkan semua data referensi wali santri"
    )
    public ResponseEntity<DataResponse<List<ReferensiWaliSantri>>> getDataReferensiWaliSantri() {
        return ResponseEntity.ok(referensiWaliSantriService.getDataRefWaliSantri());
    }

    @GetMapping("/spesialisasi")
    @Operation(
            summary = "Get all referensi spesialisasi",
            description = "Mendapatkan semua data referensi spesialisasi"
    )
    public ResponseEntity<DataResponse<List<ReferensiSpesialisasi>>> getDataReferensiSpesialisasi() {
        return ResponseEntity.ok(referensiSpesialisasiService.getDataReferensiSpesialisasi());
    }

}
