package com.springprojects.citronix.repositories;

import com.springprojects.citronix.models.Ferme;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FermeRepository extends JpaRepository<Ferme, UUID> {
}
