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

        UUID fermeId = UUID.fromString(champDTO.getFermeId());
        Ferme ferme = fermeRepository.findById(fermeId)
                .orElseThrow(() -> new ResourceNotFoundException("Ferme introuvable avec l'ID : " + fermeId));

        double superficieTotaleChamps = champRepository.findByFermeId(fermeId).stream()
                .mapToDouble(Champ::getSuperficie)
                .sum() + champDTO.getSuperficie();
        if (superficieTotaleChamps >= ferme.getSuperficie()) {
            throw new BusinessException("La superficie totale des champs dépasse celle de la ferme.");
        }

        // Vérifier le nombre maximal de champs
        long nombreDeChamps = champRepository.countByFermeId(fermeId);
        if (nombreDeChamps >= 10) {
            throw new BusinessException("Une ferme ne peut pas contenir plus de 10 champs.");
        }

        Champ champ = champMapper.champDtoToChamp(champDTO);
        champ.setFerme(ferme); // Associer la ferme
        Champ savedChamp = champRepository.save(champ);
        return champMapper.champToChampDto(savedChamp);
    }

    @Override
    public ChampDTO updateChamp(UUID id, ChampDTO champDTO) {
        // Trouver le champ existant
        Champ champ = champRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Champ introuvable avec l'ID : " + id));

        // Vérifier les nouvelles contraintes si la superficie change
        double nouvelleSuperficieTotale = champRepository.findByFermeId(champ.getFerme().getId()).stream()
                .filter(existingChamp -> !existingChamp.getId().equals(id)) // Exclure le champ actuel
                .mapToDouble(Champ::getSuperficie)
                .sum() + champDTO.getSuperficie();
        if (nouvelleSuperficieTotale >= champ.getFerme().getSuperficie()) {
            throw new BusinessException("La nouvelle superficie totale des champs dépasse celle de la ferme.");
        }

        // Mettre à jour les champs du champ existant
        champ.setNom(champDTO.getNom());
        champ.setSuperficie(champDTO.getSuperficie());
        Champ updatedChamp = champRepository.save(champ);
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
