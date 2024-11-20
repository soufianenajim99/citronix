package com.springprojects.citronix.models;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
}
