package com.springprojects.citronix.services.implementations;

import com.springprojects.citronix.dtos.ChampDTO;
import com.springprojects.citronix.exception.BusinessException;
import com.springprojects.citronix.exception.ResourceNotFoundException;
import com.springprojects.citronix.mappers.ChampMapper;
import com.springprojects.citronix.models.Champ;
import com.springprojects.citronix.models.Ferme;
import com.springprojects.citronix.repositories.ChampRepository;
import com.springprojects.citronix.repositories.FermeRepository;
import com.springprojects.citronix.services.ChampService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChampServiceImpl implements ChampService {
    private final ChampRepository champRepository;
    private final ChampMapper champMapper;
    private final FermeRepository fermeRepository;

    @Override
    public ChampDTO createChamp(ChampDTO champDTO) {
        // Validate and fetch the associated Ferme
        UUID fermeId = champDTO.getFerme().getId(); // Ferme ID now comes from ChampDTO's Ferme object
        Ferme ferme = fermeRepository.findById(fermeId)
                .orElseThrow(() -> new ResourceNotFoundException("Ferme introuvable avec l'ID : " + fermeId));

        // Calculate total superficie and validate against the Ferme's superficie
        double superficieTotaleChamps = champRepository.findByFermeId(fermeId).stream()
                .mapToDouble(Champ::getSuperficie)
                .sum() + champDTO.getSuperficie();
        if (superficieTotaleChamps >= ferme.getSuperficie()) {
            throw new BusinessException("La superficie totale des champs dépasse celle de la ferme.");
        }

        // Validate the maximum number of Champs allowed in the Ferme
        long nombreDeChamps = champRepository.countByFermeId(fermeId);
        if (nombreDeChamps >= 10) {
            throw new BusinessException("Une ferme ne peut pas contenir plus de 10 champs.");
        }

        // Build a new ChampDTO with the associated Ferme
        ChampDTO updatedChampDTO = champDTO.withFermeId(fermeId.toString());

        // Map to Champ entity and save
        Champ champ = champMapper.champDtoToChamp(updatedChampDTO);
        champ.setFerme(ferme); // Explicitly set the Ferme
        Champ savedChamp = champRepository.save(champ);

        return champMapper.champToChampDto(savedChamp);
    }

    @Override
    public ChampDTO updateChamp(UUID id, ChampDTO champDTO) {
        // Fetch the existing Champ
        Champ existingChamp = champRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Champ introuvable avec l'ID : " + id));

        // Validate new superficie constraints if it changes
        double nouvelleSuperficieTotale = champRepository.findByFermeId(existingChamp.getFerme().getId()).stream()
                .filter(champ -> !champ.getId().equals(id)) // Exclude the current Champ
                .mapToDouble(Champ::getSuperficie)
                .sum() + champDTO.getSuperficie();
        if (nouvelleSuperficieTotale >= existingChamp.getFerme().getSuperficie()) {
            throw new BusinessException("La nouvelle superficie totale des champs dépasse celle de la ferme.");
        }

        // Update Champ fields
        existingChamp.setNom(champDTO.getNom());
        existingChamp.setSuperficie(champDTO.getSuperficie());

        // Save the updated Champ and return the DTO
        Champ updatedChamp = champRepository.save(existingChamp);
        return champMapper.champToChampDto(updatedChamp);
    }

    @Override
    public void deleteChamp(UUID id) {
        if (!champRepository.existsById(id)) {
            throw new ResourceNotFoundException("Champ introuvable avec l'ID : " + id);
        }
        champRepository.deleteById(id);
    }

    @Override
    public ChampDTO getChampById(UUID id) {
        Champ champ = champRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Champ introuvable avec l'ID : " + id));
        return champMapper.champToChampDto(champ);
    }

    @Override
    public List<ChampDTO> getAllChamps() {
        return champRepository.findAll().stream()
                .map(champMapper::champToChampDto)
                .collect(Collectors.toList());
    }
}
