package com.springprojects.citronix.services.implementations;

import com.springprojects.citronix.dtos.FermeDTO;
import com.springprojects.citronix.exception.CustomNotFoundException;
import com.springprojects.citronix.mappers.FermeMapper;
import com.springprojects.citronix.models.Ferme;
import com.springprojects.citronix.repositories.FermeRepository;
import com.springprojects.citronix.services.FermeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FermeServiceImpl implements FermeService {

    @Autowired
    private FermeRepository fermeRepository;

    @Transactional
    @Override
    public FermeDTO createFerme(FermeDTO fermeDTO) {
        Ferme ferme = FermeMapper.INSTANCE.toEntity(fermeDTO);
        Ferme savedFerme = fermeRepository.save(ferme);
        return FermeMapper.INSTANCE.toDTO(savedFerme);
    }

    @Override
    public List<FermeDTO> getAllFermes() {
        return fermeRepository.findAll().stream()
                .map(FermeMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public FermeDTO getFermeById(Long id) {
        Ferme ferme = fermeRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("Ferme non trouvée avec l'ID: " + id));
        return FermeMapper.INSTANCE.toDTO(ferme);
    }

    @Transactional
    @Override
    public FermeDTO updateFerme(Long id, FermeDTO fermeDTO) {
        Ferme ferme = fermeRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("Ferme non trouvée avec l'ID: " + id));

        ferme.setNom(fermeDTO.nom());
        ferme.setLocalisation(fermeDTO.localisation());
        ferme.setSuperficie(fermeDTO.superficie());
        ferme.setDateDeCreation(fermeDTO.dateDeCreation());

        Ferme updatedFerme = fermeRepository.save(ferme);
        return FermeMapper.INSTANCE.toDTO(updatedFerme);
    }

    @Transactional
    @Override
    public void deleteFerme(Long id) {
        if (!fermeRepository.existsById(id)) {
            throw new CustomNotFoundException("Ferme non trouvée avec l'ID: " + id);
        }
        fermeRepository.deleteById(id);
    }
}
