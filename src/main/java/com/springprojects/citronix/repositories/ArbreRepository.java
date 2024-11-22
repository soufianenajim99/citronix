package com.springprojects.citronix.repositories;

import com.springprojects.citronix.models.Arbre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ArbreRepository extends JpaRepository<Arbre, UUID> {
//    List<Arbre> findByChampId(UUID champId);

    long countByChampId(UUID champId);

//    List<Arbre> findByChampIdAndRecoltedetailsIdsIsEmpty(UUID champId);
//
//    List<Arbre> findByAgeGreaterThan(int age);
}
