package com.springprojects.citronix.repositories;

import com.springprojects.citronix.models.DetailRecoltes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DetailRecoltesRepository extends JpaRepository<DetailRecoltes, UUID> {
}
