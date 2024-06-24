package com.asshofa.mapper;

import com.asshofa.model.pojo.ListSantriPojo;
import com.asshofa.model.pojo.SantriPojo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SantriMapper {
    List<ListSantriPojo> getDataSantri(String nama, String bulan, String tahun, String alamat, Integer page, Integer limit);

    SantriPojo getSantriById(String id);

    Integer countSantri(String nama, String bulan, String tahun, String alamat);

    void create(SantriPojo data);

    void update(SantriPojo data);

    void delete(String id);
}
