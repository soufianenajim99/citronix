package com.springprojects.citronix.services;

import com.springprojects.citronix.dtos.RecolteDTO;

import java.util.List;
import java.util.UUID;

public interface RecolteService {
    RecolteDTO createRecolte(RecolteDTO recolteDTO);
    RecolteDTO updateRecolte(UUID id, RecolteDTO recolteDTO);
    void deleteRecolte(UUID id);
    RecolteDTO getRecolteById(UUID id);
    List<RecolteDTO> getAllRecoltes();
}
