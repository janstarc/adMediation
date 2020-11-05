package com.jan.ad_mediation_challenge.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnore;
import net.bytebuddy.implementation.bind.annotation.IgnoreForBinding;
import com.jan.ad_mediation_challenge.domain.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@Entity
public class PerformanceData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@Column(name = "idPerformanceData", nullable = false, updatable = false)
    private Long idPerformanceData;
    private int performanceScore;

    @ManyToOne(fetch = FetchType.EAGER, optional = false, cascade = CascadeType.ALL)
    private Country country;

    @ManyToOne(fetch = FetchType.EAGER, optional = false, cascade = CascadeType.ALL)
    private AdType adType;

    @ManyToOne(fetch = FetchType.EAGER, optional = false, cascade = CascadeType.ALL)
    private AdProvider adProvider;

    public PerformanceData() {
    }

    public Long getIdPerformanceData() {
        return idPerformanceData;
    }

    public int getPerformanceScore() {
        return performanceScore;
    }

    public Country getCountry() {
        return country;
    }

    public AdType getAdType() {
        return adType;
    }

    public AdProvider getAdProvider() {
        return adProvider;
    }
}
