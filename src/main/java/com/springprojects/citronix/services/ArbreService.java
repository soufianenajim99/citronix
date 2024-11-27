package com.springprojects.citronix.services;

import com.springprojects.citronix.dtos.ArbreDTO;

import java.util.List;
import java.util.UUID;

public interface ArbreService {
    ArbreDTO createArbre(ArbreDTO arbreDTO);
    ArbreDTO updateArbre(Long id, ArbreDTO arbreDTO);
    void deleteArbre(Long id);
    ArbreDTO getArbreById(Long id);
    List<ArbreDTO> getAllArbres();
    List<ArbreDTO> getArbresByChamp(Long champId);

}
