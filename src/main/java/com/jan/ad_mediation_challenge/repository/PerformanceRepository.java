package com.jan.ad_mediation_challenge.repository;

import com.jan.ad_mediation_challenge.domain.PerformanceData;
import org.springframework.data.repository.Repository;
import view.PerformanceDTO;

public interface PerformanceRepository extends Repository<PerformanceData, Long> {

    PerformanceDTO findPerformanceDataByCountry_CountryCode(String countryCode);
}
