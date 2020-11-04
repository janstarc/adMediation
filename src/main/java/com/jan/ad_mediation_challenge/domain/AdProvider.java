package com.jan.ad_mediation_challenge.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Data
@AllArgsConstructor
@Entity
public class AdProvider {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_adProvider;
    private String descriptionProvider;

    public AdProvider() {
    }
}
