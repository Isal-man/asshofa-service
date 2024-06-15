package com.asshofa.model.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
public class SantriPojo {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String id;

    @NotBlank(message = "Full name cannot be empty")
    @Size(min = 3, max = 100, message = "Nama must have at least 3 characters and a maximum of 100 " +
            "characters")
    private String nama;
    @NotBlank(message = "Tanggal lahir cannot be empty")
    private Date tanggalLahir;
    private String alamat;
    private String idWali;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Timestamp createdAt;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Timestamp updatedAt;
}
