package com.jan.ad_mediation_challenge.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Data
@AllArgsConstructor
@Entity
public class AdType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_adType;
    private String descriptionType;

    public AdType() {
    }
}
