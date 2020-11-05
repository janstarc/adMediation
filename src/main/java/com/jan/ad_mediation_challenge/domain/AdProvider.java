package com.jan.ad_mediation_challenge.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Entity
public class AdProvider {

    @Id
    private int idAdProvider;
    private String descriptionProvider;

    public AdProvider() {
    }

    public int getIdAdProvider() {
        return idAdProvider;
    }

    public String getDescriptionProvider() {
        return descriptionProvider;
    }
}
