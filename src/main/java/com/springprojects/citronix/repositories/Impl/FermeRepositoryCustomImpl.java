package com.springprojects.citronix.repositories.Impl;

import com.springprojects.citronix.dtos.CritereRechercheFerme;
import com.springprojects.citronix.models.Ferme;
import com.springprojects.citronix.repositories.FermeRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FermeRepositoryCustomImpl implements FermeRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<Ferme> rechercherFerme(CritereRechercheFerme fermeSearchCriteria) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Ferme> query = cb.createQuery(Ferme.class);
        Root<Ferme> farm = query.from(Ferme.class);

        List<Predicate> predicates = new ArrayList<>();

        if (fermeSearchCriteria.nom() != null && !fermeSearchCriteria.nom().isBlank()) {
            predicates.add(cb.like(cb.lower(farm.get("nom()")), "%" + fermeSearchCriteria.nom().toLowerCase() + "%"));
        }

        if (fermeSearchCriteria.localisation() != null && !fermeSearchCriteria.localisation().isBlank()) {
            predicates.add(cb.like(cb.lower(farm.get("localisation()")),
                    "%" + fermeSearchCriteria.localisation().toLowerCase() + "%"));
        }

        if (fermeSearchCriteria.surfaceMin() != null) {
            predicates.add(cb.greaterThanOrEqualTo(farm.get("surface"), fermeSearchCriteria.surfaceMin()));
        }

        if (fermeSearchCriteria.surfaceMax() != null) {
            predicates.add(cb.lessThanOrEqualTo(farm.get("surface"), fermeSearchCriteria.surfaceMax()));
        }

        if (fermeSearchCriteria.creeAvant() != null) {
            predicates.add(cb.lessThanOrEqualTo(farm.get("creationDate"), fermeSearchCriteria.creeAvant()));
        }

        if (fermeSearchCriteria.creeApres() != null) {
            predicates.add(cb.greaterThanOrEqualTo(farm.get("creationDate"), fermeSearchCriteria.creeApres()));
        }

        query.where(cb.and(predicates.toArray(new Predicate[0])));

        return entityManager.createQuery(query).getResultList();

    }
}
