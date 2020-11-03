package com.jan.ad_mediation_challenge.repository;

import com.jan.ad_mediation_challenge.domain.PerformanceData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SqlDAO extends JpaRepository<PerformanceData, Long> {

}
