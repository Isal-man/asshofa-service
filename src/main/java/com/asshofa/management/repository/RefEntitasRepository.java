package com.asshofa.management.repository;

import com.asshofa.management.model.entity.TrEntitas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RefEntitasRepository extends JpaRepository<TrEntitas, String>, JpaSpecificationExecutor<TrEntitas> {
}
