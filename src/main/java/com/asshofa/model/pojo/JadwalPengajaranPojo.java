package com.asshofa.model.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.sql.Time;
import java.sql.Timestamp;

@Getter
@Setter
public class JadwalPengajaranPojo {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String id;

    @NotBlank(message = "Hari cannot be empty")
    private String hari;
    @NotBlank(message = "Jam Mulai cannot be empty")
    private Time jamMulai;
    @NotBlank(message = "Jam Selesai cannot be empty")
    private Time jamSelesai;
    @NotBlank(message = "Mata Pelajaran cannot be empty")
    private String mataPelajaran;
    @NotBlank(message = "ID Pengajar cannot be empty")
    private String idPengajar;
    private String namaPengajar;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Timestamp createdAt;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Timestamp updatedAt;
}
