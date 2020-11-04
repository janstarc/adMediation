package com.jan.ad_mediation_challenge.service;

import com.jan.ad_mediation_challenge.domain.PerformanceData;
import com.jan.ad_mediation_challenge.repository.SqlDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerformanceDataService {

    private SqlDAO sqlDAO;

    @Autowired
    public PerformanceDataService(SqlDAO sqlDAO){
        this.sqlDAO = sqlDAO;
    }

    public List<PerformanceData> getPerformanceData(String platform, String osVersion, String appName, String appVersion, String countryCode) {

        List<PerformanceData> res = sqlDAO.findPerformanceDataByPerformanceScore(22);
        System.out.println(res.get(0).getPerformanceScore());
        System.out.println(res.get(0).getId_performanceData());
        System.out.println(res.get(0).getCountry().getCountryCode());
        System.out.println(res.get(0).getCountry().getCountryName());
        System.out.println(res.get(0).getAdType().getDescriptionType());
        System.out.println(res.get(0).getAdType().getId_adType());
        System.out.println(res.get(0).getAdProvider().getDescriptionProvider());
        System.out.println(res.get(0).getAdProvider().getId_adProvider());

        //return null;
        return sqlDAO.findPerformanceDataByPerformanceScore(22);
    }
}
