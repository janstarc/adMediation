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
    private Long id_performanceData;
    private int performanceScore;

    @ManyToOne(fetch = FetchType.LAZY)
    private Country country;

    @ManyToOne(fetch = FetchType.LAZY)
    private AdType adType;

    @ManyToOne(fetch = FetchType.LAZY)
    private AdProvider adProvider;

    public PerformanceData() {
    }
}
