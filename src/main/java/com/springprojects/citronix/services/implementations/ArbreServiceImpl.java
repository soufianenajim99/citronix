package com.springprojects.citronix.services.implementations;

import com.springprojects.citronix.dtos.ArbreDTO;
import com.springprojects.citronix.exception.CustomNotFoundException;
import com.springprojects.citronix.exception.ValidationException;
import com.springprojects.citronix.mappers.ArbreMapper;
import com.springprojects.citronix.models.Arbre;
import com.springprojects.citronix.models.Champ;
import com.springprojects.citronix.repositories.ArbreRepository;
import com.springprojects.citronix.repositories.ChampRepository;
import com.springprojects.citronix.services.ArbreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;


@Service
public class ArbreServiceImpl implements ArbreService {

    @Autowired
    private ArbreRepository arbreRepository;
    @Autowired
    private ChampRepository champRepository;

    @Transactional
    @Override
    public ArbreDTO createArbre(ArbreDTO arbreDTO) {
        Champ champ = champRepository.findById(Long.valueOf(arbreDTO.champId()))
                .orElseThrow(() -> new CustomNotFoundException("Field not found with ID: " + arbreDTO.champId()));

        validateTreeSpacing(champ);
        Arbre arbre = ArbreMapper.INSTANCE.toEntity(arbreDTO);
        arbre.setChamp(champ);
        arbre.setAge(calculateAge(arbre.getDateDePlantation()));

        Arbre savedArbre = arbreRepository.save(arbre);
        return ArbreMapper.INSTANCE.toDTO(savedArbre);
    }

    private void validateTreeSpacing(Champ champ) {
        int maxTrees = (int) (champ.getSuperficie() * 100);

        long currentTreeCount = arbreRepository.countByChampId(champ.getId());

        if (currentTreeCount >= maxTrees) {
            throw new ValidationException("The field exceeds the maximum tree density of 100 trees per hectare.");
        }
    }

    private int calculateAge(LocalDate plantingDate) {
        return Period.between(plantingDate, LocalDate.now()).getYears();
    }

    @Override
    public List<ArbreDTO> getAllArbres() {
        return arbreRepository.findAll().stream()
                .map(ArbreMapper.INSTANCE::toDTO)
                .toList();
    }

    @Override
    public ArbreDTO getArbreById(Long id) {
        Arbre arbre = arbreRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("Tree not found with ID: " + id));
        return ArbreMapper.INSTANCE.toDTO(arbre);
    }

    @Transactional
    @Override
    public ArbreDTO updateArbre(Long id, ArbreDTO arbreDTO) {
        Arbre existingArbre = arbreRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("Tree not found with ID: " + id));

        Champ champ = champRepository.findById(Long.valueOf(arbreDTO.champId()))
                .orElseThrow(() -> new CustomNotFoundException("Field not found with ID: " + arbreDTO.champId()));

        validateTreeSpacing(champ);

        existingArbre.setDateDePlantation(arbreDTO.dateCreation());
        existingArbre.setChamp(champ);
        existingArbre.setAge(calculateAge(existingArbre.getDateDePlantation()));

        Arbre updatedArbre = arbreRepository.save(existingArbre);

        return ArbreMapper.INSTANCE.toDTO(updatedArbre);
    }

    @Transactional
    @Override
    public void deleteArbre(Long id) {
        Arbre arbre = arbreRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("Tree not found with ID: " + id));
        arbreRepository.delete(arbre);
    }

    @Override
    public List<ArbreDTO> getArbresByChamp(Long champId) {
        return arbreRepository.findByChampId(champId).stream()
                .map(ArbreMapper.INSTANCE::toDTO)
                .toList();
    }
}





