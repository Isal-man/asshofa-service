package com.asshofa.management.repository;

import com.asshofa.management.model.entity.ReferensiKota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReferensiKotaRepository extends JpaRepository<ReferensiKota, Short> {
    List<ReferensiKota> findByNamaKotaContainingIgnoreCase(String namaKota);

    @Modifying
    @Query(value = "TRUNCATE TABLE referensi_kota RESTART IDENTITY RESTRICT", nativeQuery = true)
    void truncateTable();
}
