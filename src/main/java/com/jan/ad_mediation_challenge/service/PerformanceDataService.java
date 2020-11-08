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


    @Autowired
    public PerformanceDataService(SqlDAO sqlDAO, PerformanceDataDao performanceDataDao){
        this.sqlDAO = sqlDAO;
        this.performanceDataDao = performanceDataDao;
    }

    public ResultList getTestSubsetRows(String platform, String osVersion, String appName, String appVersion, String countryCode) {

        return performanceDataDao.findSubsetRows(platform, osVersion, appName, appVersion, countryCode);
    }


}
