package com.zunke.shopmanager.service.impl;

import com.zunke.shopmanager.dto.reportsForDMY.MembersRegister;
import com.zunke.shopmanager.dto.reportsForDMY.ReportsForDMY;
import com.zunke.shopmanager.mapper.ReportsMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author Yiming
 * @version 1.0
 * @since 2021/9/17 18:49
 */
@Service
public class ReportsServiceImpl {
    @Resource
    private ReportsMapper reportsMapper;

    public Map<String, Object> reportsForYMD(int index, String signal) {
        System.out.println("---------------------------------------------");
        System.out.println(signal);
        System.out.println("---------------------------------------------");
        int signal_int = Integer.parseInt(signal);
        int tableCount = 0;
        int pageCount = 0;
        Map<String, Object> map = new HashMap<>();
        switch (signal_int){
            case 1:
                tableCount = reportsMapper.selectForDayCount();
                pageCount = (tableCount - 1) / 10 + 1;
                index = (index - 1) * 10;
                map.put("code", 200);
                map.put("msg", "查询成功");
                map.put("pageCount", pageCount);
                map.put("count", tableCount);
                map.put("data", reportsMapper.reportsForDay(index));
                return map;
            case 2:
                tableCount = reportsMapper.selectForMouthCount();
                pageCount = (tableCount - 1) / 10 + 1;
                index = (index - 1) * 10;
                map.put("code", 200);
                map.put("msg", "查询成功");
                map.put("pageCount", pageCount);
                map.put("count", tableCount);
                map.put("data", reportsMapper.reportsForMouth(index));
                return map;
            case 3:
                tableCount = reportsMapper.selectForYearCount();
                pageCount = (tableCount - 1) / 10 + 1;
                index = (index - 1) * 10;
                map.put("code", 200);
                map.put("msg", "查询成功");
                map.put("pageCount", pageCount);
                map.put("count", tableCount);
                map.put("data", reportsMapper.reportsForYear(index));
                return map;
            default:
                map.put("code", 500);
                map.put("msg", "查询失败，约定1按日，2按月，3按年");
                return map;
        }

    }
    //营业情况分析
    public Map<String, Object> selectForReportsByMouth(String yearAndMouth) {
        String[] str = yearAndMouth.split("-");//以汉字为标志，分割字符串
        String myYear = str[0];
        String myMouth = str[1];
        int year = Integer.parseInt(myYear);
        int month = Integer.parseInt(myMouth);
        Calendar c = Calendar.getInstance();
        c.set(year, month, 0);//设置时间

        List<String> dateList = new ArrayList<>();
        List<Integer> soldNumList = new ArrayList<>();
        List<Double> soldSumList = new ArrayList<>();
        int dayOfMonth = c.get(Calendar.DAY_OF_MONTH);
        for(int i = 1;i <=dayOfMonth ;i++){
            String day = ""+i;
            if(i<10){
                day = "0"+i;
            }
            ReportsForDMY reportsForDMY = reportsMapper.selectForReportsByMouth(yearAndMouth+"-"+day);
            dateList.add(reportsForDMY.getBywhen().substring(8));
            soldNumList.add(reportsForDMY.getOrdersNum());
            soldSumList.add(reportsForDMY.getOrdersSum());
        }

        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("msg", "查询成功");
        map.put("dateList", dateList);
        map.put("soldNumList",soldNumList);
        map.put("soldSumList",soldSumList);
        return map;
    }

    //会员注册情况分析
    public Map<String, Object> selectForMembersRegisterByMouth(String yearAndMouth) {
        String[] str = yearAndMouth.split("-");//以汉字为标志，分割字符串
        String myYear = str[0];
        String myMouth = str[1];
        int year = Integer.parseInt(myYear);
        int month = Integer.parseInt(myMouth);
        Calendar c = Calendar.getInstance();
        c.set(year, month, 0);//设置时间

        List<String> dateList = new ArrayList<>();
        List<Integer> registerNumList = new ArrayList<>();
        int dayOfMonth = c.get(Calendar.DAY_OF_MONTH);
        for(int i = 1;i <=dayOfMonth ;i++){
            String day = ""+i;
            if(i<10){
                day = "0"+i;
            }
            MembersRegister membersRegister = reportsMapper.selectForMembersRegisterByMouth(yearAndMouth + "-" + day);
            dateList.add(membersRegister.getBywhen().substring(8));
            registerNumList.add(membersRegister.getRegisterNum());
        }

        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("msg", "查询成功");
        map.put("dateList", dateList);
        map.put("registerNumList",registerNumList);
        return map;
    }

    //营业情况分析（饼图）
    public Map<String, Object> selectForReportsAnalysisByMouth(String yearAndMouth) {
        String[] str = yearAndMouth.split("-");//以汉字为标志，分割字符串
        String myYear = str[0];
        String myMouth = str[1];
        int year = Integer.parseInt(myYear);
        int month = Integer.parseInt(myMouth);
        Calendar c = Calendar.getInstance();
        c.set(year, month, 0);//设置时间

        List<Map<String, String>> registerNumList = new ArrayList<>();
        int dayOfMonth = c.get(Calendar.DAY_OF_MONTH);
        for(int i = 1;i <=dayOfMonth ;i++){
            String day = ""+i;
            if(i<10){
                day = "0"+i;
            }
            MembersRegister membersRegister = reportsMapper.selectForMembersRegisterByMouth(yearAndMouth + "-" + day);
            Map<String,String> valueAndNameMap = new HashMap<>();

            valueAndNameMap.put("value",membersRegister.getRegisterNum().toString());
            valueAndNameMap.put("name",membersRegister.getBywhen().substring(8));
            registerNumList.add(valueAndNameMap);
        }

        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("msg", "查询成功");
        map.put("nameAndValueMap",registerNumList);
        return map;
    }
}
