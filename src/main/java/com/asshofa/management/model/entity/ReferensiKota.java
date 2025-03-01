package com.asshofa.management.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "referensi_kota")
public class ReferensiKota {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", columnDefinition = "smallserial", nullable = false)
    private Short id;
    @Column(name = "NAMA_KOTA", nullable = false, length = 100)
    private String namaKota;
}
