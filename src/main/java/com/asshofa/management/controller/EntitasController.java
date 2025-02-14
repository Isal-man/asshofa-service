package com.asshofa.management.controller;

import com.asshofa.management.model.pojo.EntitasPojo;
import com.asshofa.management.model.response.DataResponse;
import com.asshofa.management.model.response.DatatableResponse;
import com.asshofa.management.model.response.DefaultResponse;
import com.asshofa.management.service.EntitasService;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/entitas")
@Tag(name = "Entitas Service", description = "API Collections for Entitas Data")
public class EntitasController {
    private final EntitasService entitasService;

    public EntitasController(EntitasService entitasService) {
        this.entitasService = entitasService;
    }

    @GetMapping
    @Operation(
            summary = "Get Entitas List",
            description = "fetches all entitas and their data from data source"
    )
    public ResponseEntity<DatatableResponse<EntitasPojo>> getDatatable(
            @RequestParam(defaultValue = "1") @Min(1) int page,
            @RequestParam(defaultValue = "10") @Min(1) int limit,
            @RequestParam(defaultValue = "waktuRekam", required = false) String sortField,
            @RequestParam(defaultValue = "DESC", required = false) String sortOrder
    ) {
        DatatableResponse<EntitasPojo> list = entitasService.getDatatable(page, limit, sortField, sortOrder);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(path = "/findOne")
    @Operation(
            summary = "Get Detailed Entitas Data",
            description = "fetch detailed entitas data from data source"
    )
    public ResponseEntity<DataResponse<EntitasPojo>> getById(
            @RequestParam String idEntitas
    ) {
        DataResponse<EntitasPojo> data = entitasService.findOne(idEntitas);
        return ResponseEntity.ok().body(data);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Create a new Company",
            description = "Create a new entitas and save it into the data source"
    )
    public ResponseEntity<DataResponse<EntitasPojo>> create(@Valid @RequestBody EntitasPojo entitas) {
        DataResponse<EntitasPojo> data = entitasService.create(entitas);
        return ResponseEntity.ok().body(data);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Update an existing entitas data"
    )
    public ResponseEntity<DataResponse<EntitasPojo>> update(
            @RequestParam String idEntitas,
            @Valid @RequestBody EntitasPojo entitas
    ) {
        DataResponse<EntitasPojo> data = entitasService.update(idEntitas, entitas);
        return ResponseEntity.ok().body(data);
    }

    @DeleteMapping
    @Operation(
            summary = "Delete a entitas data"
    )
    public ResponseEntity<DefaultResponse> delete(
            @RequestParam String idEntitas
    ) {
        DefaultResponse response = entitasService.delete(idEntitas);
        return ResponseEntity.ok().body(response);
    }
}
