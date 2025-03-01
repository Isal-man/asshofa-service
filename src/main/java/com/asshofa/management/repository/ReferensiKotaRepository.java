package com.asshofa.management.repository;

import com.asshofa.management.model.entity.ReferensiKota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReferensiKotaRepository extends JpaRepository<ReferensiKota, Short> {
    List<ReferensiKota> findByNamaKotaContainingIgnoreCase(String namaKota);
}
