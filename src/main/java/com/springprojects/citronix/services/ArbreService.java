package com.springprojects.citronix.services;

import com.springprojects.citronix.dtos.ArbreDTO;

import java.util.List;
import java.util.UUID;

public interface ArbreService {
    ArbreDTO createArbre(ArbreDTO arbreDTO);
    ArbreDTO updateArbre(UUID id, ArbreDTO arbreDTO);
    void deleteArbre(UUID id);
    ArbreDTO getArbreById(UUID id);
    List<ArbreDTO> getAllArbres();

}
