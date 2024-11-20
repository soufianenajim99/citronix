package com.springprojects.citronix.models;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "detailrecoltes")
public class DetailRecoltes {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
}
