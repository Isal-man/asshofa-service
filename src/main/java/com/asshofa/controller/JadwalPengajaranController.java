package com.asshofa.controller;

import com.asshofa.model.pojo.JadwalPengajaranPojo;
import com.asshofa.model.response.DataResponse;
import com.asshofa.model.response.DatatableResponse;
import com.asshofa.model.response.DefaultResponse;
import com.asshofa.service.JadwalPengajaranService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jadwal-pengajaran")
@CrossOrigin(origins = "*")
@Tag(name = "Jadwal Pengajaran Service", description = "API Collections For Jadwal Pengajaran")
public class JadwalPengajaranController {

    private final JadwalPengajaranService jadwalPengajaranService;

    public JadwalPengajaranController(JadwalPengajaranService jadwalPengajaranService) {
        this.jadwalPengajaranService = jadwalPengajaranService;
    }

    @GetMapping("get-list-jadwal-pengajaran")
    @Operation(summary = "Get All Jadwal Pengajaran", description = "Fetches all jadwal pengajaran from data source")
    public ResponseEntity<DatatableResponse<JadwalPengajaranPojo>> getDataJadwalPengajaran(@RequestParam(value = "hari", required = false) String hari, @RequestParam(value = "mataPelajaran", required = false) String mataPelajaran, @RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "limit", defaultValue = "10") Integer limit) {
        DatatableResponse<JadwalPengajaranPojo> response = jadwalPengajaranService.getDataJadwalPengajaran(hari, mataPelajaran, page, limit);
        return ResponseEntity.ok(response);
    }

    @GetMapping("get-jadwal-pengajaran")
    @Operation(summary = "Get Jadwal Pengajaran", description = "Fetch jadwal pengajaran from data source")
    public ResponseEntity<DataResponse<JadwalPengajaranPojo>> getJadwalPengajaran(@RequestParam("id") String id) {
        DataResponse<JadwalPengajaranPojo> response = jadwalPengajaranService.getJadwalPengajaran(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("create")
    @Operation(summary = "Create Data Jadwal Pengajaran", description = "Create data jadwal pengajaran and save to data source")
    public ResponseEntity<DataResponse<JadwalPengajaranPojo>> create(@RequestBody JadwalPengajaranPojo data) {
        DataResponse<JadwalPengajaranPojo> response = jadwalPengajaranService.create(data);
        return ResponseEntity.ok(response);
    }

    @PutMapping("update")
    @Operation(summary = "Update Data Jadwal Pengajaran", description = "Update data jadwal pengajaran and save to data source")
    public ResponseEntity<DataResponse<JadwalPengajaranPojo>> update(@RequestParam("id") String id, @RequestBody JadwalPengajaranPojo data) {
        DataResponse<JadwalPengajaranPojo> response = jadwalPengajaranService.update(id, data);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("delete")
    @Operation(summary = "Delete Data Jadwal Pengajaran", description = "Delete data jadwal pengajaran from data source")
    public ResponseEntity<DefaultResponse> delete(@RequestParam String id) {
        DefaultResponse response = jadwalPengajaranService.delete(id);
        return ResponseEntity.ok(response);
    }
}
