package com.asshofa.mapper;

import com.asshofa.model.pojo.JadwalPengajaranPojo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface JadwalPengajaranMapper {
    List<JadwalPengajaranPojo> getDataJadwalPengajaran(String hari, String mataPelajaran, Integer page, Integer limit);

    JadwalPengajaranPojo getJadwalPengajaranById(String id);

    Integer countJadwalPengajaran(String hari, String mataPelajaran);

    void create(JadwalPengajaranPojo data);

    void update(JadwalPengajaranPojo data);

    void delete(String id);
}
