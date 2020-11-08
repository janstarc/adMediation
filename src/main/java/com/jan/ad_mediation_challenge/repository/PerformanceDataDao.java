package com.jan.ad_mediation_challenge.repository;

import com.jan.ad_mediation_challenge.domain.PerformanceData;

import java.util.List;

public interface PerformanceDataDao {

    public ResultList findSubsetRows(String platform, String osVersion, String appName, String appVersion, String countryCode);
}
