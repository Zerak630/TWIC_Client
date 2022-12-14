package com.twic.model;

import lombok.Data;

@Data
public class VilleModel {

    private Long id;
    private String codeCommune;
    private String nomCommune;
    private String codePostal;
    private String libelleAcheminement;
    private String ligne;
    private String longitude;
    private String latitude;
}