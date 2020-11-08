package com.jan.ad_mediation_challenge.repository;

public class Result {

    private int performanceScore;
    private int adProviderId;
    private int adTypeId;

    public Result(int performanceScore, int adProviderId, int adTypeId) {
        this.performanceScore = performanceScore;
        this.adProviderId = adProviderId;
        this.adTypeId = adTypeId;
    }

    public int getPerformanceScore() {
        return performanceScore;
    }

    public void setPerformanceScore(int performanceScore) {
        this.performanceScore = performanceScore;
    }

    public int getAdProviderId() {
        return adProviderId;
    }

    public void setAdProviderId(int adProviderId) {
        this.adProviderId = adProviderId;
    }

    public int getAdTypeId() {
        return adTypeId;
    }

    public void setAdTypeId(int adTypeId) {
        this.adTypeId = adTypeId;
    }
}
