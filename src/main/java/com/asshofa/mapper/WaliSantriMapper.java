package com.asshofa.mapper;

import com.asshofa.model.pojo.SantriPojo;
import com.asshofa.model.pojo.WaliSantriPojo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface WaliSantriMapper {
    List<WaliSantriPojo> getDataWaliSantri(String nama, String telepon, String alamat, Integer page, Integer limit);

    List<WaliSantriPojo> getAllWaliSantri();

    WaliSantriPojo getWaliSantriById(String id);

    Integer countWaliSantri(String nama, String telepon, String alamat);

    void create(WaliSantriPojo data);

    void update(WaliSantriPojo data);

    void delete(String id);
}
