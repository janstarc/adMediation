package com.jan.ad_mediation_challenge.repository;


import com.jan.ad_mediation_challenge.domain.*;
import com.jan.ad_mediation_challenge.domain.QPerformanceData;
import com.querydsl.core.group.GroupBy;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
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

    public List<Result> convertList(List<PerformanceData> list){
        List<Result> resultList = new ArrayList<>();

        for(PerformanceData pd : list){
            resultList.add(new Result(pd.getPerformanceScore(), pd.getAdProvider().getIdAdProvider()));
        }

        return resultList;
    }


    public ResultList findSubsetRows(String platform, String osVersion, String appName, String appVersion, String countryCode){

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

        final JPAQuery<PerformanceData> q1 = query.clone(em);
        final JPAQuery<PerformanceData> q2 = query.clone(em);
        final JPAQuery<PerformanceData> q3 = query.clone(em);

        //query.orderBy(performanceData.adType.idAdType.asc());
        List<Result> adType1_List = convertList(q1.where(performanceData.adType.idAdType.eq(1)).orderBy(performanceData.performanceScore.desc()).fetch());
        List<Result> adType2_List = convertList(q2.where(performanceData.adType.idAdType.eq(2)).orderBy(performanceData.performanceScore.desc()).fetch());
        List<Result> adType3_List = convertList(q3.where(performanceData.adType.idAdType.eq(3)).orderBy(performanceData.performanceScore.desc()).fetch());


        //query.orderBy(performanceData.performanceScore.desc());

        //List<Object[]> rows = query.list(performanceData.performanceScore, performanceData.adType.idAdType, performanceData.adProvider.descriptionProvider);

        //return query.fetch();
        //List<PerformanceData> fullResult = query.fetch();

        return new ResultList(adType1_List, adType2_List, adType3_List);
    }
}
