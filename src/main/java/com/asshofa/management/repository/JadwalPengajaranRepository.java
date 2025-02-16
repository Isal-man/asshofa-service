package com.asshofa.management.repository;

import com.asshofa.management.model.entity.JadwalPengajaran;
import com.asshofa.management.model.entity.Pengajar;
import com.asshofa.management.model.projection.DetailJadwalPengajaranProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JadwalPengajaranRepository extends JpaRepository<JadwalPengajaran, Short> {
    @Query(value = "select \n" +
            "    jp.id as id, \n" +
            "    jp.mata_pelajaran as mataPelajaran, \n" +
            "    jp.hari as hari, \n" +
            "    jp.jam_mulai as jamMulai, \n" +
            "    jp.jam_selesai as jamSelesai, \n" +
            "    jp.id_pengajar as idPengajar, \n" +
            "    p.nama_lengkap as namaPengajar \n" +
            "from asshofa_management.jadwal_pengajaran jp \n" +
            "join asshofa_management.pengajar p on jp.id_pengajar = p.id \n" +
            "where id_pengajar = :#{#pengajar.id} ", nativeQuery = true)
    List<DetailJadwalPengajaranProjection> findByPengajar(Pengajar pengajar);
}
