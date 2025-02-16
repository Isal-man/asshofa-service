package com.asshofa.management.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "jadwal_pengajaran")
public class JadwalPengajaran {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "smallserial")
    private Short id;

    private String hari;
    private LocalTime jamMulai;
    private LocalTime jamSelesai;
    private String mataPelajaran;

    @ManyToOne
    @JoinColumn(name = "id_pengajar", referencedColumnName = "id")
    private Pengajar pengajar;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
