package com.springprojects.citronix.services.implementations;

import com.springprojects.citronix.dtos.FermeDTO;
import com.springprojects.citronix.exception.ResourceNotFoundException;
import com.springprojects.citronix.mappers.FermeMapper;
import com.springprojects.citronix.models.Ferme;
import com.springprojects.citronix.repositories.FermeRepository;
import com.springprojects.citronix.services.FermeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FermeServiceImpl implements FermeService {

    private final FermeRepository fermeRepository;
    private final FermeMapper fermeMapper;


    @Override
    public FermeDTO createFerme(FermeDTO fermeDTO) {
        Ferme ferme = fermeMapper.fermeDtoToFerme(fermeDTO);
        Ferme savedFerme = fermeRepository.save(ferme);
        return fermeMapper.fermeToFermeDto(savedFerme);
    }

    @Override
    public FermeDTO updateFerme(UUID id, FermeDTO fermeDTO) {
        Ferme ferme = fermeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ferme not found with id: " + id));
        ferme.setNom(fermeDTO.getNom());
        ferme.setLocalisation(fermeDTO.getLocalisation());
        ferme.setSuperficie(fermeDTO.getSuperficie());
        ferme.setDateCreation(fermeDTO.getDateCreation());
        Ferme updatedFerme = fermeRepository.save(ferme);
        return fermeMapper.fermeToFermeDto(updatedFerme);
    }

    @Override
    public void deleteFerme(UUID id) {
        if (!fermeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Ferme not found with id: " + id);
        }
        fermeRepository.deleteById(id);
    }

    @Override
    public FermeDTO getFermeById(UUID id) {
        Ferme ferme = fermeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ferme not found with id: " + id));
        return fermeMapper.fermeToFermeDto(ferme);
    }

    @Override
    public List<FermeDTO> getAllFermes() {
        return fermeRepository.findAll().stream()
                .map(fermeMapper::fermeToFermeDto)
                .collect(Collectors.toList());
    }
}
