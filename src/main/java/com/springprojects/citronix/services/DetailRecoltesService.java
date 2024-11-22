package com.springprojects.citronix.services;

import com.springprojects.citronix.dtos.DetailRecoltesDTO;

import java.util.List;
import java.util.UUID;

public interface DetailRecoltesService {
    DetailRecoltesDTO createDetailRecoltes(DetailRecoltesDTO detailRecoltesDTO);
    DetailRecoltesDTO updateDetailRecoltes(UUID id, DetailRecoltesDTO detailRecoltesDTO);
    void deleteDetailRecoltes(UUID id);
    DetailRecoltesDTO getDetailRecoltesById(UUID id);
    List<DetailRecoltesDTO> getAllDetailRecoltess();
}
