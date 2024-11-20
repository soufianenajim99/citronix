package com.springprojects.citronix.models;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "arbres")
public class Arbre {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
}
