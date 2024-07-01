package com.asshofa.mapper;

import com.asshofa.model.pojo.JumlahSantriByBulanPojo;
import com.asshofa.model.pojo.JumlahSantriByTahunPojo;
import com.asshofa.model.pojo.RataRataNilaiPojo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DashboardMapper {

    Integer jumlahSantri();

    List<JumlahSantriByTahunPojo> jumlahSantriPerTahun();

    List<JumlahSantriByBulanPojo> jumlahSantriPerBulan();

    List<RataRataNilaiPojo> rataRataNilai();

}
