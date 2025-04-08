package com.asshofa.management.repository;

import com.asshofa.management.model.entity.Santri;
import com.asshofa.management.model.entity.WaliSantri;
import com.asshofa.management.model.param.BrowseSantriParam;
import com.asshofa.management.model.projection.BrowseSantriProjection;
import com.asshofa.management.model.projection.SantriTerbaruProjection;
import com.asshofa.management.model.projection.TrenPendaftaranProjection;
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
            "where (cast(:#{#param.nama} as text) is null or upper(cast(:#{#param.nama} as text)) = upper(s.nama_lengkap)) " +
            "and (cast(:#{#param.tempatLahir} as text) is null or upper(cast(:#{#param.tempatLahir} as text)) = upper(tempat_lahir)) " +
            "and (cast(:#{#param.tanggalLahir} as text) is null or s.tanggal_lahir = cast(cast(:#{#param.tanggalLahir} as text) as date)) " +
            "and (cast(:#{#param.jenisKelamin} as text) is null or upper(cast(:#{#param.jenisKelamin} as text)) = upper(jenis_kelamin)) " +
            "and (cast(:#{#param.namaWali} as text) is null or upper(cast(:#{#param.namaWali} as text)) = upper(ws.nama_lengkap)) ", nativeQuery = true)
    Page<BrowseSantriProjection> browseSantri(BrowseSantriParam param, Pageable pageable);

    Integer countByJenisKelaminIgnoreCase(String jenisKelamin);

    @Query(value = "select \n" +
            "id as id, \n" +
            "nama_lengkap as namaLengkap, \n" +
            "created_at as createdAt \n" +
            "from asshofa_management.santri\n" +
            "order by createdAt \n" +
            "limit 10  ", nativeQuery = true)
    List<SantriTerbaruProjection> getSantriTerbaru();

    @Query(value = "select \n" +
            "to_char(created_at, 'Month - YYYY') as bulan, \n" +
            "count(id) as jumlah \n" +
            "from asshofa_management.santri \n" +
            "group by bulan \n" +
            "order by bulan ", nativeQuery = true)
    List<TrenPendaftaranProjection> getTrenPendaftaran();

    @Query("select distinct tempatLahir from Santri")
    List<String> getAllTempatLahir();
}
