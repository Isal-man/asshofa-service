package com.asshofa.mapper;

import com.asshofa.model.pojo.SpesialisasiPojo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SpesialisasiMapper {
    List<SpesialisasiPojo> getDataSpesialisasi();
}
