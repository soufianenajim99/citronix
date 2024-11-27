package com.springprojects.citronix.repositories;

import com.springprojects.citronix.models.Vente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VenteRepository extends JpaRepository<Vente, Long> {
}
