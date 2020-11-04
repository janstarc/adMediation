package com.jan.ad_mediation_challenge.repository;

import com.jan.ad_mediation_challenge.domain.PerformanceData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SqlDAO extends JpaRepository<PerformanceData, Long> {

    List<PerformanceData> findPerformanceDataByPerformanceScore(int performanceScore);

}
