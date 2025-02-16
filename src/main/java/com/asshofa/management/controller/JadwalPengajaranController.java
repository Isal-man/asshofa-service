package com.asshofa.management.controller;

import com.asshofa.management.model.param.BrowseJadwalPengajaranParam;
import com.asshofa.management.model.pojo.BrowseJadwalPengajaranPojo;
import com.asshofa.management.model.pojo.DetailJadwalPengajaranPojo;
import com.asshofa.management.model.pojo.RekamJadwalPengajaranPojo;
import com.asshofa.management.model.response.DataResponse;
import com.asshofa.management.model.response.DatatableResponse;
import com.asshofa.management.model.response.DefaultResponse;
import com.asshofa.management.service.JadwalPengajaranService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/jadwal-pengajaran")
@Tag(name = "Jadwal Pengajaran Service", description = "API Collections for Jadwal Pengajaran")
public class JadwalPengajaranController {

    private final JadwalPengajaranService jadwalPengajaranService;

    @PostMapping
    @Operation(
            summary = "Create Jadwal Pengajaran",
            description = "Merekam data jadwal Pengajaran, kemudian disimpan ke database"
    )
    public ResponseEntity<DataResponse<DetailJadwalPengajaranPojo>> create(@RequestBody @Valid RekamJadwalPengajaranPojo rekamJadwalPengajaranPojo) {
        DataResponse<DetailJadwalPengajaranPojo> response = jadwalPengajaranService.create(rekamJadwalPengajaranPojo);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get Jadwal Pengajaran By ID",
            description = "Mengambil data jadwal Pengajaran berdasarkan ID"
    )
    public ResponseEntity<DataResponse<DetailJadwalPengajaranPojo>> getDetailJadwalPengajaranPojo(@PathVariable("id") String id) {
        DataResponse<DetailJadwalPengajaranPojo> response = jadwalPengajaranService.getDetailJadwalPengajaran(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/data")
    @Operation(
            summary = "Browse Jadwal Pengajaran",
            description = "Menampilkan semua data jadwal Pengajaran"
    )
    public ResponseEntity<DatatableResponse<BrowseJadwalPengajaranPojo>> browseJadwalPengajaran(@RequestBody BrowseJadwalPengajaranParam param) {
        DatatableResponse<BrowseJadwalPengajaranPojo> response = jadwalPengajaranService.browseJadwalPengajaran(param);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Update Jadwal Pengajaran",
            description = "Mengubah data jadwal Pengajaran dan disimpan ke database"
    )
    public ResponseEntity<DataResponse<DetailJadwalPengajaranPojo>> update(@PathVariable("id") String id, @RequestBody @Valid RekamJadwalPengajaranPojo rekamJadwalPengajaranPojo) {
        DataResponse<DetailJadwalPengajaranPojo> response = jadwalPengajaranService.update(rekamJadwalPengajaranPojo, id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete Jadwal Pengajaran",
            description = "Menghapus data jadwal Pengajaran dari database berdasarkan ID"
    )
    public ResponseEntity<DefaultResponse> delete(@PathVariable("id") String id) {
        DefaultResponse response = jadwalPengajaranService.delete(id);
        return ResponseEntity.ok(response);
    }

}
