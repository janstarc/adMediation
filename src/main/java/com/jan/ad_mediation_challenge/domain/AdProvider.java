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
    private int id_adProvider;
    private String descriptionProvider;

    public AdProvider() {
    }
}
