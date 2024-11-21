package com.springprojects.citronix.services;

import com.springprojects.citronix.dtos.FermeDTO;

import java.util.List;
import java.util.UUID;

public interface FermeService {
    FermeDTO createFerme(FermeDTO fermeDTO);
    FermeDTO updateFerme(UUID id, FermeDTO fermeDTO);
    void deleteFerme(UUID id);
    FermeDTO getFermeById(UUID id);
    List<FermeDTO> getAllFermes();
}
