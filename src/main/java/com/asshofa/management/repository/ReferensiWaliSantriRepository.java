package com.asshofa.management.repository;

import com.asshofa.management.model.entity.ReferensiWaliSantri;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReferensiWaliSantriRepository extends JpaRepository<ReferensiWaliSantri, Short> {
}
