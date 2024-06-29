package com.asshofa.mapper;

import com.asshofa.model.pojo.PenilaianPojo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PenilaianMapper {
    List<PenilaianPojo> getDataPenilaian(String idJadwal, String namaPelajar, Integer page, Integer limit);

    PenilaianPojo getPenilaianById(String id);

    Integer countPenilaian(String idJadwal, String namaPelajar);

    void create(PenilaianPojo data);

    void update(PenilaianPojo data);

    void delete(String id);
}
