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

    public List<PerformanceData> getPerformanceData(String platform, String osVersion, String appName, String appVersion, String countryCode, int testNum) {
        logger.info("################# " + countryCode);
        return sqlDAO.findPerformanceDataByPerformanceScoreAndCountry_CountryCode(testNum, "");
    }

    public List<PerformanceDataSubset> getPerformanceDataSubset(String platform, String osVersion, String appName, String appVersion, String countryCode, int testNum) {

        if (platform.equals("Android") && osVersion.charAt(0) == '9'){
            // Results without AdMob
            return sqlDAO.findByCountry_CountryCodeNoAdmob(countryCode);
        } else if (countryCode.equals("CN")){
            // Results without FB
            return sqlDAO.findByCountry_CountryCodeNoFacebook(countryCode);
        }
        // TODO Can query be a variable?

        return sqlDAO.findByCountry_CountryCode(countryCode);
    }


}
