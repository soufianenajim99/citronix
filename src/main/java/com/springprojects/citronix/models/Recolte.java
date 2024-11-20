package com.springprojects.citronix.models;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "recoltes")
public class Recolte {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
}
