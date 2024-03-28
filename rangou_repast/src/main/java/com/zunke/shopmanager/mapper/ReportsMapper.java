package com.zunke.shopmanager.mapper;

import com.zunke.shopmanager.dto.reportsForDMY.MembersRegister;
import com.zunke.shopmanager.dto.reportsForDMY.ReportsForDMY;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReportsMapper {

    int selectForDayCount();

    int selectForMouthCount();

    int selectForYearCount();

    List<ReportsForDMY> reportsForDay(@Param("index") int index);

    List<ReportsForDMY> reportsForMouth(@Param("index") int index);

    List<ReportsForDMY> reportsForYear(@Param("index") int index);

    //营业情况分析
    ReportsForDMY selectForReportsByMouth(@Param("date") String date);

    MembersRegister selectForMembersRegisterByMouth(@Param("date") String date);
}
