package com.springprojects.citronix.models;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "champs")
public class Champ {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
}
