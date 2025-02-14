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
@Table(name = "nilai_santri", schema = "asshofa_management")
public class NilaiSantri {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "smallserial")
    private Short id;

    private Integer nilai;
    private String keterangan;
    private LocalDate tanggalPenilaian;

    @ManyToOne
    @JoinColumn(name = "id_santri", referencedColumnName = "id")
    private Santri santri;

    @ManyToOne
    @JoinColumn(name = "id_jadwal", referencedColumnName = "id")
    private JadwalPengajaran jadwalPengajaran;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
