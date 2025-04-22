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
@Table(name = "wali_santri")
public class WaliSantri {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "smallserial")
    private Short id;

    private String namaLengkap;
    private String noTelepon;
    private String gambar;

    @Column(columnDefinition = "text")
    private String alamat;

    private String hubunganDenganSantri;

    @OneToMany(mappedBy = "waliSantri")
    private List<Santri> santriList;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
