package com.asshofa.mapper;

import com.asshofa.model.pojo.MataPelajaranPojo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MataPelajaranMapper {
    List<MataPelajaranPojo> getDataMataPelajaran();
}
