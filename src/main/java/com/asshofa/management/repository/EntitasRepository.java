package com.asshofa.management.repository;

import com.asshofa.management.model.entity.TdEntitas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EntitasRepository extends JpaRepository<TdEntitas, String>, JpaSpecificationExecutor<TdEntitas> {

}
