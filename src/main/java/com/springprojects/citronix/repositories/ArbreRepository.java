package com.springprojects.citronix.repositories;

import com.springprojects.citronix.models.Arbre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ArbreRepository extends JpaRepository<Arbre, Long> {
    long countByChampId(Long idChamp);

    List<Arbre> findByChampId(Long idChamp);

}
