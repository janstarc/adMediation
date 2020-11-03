package com.jan.ad_mediation_challenge.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Embeddable;

@Data
@AllArgsConstructor
@Embeddable
public class AdProvider {

    private int id_adProvider;
    private String descriptionProvider;

    public AdProvider() {
    }
}
