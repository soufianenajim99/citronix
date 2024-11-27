package com.springprojects.citronix.services;

import com.springprojects.citronix.dtos.RecolteDTO;

import java.util.List;
import java.util.UUID;

public interface RecolteService {
    RecolteDTO createRecolte(RecolteDTO recolteDTO);
    RecolteDTO updateRecolte(Long id, RecolteDTO recolteDTO);
    void deleteRecolte(Long id);
    RecolteDTO getRecolteById(Long id);
    List<RecolteDTO> getAllRecoltes();
}
