package com.asshofa.management.repository;

import com.asshofa.management.model.entity.NilaiSantri;
import com.asshofa.management.model.param.BrowseNilaiSantriParam;
import com.asshofa.management.model.projection.BrowseNilaiSantriProjection;
import com.asshofa.management.model.projection.DetailNilaiSantriProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NilaiSantriRepository extends JpaRepository<NilaiSantri, Short> {
    @Query(value = "select \n" +
            "    ns.id as id, \n" +
            "    ns.keterangan as keterangan, \n" +
            "    ns.nilai as nilai, \n" +
            "    ns.tanggal_penilaian as tanggalPenilaian, \n" +
            "    ns.id_santri as idSantri, \n" +
            "    s.nama_lengkap as namaSantri, \n" +
            "    ns.created_at as createdAt \n " +
            "from asshofa_management.nilai_santri ns\n" +
            "join asshofa_management.santri s on ns.id_santri = s.id \n" +
            "where (:#{#param.tanggalPenilaian} is null or cast(cast(:#{#param.tanggalPenilaian} as text) as date) = tanggal_penilaian) \n" +
            "and (:#{#param.namaSantri} is null or upper(cast(:#{#param.namaSantri} as text)) = upper(nama_lengkap)) ", nativeQuery = true)
    Page<BrowseNilaiSantriProjection> browseNilaiSantri(BrowseNilaiSantriParam param, Pageable pageable);

    @Query(value = "select \n" +
            "    ns.id as id, \n" +
            "    ns.keterangan as keterangan, \n" +
            "    ns.nilai as nilai, \n" +
            "    ns.tanggal_penilaian as tanggalPenilaian, \n" +
            "    ns.id_santri as idSantri, \n" +
            "    s.nama_lengkap as namaSantri, \n" +
            "    ns.id_jadwal as idJadwal, \n" +
            "    jp.mata_pelajaran as mataPelajaran, \n" +
            "    jp.id_pengajar as idPengajar, \n" +
            "    p.nama_lengkap as namaPengajar \n" +
            "from asshofa_management.nilai_santri ns \n" +
            "join asshofa_management.santri s on ns.id_santri = s.id \n" +
            "join asshofa_management.jadwal_pengajaran jp on ns.id_jadwal = jp.id \n" +
            "join asshofa_management.pengajar p on jp.id_pengajar = p.id \n" +
            "where ns.id = :id ", nativeQuery = true)
    DetailNilaiSantriProjection getDetailNilaiSantri(Short id);
}
