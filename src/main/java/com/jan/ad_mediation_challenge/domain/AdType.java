package com.jan.ad_mediation_challenge.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Entity
public class AdType {

    @Id
    private int id_adType;
    private String descriptionType;

    @OneToMany
    private List<PerformanceData> perfData = new ArrayList<>();

    public AdType() {
    }
}
