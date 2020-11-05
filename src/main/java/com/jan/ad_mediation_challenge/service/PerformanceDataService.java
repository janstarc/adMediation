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

        return sqlDAO.findPerformanceDataByCountry_CountryCode(countryCode);
    }
}
