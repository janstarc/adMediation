package com.jan.ad_mediation_challenge.service;

import com.jan.ad_mediation_challenge.domain.PerformanceData;
import com.jan.ad_mediation_challenge.repository.PerformanceDataSubset;
import com.jan.ad_mediation_challenge.repository.SqlDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerformanceDataService {

    private SqlDAO sqlDAO;
    //private PerformanceDataSubset performanceDataSubset;


    @Autowired
    public PerformanceDataService(SqlDAO sqlDAO){
        this.sqlDAO = sqlDAO;
    }

    /*
    @Autowired
    public PerformanceDataSubset(PerformanceDataSubset performanceDataSubset){
        this.performanceDataSubset = performanceDataSubset;
    }
    */

    // TODO
    Logger logger = LoggerFactory.getLogger(UpdateService.class);

    public List<PerformanceData> getPerformanceData(String platform, String osVersion, String appName, String appVersion, String countryCode) {
        logger.info("################# " + countryCode);
        return sqlDAO.findPerformanceDataByCountry_CountryCode(countryCode);
    }

    public List<PerformanceDataSubset> getPerformanceDataSubset(String platform, String osVersion, String appName, String appVersion, String countryCode) {
        logger.info("################# " + countryCode);
        return sqlDAO.findByCountry_CountryCodeOrderByAdTypeAscPerformanceScoreDesc(countryCode);
    }
}
