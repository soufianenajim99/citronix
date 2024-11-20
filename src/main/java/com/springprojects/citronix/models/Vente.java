package com.springprojects.citronix.models;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "ventes")
public class Vente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
}
