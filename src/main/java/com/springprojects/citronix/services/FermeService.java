package com.springprojects.citronix.services;

import com.springprojects.citronix.dtos.FermeDTO;

import java.util.List;
import java.util.UUID;

public interface FermeService {
    FermeDTO createFerme(FermeDTO fermeDTO);
    FermeDTO updateFerme(Long id, FermeDTO fermeDTO);
    void deleteFerme(Long id);
    FermeDTO getFermeById(Long id);
    List<FermeDTO> getAllFermes();
}
