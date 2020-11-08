package com.jan.ad_mediation_challenge.service;

import com.jan.ad_mediation_challenge.domain.PerformanceData;
import com.jan.ad_mediation_challenge.repository.*;
import com.querydsl.jpa.impl.JPAQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class PerformanceDataService {

    private SqlDAO sqlDAO;
    private PerformanceDataDao performanceDataDao;
    //private PerformanceDataSubset performanceDataSubset;


    @Autowired
    public PerformanceDataService(SqlDAO sqlDAO, PerformanceDataDao performanceDataDao){
        this.sqlDAO = sqlDAO;
        this.performanceDataDao = performanceDataDao;
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
        return sqlDAO.findPerformanceDataByPerformanceScoreAndCountry_CountryCode(testNum, countryCode);
    }

    public List<PerformanceDataSubset> getPerformanceDataSubset(String platform, String osVersion, String appName, String appVersion, String countryCode, int testNum) {

        if (platform.equals("Android") && osVersion.charAt(0) == '9' && countryCode.equals("CN")){
            // Results without AdMob
            return sqlDAO.findNoFacebookNoAdMobByCountry_CountryCode(countryCode);
        } else if (countryCode.equals("CN")){
            // Results without FB
            return sqlDAO.findNoFacebookByCountry_CountryCode(countryCode);
        } else if (platform.equals("Android") && osVersion.charAt(0) == '9'){
            return sqlDAO.findNoAdmobByCountry_CountryCode(countryCode);
        } else {
            return sqlDAO.findByCountry_CountryCode(countryCode);
        }


    }

    public List<PerformanceData> getTest(String platform, String osVersion, String appName, String appVersion, String countryCode, int testNum) {

        return performanceDataDao.findPerformanceDataByPerformanceScoreQueryDSL(testNum);
    }

    public List<PerformanceData> getTestSubset(String platform, String osVersion, String appName, String appVersion, String countryCode) {

        return performanceDataDao.findSubset(platform, osVersion, appName, appVersion, countryCode);
    }

    public List<Result> getTestSubsetRows(String platform, String osVersion, String appName, String appVersion, String countryCode) {

        return performanceDataDao.findSubsetRows(platform, osVersion, appName, appVersion, countryCode);
    }


}
