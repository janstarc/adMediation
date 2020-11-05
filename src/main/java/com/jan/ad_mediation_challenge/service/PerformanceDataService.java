package com.jan.ad_mediation_challenge.service;

import com.jan.ad_mediation_challenge.domain.PerformanceData;
import com.jan.ad_mediation_challenge.repository.PerformanceRepository;
import com.jan.ad_mediation_challenge.repository.SqlDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import view.PerformanceDTO;

import java.util.List;

@Service
public class PerformanceDataService {

    //private SqlDAO sqlDAO;
    private PerformanceRepository performanceRepository;

    /*
    @Autowired
    public PerformanceDataService(SqlDAO sqlDAO){
        this.sqlDAO = sqlDAO;
    }

     */

    @Autowired
    public PerformanceDataService(PerformanceRepository performanceRepository){
        this.performanceRepository = performanceRepository;
    }

    public PerformanceDTO getPerformanceData(String platform, String osVersion, String appName, String appVersion, String countryCode) {

        return performanceRepository.findPerformanceDataByCountry_CountryCode(countryCode);
    }
}
