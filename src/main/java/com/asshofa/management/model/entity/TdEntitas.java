package com.asshofa.management.model.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "td_entitas")
public class TdEntitas {
    @Id
    @Column(name = "ID_ENTITAS", length = 255)
    private String idEntitas;

    @Column(name = "ID_HEADER", length = 255)
    private String idHeader;

    @Column(name = "SERI_ENTITAS")
    private BigDecimal seriEntitas;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kode_entitas", referencedColumnName="kode_entitas")
    private TrEntitas refEntitas;

    @Column(name = "NAMA_ENTITAS", length = 255)
    private String namaEntitas;

    @Column(name = "NIB_ENTITAS", length = 255)
    @Size(max = 255)
    private String nibEntitas;

    @Column(name = "NIP_REKAM")
    private String nipRekam;

    @Column(name = "WAKTU_REKAM")
    private Timestamp waktuRekam;
}
