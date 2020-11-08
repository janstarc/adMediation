package com.jan.ad_mediation_challenge.repository;

import com.jan.ad_mediation_challenge.domain.PerformanceData;

import java.util.List;

public interface PerformanceDataDao {

    public PerformanceData save(PerformanceData performanceData);

    public List<PerformanceData> findPerformanceDataByPerformanceScoreQueryDSL(int perfScore);
    public List<PerformanceData> findSubset(String platform, String osVersion, String appName, String appVersion, String countryCode);
    public List<Result> findSubsetRows(String platform, String osVersion, String appName, String appVersion, String countryCode);
}
