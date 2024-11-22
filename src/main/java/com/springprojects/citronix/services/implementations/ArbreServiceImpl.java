package com.springprojects.citronix.services.implementations;

import com.springprojects.citronix.dtos.ArbreDTO;
import com.springprojects.citronix.exception.BusinessException;
import com.springprojects.citronix.exception.ResourceNotFoundException;
import com.springprojects.citronix.mappers.ArbreMapper;
import com.springprojects.citronix.models.Arbre;
import com.springprojects.citronix.models.Champ;
import com.springprojects.citronix.repositories.ArbreRepository;
import com.springprojects.citronix.repositories.ChampRepository;
import com.springprojects.citronix.repositories.RecolteRepository;
import com.springprojects.citronix.services.ArbreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArbreServiceImpl implements ArbreService {
    private final ArbreRepository arbreRepository;
    private final ChampRepository champRepository;
    private final RecolteRepository recolteRepository;  // Assuming you have this repository
    private final ArbreMapper arbreMapper;  // Assuming you have a mapper for ArbreDTO to Arbre

    @Override
    public ArbreDTO createArbre(ArbreDTO arbreDTO) {
        // Fetch the Champ by ID to validate the field
        UUID champId = UUID.fromString(arbreDTO.getChamp().getId().toString());
        Champ champ = champRepository.findById(champId)
                .orElseThrow(() -> new ResourceNotFoundException("Champ introuvable avec l'ID : " + champId));

        // Validate the tree planting period (March to May)
        LocalDate dateCreation = arbreDTO.getDateCreation();
        if (dateCreation.getMonthValue() < 3 || dateCreation.getMonthValue() > 5) {
            throw new BusinessException("La période de plantation des arbres est limitée entre mars et mai.");
        }

        // Validate spacing between trees (100 trees per hectare = 10 trees per 1000m²)
        long currentTreeCount = arbreRepository.countByChampId(champ.getId());
        if (currentTreeCount >= 100 * champ.getSuperficie()) {
            throw new BusinessException("Le champ dépasse la densité maximale d'arbres.");
        }

        // Use the withChampId method to update the ChampDTO
        ArbreDTO updatedArbreDTO = arbreDTO.withChampId(champ.getId().toString());

        // Create the tree
        Arbre arbre = arbreMapper.arbreDtoToArbre(updatedArbreDTO);
        arbre.setChamp(champ);  // Associate the Champ with the tree
        Arbre savedArbre = arbreRepository.save(arbre);

        return arbreMapper.arbreToArbreDto(savedArbre);
    }

    @Override
    public ArbreDTO updateArbre(UUID id, ArbreDTO arbreDTO) {
        Arbre arbre = arbreRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Arbre introuvable avec l'ID : " + id));

        // Ensure the tree is not older than 20 years
        if (arbreDTO.getAge() > 20) {
            throw new BusinessException("Un arbre ne peut être productif au-delà de 20 ans.");
        }

        // If the Champ is updated, fetch and validate the new Champ
        if (arbreDTO.getChamp() != null && arbreDTO.getChamp().getId() != null) {
            UUID champId = UUID.fromString(arbreDTO.getChamp().getId().toString());
            Champ champ = champRepository.findById(champId)
                    .orElseThrow(() -> new ResourceNotFoundException("Champ introuvable avec l'ID : " + champId));
            arbre.setChamp(champ);
        }

        // Update tree fields
        arbre.setDateCreation(arbreDTO.getDateCreation());
        arbre.setAge(arbreDTO.getAge());

        Arbre updatedArbre = arbreRepository.save(arbre);
        return arbreMapper.arbreToArbreDto(updatedArbre);
    }

    @Override
    public void deleteArbre(UUID id) {
        if (!arbreRepository.existsById(id)) {
            throw new ResourceNotFoundException("Arbre introuvable avec l'ID : " + id);
        }
        arbreRepository.deleteById(id);
    }

    @Override
    public ArbreDTO getArbreById(UUID id) {
        Arbre arbre = arbreRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Arbre introuvable avec l'ID : " + id));
        return arbreMapper.arbreToArbreDto(arbre);
    }

    @Override
    public List<ArbreDTO> getAllArbres() {
        return arbreRepository.findAll().stream()
                .map(arbreMapper::arbreToArbreDto)
                .collect(Collectors.toList());
    }
}

