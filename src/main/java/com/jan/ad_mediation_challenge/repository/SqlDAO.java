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



    // TODO NOTE! When working with dynamic queries the number of parameters in the JPA query method name isn't irrelevant!
    /*
    @DynamicQuery(
            value = "select p from PerformanceData p where p.performanceScore > :performanceScore \n" +
                    "<#if countryCode?has_content> \n" +
                    " and p.country.countryCode = :countryCode \n" +
                    "</#if>"
    )
    List<PerformanceData> findPerformanceDataByPerformanceScoreAndCountry_CountryCode(int performanceScore, String countryCode);


     */
    List<PerformanceData> findPerformanceDataByPerformanceScoreAndCountry_CountryCode(int performanceScore, String countryCode);

    /*
    @Query(
            value = "select p from PerformanceData p where p.performanceScore > :testNum order by p.performanceScore asc"
    )
    List<PerformanceData> findByPerformanceScore(int testNum);
    */

    @Query(
            value = "select p from PerformanceData p " +
                    "where p.country.countryCode = :countryCode " +
                    "order by p.adType.idAdType asc, p.performanceScore desc"
    )
    List<PerformanceDataSubset> findByCountry_CountryCode(String countryCode);

    @Query(
            value = "select p from PerformanceData p " +
                    "where p.country.countryCode = :countryCode " +
                    "and p.adProvider.descriptionProvider <> 'AdMob' " +
                    "order by p.adType.idAdType asc, p.performanceScore desc"
    )
    List<PerformanceDataSubset> findNoAdmobByCountry_CountryCode(String countryCode);

    @Query(
            value = "select p from PerformanceData p " +
                    "where p.country.countryCode = :countryCode " +
                    "and p.adProvider.descriptionProvider <> 'Facebook' " +
                    "order by p.adType.idAdType asc, p.performanceScore desc"
    )
    List<PerformanceDataSubset> findNoFacebookByCountry_CountryCode(String countryCode);

    @Query(
            value = "select p from PerformanceData p " +
                    "where p.country.countryCode = :countryCode " +
                    "and p.adProvider.descriptionProvider <> 'Facebook' " +
                    "and p.adProvider.descriptionProvider <> 'AdMob' " +
                    "order by p.adType.idAdType asc, p.performanceScore desc"
    )
    List<PerformanceDataSubset> findNoFacebookNoAdMobByCountry_CountryCode(String countryCode);

    /*
    String test = "test";
    @Query(
            value = test
    )
    List<PerformanceDataSubset> findTestByCountry_CountryCodeNoFacebook(String countryCode);
    */

}
