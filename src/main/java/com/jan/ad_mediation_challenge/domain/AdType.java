package com.jan.ad_mediation_challenge.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Embeddable;

@Data
@AllArgsConstructor
@Embeddable
public class AdType {

    private int id_adType;
    private String descriptionType;

    public AdType() {
    }
}
