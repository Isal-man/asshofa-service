package com.asshofa.model.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
public class PenilaianPojo {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String id;
    @NotBlank(message = "ID Santri cannot be empty")
    private String idSantri;
    @NotBlank(message = "ID Jadwal cannot be empty")
    private String idJadwal;
    @NotBlank(message = "Nilai cannot be empty")
    private Double nilai;
    private String namaPelajar;
    private Date tanggalPenilaian;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Timestamp createdAt;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Timestamp updatedAt;
}
