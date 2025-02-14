package com.asshofa.management.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "tr_entitas")
public class TrEntitas {
    @Id
    @Column(name = "KODE_ENTITAS", length = 255)
    private String kodeEntitas;

    @Column(name = "NAMA_ENTITAS", length = 255)
    private String namaEntitas;
}
