package com.jan.ad_mediation_challenge.repository;

import java.util.List;

public class ResultList {

    List<Result> adType1;
    List<Result> adType2;
    List<Result> adType3;

    public ResultList(List<Result> adType1, List<Result> adType2, List<Result> adType3) {
        this.adType1 = adType1;
        this.adType2 = adType2;
        this.adType3 = adType3;
    }

    public List<Result> getAdType1() {
        return adType1;
    }

    public void setAdType1(List<Result> adType1) {
        this.adType1 = adType1;
    }

    public List<Result> getAdType2() {
        return adType2;
    }

    public void setAdType2(List<Result> adType2) {
        this.adType2 = adType2;
    }

    public List<Result> getAdType3() {
        return adType3;
    }

    public void setAdType3(List<Result> adType3) {
        this.adType3 = adType3;
    }
}
