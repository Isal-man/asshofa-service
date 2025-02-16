package com.asshofa.management.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "santri", schema = "asshofa_management")
public class Santri {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "smallserial")
    private Short id;

    private String namaLengkap;
    private String tempatLahir;
    private LocalDate tanggalLahir;
    private String jenisKelamin;
    private String gambar;

    @Column(columnDefinition = "text")
    private String alamat;

    @ManyToOne
    @JoinColumn(name = "id_wali", referencedColumnName = "id")
    private WaliSantri waliSantri;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
