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
public class ListSantriPojo {
    private String id;
    private String nama;
    private Date tanggalLahir;
    private String alamat;
    private String namaWali;
}
