package com.springprojects.citronix.services;

import com.springprojects.citronix.dtos.ChampDTO;

import java.util.List;


public interface ChampService {
    ChampDTO createChamp(ChampDTO champDTO);
    ChampDTO updateChamp(Long id, ChampDTO champDTO);
    void deleteChamp(Long id);
    ChampDTO getChampById(Long id);
    List<ChampDTO> getAllChamps();
}
