package com.asshofa.management.model.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class RekamNilaiSantriPojo {
    @NotNull(message = "Keterangan tidak boleh kosong!")
    private String keterangan;
    @NotNull(message = "Nilai tidak boleh kosong!")
    @Min(message = "Tidak boleh kurang dari 0", value = 0)
    @Max(message = "Tidak boleh lebih dari 100", value = 100)
    private Integer nilai;
    @NotNull(message = "Tanggal penilaian tidak boleh kosong!")
    private LocalDate tanggalPenilaian;
    @NotBlank(message = "Santri tidak boleh kosong")
    private String idSantri;
    @NotBlank(message = "Mata pelajaran tidak boleh kosong")
    private String idJadwal;
}
