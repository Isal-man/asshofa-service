package com.asshofa.management.repository;

import com.asshofa.management.model.entity.JadwalPengajaran;
import com.asshofa.management.model.entity.Pengajar;
import com.asshofa.management.model.param.BrowseJadwalPengajaranParam;
import com.asshofa.management.model.projection.BrowseJadwalPengajaranProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JadwalPengajaranRepository extends JpaRepository<JadwalPengajaran, Short> {
    @Query(value = "select \n" +
            "    jp.id as id, \n" +
            "    jp.hari as hari, \n" +
            "    jp.jam_mulai as jamMulai, \n" +
            "    jp.jam_selesai as jamSelesai, \n" +
            "    jp.mata_pelajaran as mataPelajaran, \n" +
            "    jp.id_pengajar as idPengajar, \n" +
            "    p.nama_lengkap as namaPengajar, \n" +
            "    jp.created_at as createdAt \n" +
            "from asshofa_management.jadwal_pengajaran jp \n" +
            "join asshofa_management.pengajar p on jp.id_pengajar = p.id \n" +
            "where (:#{#param.hari} is null or upper(cast(:#{#param.hari} as text)) = upper(hari)) \n" +
            "and (:#{#param.mataPelajaran} is null or upper(cast(:#{#param.mataPelajaran} as text)) = upper(mata_pelajaran)) \n" +
            "and (:#{#param.namaPengajar} is null or upper(cast(:#{#param.namaPengajar} as text)) = upper(nama_lengkap)) ", nativeQuery = true)
    Page<BrowseJadwalPengajaranProjection> browseJadwalPengajaran(BrowseJadwalPengajaranParam param, Pageable pageable);

    List<JadwalPengajaran> findByPengajar(Pengajar pengajar);
}
