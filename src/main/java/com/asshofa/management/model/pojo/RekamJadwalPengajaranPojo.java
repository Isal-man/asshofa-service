package com.asshofa.management.model.pojo;

import com.asshofa.management.util.serialize.LocalTimeDeserializer;
import com.asshofa.management.util.serialize.LocalTimeSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;

@Builder
@Getter
@Setter
public class RekamJadwalPengajaranPojo {
    @NotBlank(message = "Hari tidak boleh kosong.")
    private String hari;
    @NotBlank(message = "Mata pelajaran tidak boleh kosong.")
    private String mataPelajaran;
    @NotNull(message = "Jam mulai tidak boleh kosong.")
    @JsonSerialize(using = LocalTimeSerializer.class)
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime jamMulai;
    @NotNull(message = "Jam selesai tidak boleh kosong.")
    @JsonSerialize(using = LocalTimeSerializer.class)
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime jamSelesai;
    @NotBlank(message = "Pengajar tidak boleh kosong.")
    private String idPengajar;
}
