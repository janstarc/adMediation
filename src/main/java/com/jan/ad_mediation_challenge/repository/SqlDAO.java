package com.jan.ad_mediation_challenge.repository;

import com.jan.ad_mediation_challenge.domain.PerformanceData;
import com.joutvhu.dynamic.jpa.DynamicQuery;
import com.joutvhu.dynamic.jpa.support.DynamicJpaRepositoryFactoryBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.List;

//@EnableJpaRepositories(repositoryFactoryBeanClass = DynamicJpaRepositoryFactoryBean.class)

public interface SqlDAO extends JpaRepository<PerformanceData, Long> {

}
