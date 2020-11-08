package com.jan.ad_mediation_challenge.repository;

public class Result {

    private int performanceScore;
    private int adProviderId;

    public Result(int performanceScore, int adProviderId) {
        this.performanceScore = performanceScore;
        this.adProviderId = adProviderId;
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
}
