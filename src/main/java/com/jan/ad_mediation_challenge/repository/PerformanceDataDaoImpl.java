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
public class PerformanceDataDaoImpl implements PerformanceDataDao {

    @PersistenceContext
    private EntityManager em;

    public ResultList findSubsetRows(String platform, String osVersion, String appName, String appVersion, String countryCode){

        final JPAQuery<PerformanceData> query = new JPAQuery<>(em);
        final QPerformanceData performanceData = QPerformanceData.performanceData;

        // Filtering
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

        // Clone the initial query for each ad type
        final JPAQuery<PerformanceData> q1 = query.clone(em);
        final JPAQuery<PerformanceData> q2 = query.clone(em);
        final JPAQuery<PerformanceData> q3 = query.clone(em);

        // Execute Query for each ad type
        List<Result> adType1_List = convertList(q1.where(performanceData.adType.idAdType.eq(1)).orderBy(performanceData.performanceScore.desc()).fetch());
        List<Result> adType2_List = convertList(q2.where(performanceData.adType.idAdType.eq(2)).orderBy(performanceData.performanceScore.desc()).fetch());
        List<Result> adType3_List = convertList(q3.where(performanceData.adType.idAdType.eq(3)).orderBy(performanceData.performanceScore.desc()).fetch());

        return new ResultList(adType1_List, adType2_List, adType3_List);
    }

    // Support methods
    // Converts from PerformanceData to Result
    public List<Result> convertList(List<PerformanceData> list){
        List<Result> resultList = new ArrayList<>();
        for(PerformanceData pd : list){
            resultList.add(new Result(pd.getPerformanceScore(), pd.getAdProvider().getIdAdProvider()));
        }
        return resultList;
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
}
