package com.springprojects.citronix.services;

import com.springprojects.citronix.dtos.ChampDTO;

import java.util.List;
import java.util.UUID;

public interface ChampService {
    ChampDTO createChamp(ChampDTO champDTO);
    ChampDTO updateChamp(UUID id, ChampDTO champDTO);
    void deleteChamp(UUID id);
    ChampDTO getChampById(UUID id);
    List<ChampDTO> getAllChamps();
}
