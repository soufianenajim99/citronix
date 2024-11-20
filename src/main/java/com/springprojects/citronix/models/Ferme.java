package com.springprojects.citronix.models;

import jakarta.persistence.*;


import java.util.UUID;

@Entity
@Table(name = "fermes")
public class Ferme {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String username;
    private String password;
    private String loftName;
    private double finalScore;
}
