package com.asshofa.management.repository;

import com.asshofa.management.model.entity.Santri;
import com.asshofa.management.model.entity.WaliSantri;
import com.asshofa.management.model.pojo.BrowseSantriParam;
import com.asshofa.management.model.projection.BrowseSantriProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SantriRepository extends JpaRepository<Santri, Short> {


    List<Santri> findByWaliSantri(WaliSantri waliSantri);

    @Query(value = "select \n" +
            "    s.id as id, \n" +
            "    s.nama_lengkap as namaLengkap, \n" +
            "    s.tempat_lahir as tempatLahir, \n" +
            "    s.tanggal_lahir as tanggalLahir, \n" +
            "    s.jenis_kelamin as jenisKelamin, \n" +
            "    s.created_at as createdAt, \n" +
            "    ws.id as idWali, \n" +
            "    ws.nama_lengkap as namaWali \n" +
            "from asshofa_management.santri s \n" +
            "join asshofa_management.wali_santri ws on s.id_wali = ws.id " +
            "where (:#{#param.nama} is null or upper(cast(:#{#param.nama} as text)) = upper(s.nama_lengkap)) " +
            "and (:#{#param.tempatLahir} is null or upper(cast(:#{#param.tempatLahir} as text)) = upper(tempat_lahir)) " +
            "and (:#{#param.tanggalLahir} is null or upper(cast(:#{#param.tanggalLahir} as text)) = upper(to_char(tanggal_lahir, 'Month'))) " +
            "and (:#{#param.jenisKelamin} is null or upper(cast(:#{#param.jenisKelamin} as text)) = upper(jenis_kelamin)) " +
            "and (:#{#param.namaWali} is null or upper(cast(:#{#param.namaWali} as text)) = upper(ws.nama_lengkap)) ", nativeQuery = true)
    Page<BrowseSantriProjection> browseSantri(BrowseSantriParam param, Pageable pageable);
}
