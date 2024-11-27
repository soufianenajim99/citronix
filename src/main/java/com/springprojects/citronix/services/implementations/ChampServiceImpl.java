package com.springprojects.citronix.services.implementations;

import com.springprojects.citronix.dtos.ChampDTO;
import com.springprojects.citronix.exception.CustomNotFoundException;
import com.springprojects.citronix.exception.ValidationException;
import com.springprojects.citronix.mappers.ChampMapper;
import com.springprojects.citronix.models.Champ;
import com.springprojects.citronix.models.Ferme;
import com.springprojects.citronix.repositories.ChampRepository;
import com.springprojects.citronix.repositories.FermeRepository;
import com.springprojects.citronix.services.ChampService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChampServiceImpl implements ChampService {

    @Autowired
    private ChampRepository champRepository;

    @Autowired
    private FermeRepository fermeRepository;

    @Transactional
    @Override
    public ChampDTO createChamp(ChampDTO champDTO) {
        Ferme ferme = fermeRepository.findById(champDTO.fermeId())
                .orElseThrow(() -> new CustomNotFoundException("Ferme non trouvée avec l'ID: " + champDTO.fermeId()));

        validateChampConstraints(ferme, champDTO);
        Champ champ = ChampMapper.INSTANCE.toEntity(champDTO);
        champ.setFerme(ferme);
        Champ savedChamp = champRepository.save(champ);
        return ChampMapper.INSTANCE.toDTO(savedChamp);
    }

    @Override
    public List<ChampDTO> getAllChamps() {
        return champRepository.findAll().stream()
                .map(ChampMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ChampDTO getChampById(Long id) {
        Champ champ = champRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("Champ non trouvé avec l'ID: " + id));
        return ChampMapper.INSTANCE.toDTO(champ);
    }

    @Transactional
    @Override
    public ChampDTO updateChamp(Long id, ChampDTO champDTO) {
        Champ champ = champRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("Champ non trouvé avec l'ID: " + id));

        Ferme ferme = fermeRepository.findById(champDTO.fermeId())
                .orElseThrow(() -> new CustomNotFoundException("Ferme non trouvée avec l'ID: " + champDTO.fermeId()));

        validateChampConstraints(ferme, champDTO);
        champ.setNom(champDTO.nom());
        champ.setSuperficie(champDTO.superficie());
        champ.setFerme(ferme);

        Champ updatedChamp = champRepository.save(champ);
        return ChampMapper.INSTANCE.toDTO(updatedChamp);
    }

    @Transactional
    @Override
    public void deleteChamp(Long id) {
        Champ champ = champRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("Champ non trouvé avec l'ID: " + id));
        champRepository.delete(champ);
    }

    private void validateChampConstraints(Ferme ferme, ChampDTO champDTO) {
        if (champDTO.superficie() < 0.1) {
            throw new ValidationException("La surface du champ doit être d'au moins 0,1 hectare.");
        }

        double maxFieldSurface = ferme.getSuperficie() * 0.5;
        if (champDTO.superficie() > maxFieldSurface) {
            throw new ValidationException("La surface du champ ne peut pas dépasser 50% de la surface totale de la ferme.");
        }

        long fieldCount = champRepository.countByFermeId(ferme.getId());
        if (fieldCount >= 10) {
            throw new ValidationException("Une ferme ne peut pas contenir plus de 10 champs.");
        }
    }
}

