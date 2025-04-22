package com.asshofa.management.repository;

import com.asshofa.management.model.entity.WaliSantri;
import com.asshofa.management.model.param.BrowseWaliSantriParam;
import com.asshofa.management.model.projection.BrowseWaliSantriProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface WaliSantriRepository extends JpaRepository<WaliSantri, Short> {

    @Query(value = "select id as id, " +
            "nama_lengkap as namaLengkap, " +
            "no_telepon as noTelepon, " +
            "hubungan_dengan_santri as hubunganDenganSantri, " +
            "created_at as createdAt " +
            "from asshofa_management.wali_santri " +
            "where (:#{#param.namaLengkap} is null or upper(cast(:#{#param.namaLengkap} as text)) = upper(nama_lengkap)) " +
            "and (:#{#param.noTelepon} is null or cast(:#{#param.noTelepon} as text) = no_telepon) " +
            "and (:#{#param.hubunganDenganSantri} is null or upper(cast(:#{#param.hubunganDenganSantri} as text)) = upper(hubungan_dengan_santri))", nativeQuery = true)
    Page<BrowseWaliSantriProjection> browseWaliSantri(BrowseWaliSantriParam param, Pageable pageable);

    Optional<WaliSantri> findFirstByNamaLengkapEqualsIgnoreCase(String namaLengkap);
}
