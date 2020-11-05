package com.jan.ad_mediation_challenge.repository;

import com.jan.ad_mediation_challenge.domain.PerformanceData;
import com.jan.ad_mediation_challenge.service.UpdateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SqlDAO extends JpaRepository<PerformanceData, Long> {

    //List<PerformanceData> findPerformanceDataByPerformanceScore(int performanceScore);


    List<PerformanceData> findPerformanceDataByCountry_CountryCode(String countryCode);

    List<PerformanceDataSubset> findByCountry_CountryCodeOrderByAdTypeAscPerformanceScoreDesc(String countryCode);

}
