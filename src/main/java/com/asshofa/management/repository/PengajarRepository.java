package com.asshofa.management.repository;

import com.asshofa.management.model.entity.Pengajar;
import com.asshofa.management.model.pojo.BrowsePengajarParam;
import com.asshofa.management.model.projection.BrowsePengajarProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PengajarRepository extends JpaRepository<Pengajar, Short> {
    @Query(value = "select \n" +
            "    id as id, \n" +
            "    nama_lengkap as namaLengkap, \n" +
            "    no_telepon as noTelepon, \n" +
            "    spesialisasi as spesialisasi, \n" +
            "    created_at as createdAt \n " +
            "from asshofa_management.pengajar \n" +
            "where (:#{#param.namaLengkap} is null or upper(cast(:#{#param.namaLengkap} as text)) = upper(nama_lengkap)) \n" +
            "and (:#{#param.noTelepon} is null or cast(:#{#param.noTelepon} as text) = no_telepon) \n" +
            "and (:#{#param.spesialisasi} is null or upper(cast(:#{#param.spesialisasi} as text)) = upper(spesialisasi)) ", nativeQuery = true)
    Page<BrowsePengajarProjection> browsePengajar(BrowsePengajarParam param, Pageable pageable);
}
