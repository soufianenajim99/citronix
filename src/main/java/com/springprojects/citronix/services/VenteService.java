package com.springprojects.citronix.services;

import com.springprojects.citronix.dtos.VenteDTO;

import java.util.List;

public interface VenteService {
    VenteDTO createVente(VenteDTO venteDTO);
    VenteDTO updateVente(Long id, VenteDTO venteDTO);
    void deleteVente(Long id);
    List<VenteDTO> getAllVentes();
}
