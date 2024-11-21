package com.springprojects.citronix.repositories;

import com.springprojects.citronix.models.Recolte;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RecolteRepository extends JpaRepository<Recolte, UUID> {
}
