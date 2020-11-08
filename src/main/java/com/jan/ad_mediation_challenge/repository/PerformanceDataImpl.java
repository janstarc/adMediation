package com.jan.ad_mediation_challenge.repository;


import com.jan.ad_mediation_challenge.domain.*;
import com.jan.ad_mediation_challenge.domain.QAdProvider;
import com.jan.ad_mediation_challenge.domain.QAdType;
import com.jan.ad_mediation_challenge.domain.QCountry;
import com.jan.ad_mediation_challenge.domain.QPerformanceData;
import com.querydsl.core.group.GroupBy;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;


@Repository
public class PerformanceDataImpl implements PerformanceDataDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public PerformanceData save(PerformanceData performanceData) {
        em.persist(performanceData);
        return performanceData;
    }

    @Override
    public List<PerformanceData> findPerformanceDataByPerformanceScoreQueryDSL(int perfScore) {
        final JPAQuery<PerformanceData> query = new JPAQuery<>(em);
        final QPerformanceData performanceData = QPerformanceData.performanceData;

        return query.from(performanceData).where(performanceData.performanceScore.eq(perfScore)).fetch();
    }

    @Override
    public List<PerformanceData> findSubset(String platform, String osVersion, String appName, String appVersion, String countryCode){
        final JPAQuery<PerformanceData> query = new JPAQuery<>(em);
        final QPerformanceData performanceData = QPerformanceData.performanceData;
        //final QAdProvider adProvider = QAdProvider.adProvider;
        //final QAdType adType = QAdType.adType;
        //final QCountry country = QCountry.country;

        /*
        if (platform.equals("Android") && osVersion.charAt(0) == '9' && countryCode.equals("CN")){
            // Results without AdMob
            return sqlDAO.findNoFacebookNoAdMobByCountry_CountryCode(countryCode);
        } else if (countryCode.equals("CN")){
            // Results without FB
            return sqlDAO.findNoFacebookByCountry_CountryCode(countryCode);
        } else if (platform.equals("Android") && osVersion.charAt(0) == '9'){
            return sqlDAO.findNoAdmobByCountry_CountryCode(countryCode);
        } else {
            return sqlDAO.findByCountry_CountryCode(countryCode);
        }

         */


        query.from(performanceData);
        query.where(performanceData.country.countryCode.equalsIgnoreCase(countryCode));
        if(platform.equals("Android") && osVersion.charAt(0) == '9'){
            query.where(performanceData.adProvider.descriptionProvider.notEqualsIgnoreCase("AdMob"));
        }


        return query.fetch();
    }



}
