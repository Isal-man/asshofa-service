package com.asshofa.mapper;

import com.asshofa.model.pojo.PengajarPojo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PengajarMapper {
    List<PengajarPojo> getDataPengajar(String nama, String telepon, String spesialisasi, Integer page, Integer limit);

    List<PengajarPojo> getPengajarBySpesialisasi(String spesialisasi);

    PengajarPojo getPengajarById(String id);

    Integer countPengajar(String nama, String telepon, String spesialisasi);

    void create(PengajarPojo data);

    void update(PengajarPojo data);

    void delete(String id);
}
