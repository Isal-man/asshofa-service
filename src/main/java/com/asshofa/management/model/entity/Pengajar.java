package com.asshofa.management.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "pengajar", schema = "asshofa_management")
public class Pengajar {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "smallserial")
    private Short id;

    private String namaLengkap;
    private String noTelepon;

    @Column(columnDefinition = "text")
    private String alamat;

    private String spesialisasi;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
