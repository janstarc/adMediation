package com.jan.ad_mediation_challenge.repository;


import com.jan.ad_mediation_challenge.domain.*;
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

    public boolean isAdMobPresent(String countryCode){
        final JPAQuery<PerformanceData> queryCount = new JPAQuery<>(em);
        final QPerformanceData performanceData = QPerformanceData.performanceData;

        queryCount.from(performanceData).where(performanceData.country.countryCode.equalsIgnoreCase(countryCode));
        queryCount.where(performanceData.adProvider.descriptionProvider.equalsIgnoreCase("AdMob"));
        long numOfAdMob = queryCount.fetchCount();
        //System.out.println("Num of AdMob " + numOfAdMob);

        return numOfAdMob > 0;
    }

    @Override
    public List<PerformanceData> findSubset(String platform, String osVersion, String appName, String appVersion, String countryCode){


        final JPAQuery<PerformanceData> query = new JPAQuery<>(em);
        final QPerformanceData performanceData = QPerformanceData.performanceData;

        query.from(performanceData);
        query.where(performanceData.country.countryCode.equalsIgnoreCase(countryCode));
        if(platform.equals("Android") && osVersion.charAt(0) == '9'){
            query.where(performanceData.adProvider.descriptionProvider.notEqualsIgnoreCase("AdMob").and(performanceData.adProvider.descriptionProvider.notEqualsIgnoreCase("AdMob-OptOut")));
        }
        if(countryCode.equals("CN")){
            query.where(performanceData.adProvider.descriptionProvider.notEqualsIgnoreCase("Facebook"));
        }
        if(isAdMobPresent(countryCode)){
            query.where(performanceData.adProvider.descriptionProvider.notEqualsIgnoreCase("AdMob-OptOut"));
        }

        query.orderBy(performanceData.adType.idAdType.asc());
        query.orderBy(performanceData.performanceScore.desc());

        //List<Object[]> rows = query.list(performanceData.performanceScore, performanceData.adType.idAdType, performanceData.adProvider.descriptionProvider);



        //return query.fetch();
        return query.fetch();
    }



}
