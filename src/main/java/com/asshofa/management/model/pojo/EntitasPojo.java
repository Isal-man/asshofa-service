package com.asshofa.management.model.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EntitasPojo {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    String idEntitas;
    @NotBlank(message = "ID Header tidak boleh kosong.")
    @Size(max = 255)
    String idHeader;
    @NotNull(message = "Seri entitas tidak boleh kosong.")
    BigDecimal seriEntitas;
    @Size(max = 255)
    @NotBlank(message = "Nama entitas tidak boleh kosong.")

    String namaEntitas;
    String kodeEntitas;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    String jenisEntitas;
    @Size(max = 255)
    String nibEntitas;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    String nipRekam;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Jakarta")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Timestamp waktuRekam;
}
