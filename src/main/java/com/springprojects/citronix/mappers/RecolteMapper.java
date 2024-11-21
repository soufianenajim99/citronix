package com.springprojects.citronix.mappers;

import com.springprojects.citronix.dtos.RecolteDTO;
import com.springprojects.citronix.models.Client;
import com.springprojects.citronix.models.Recolte;
import org.mapstruct.Mapper;

@Mapper
public interface RecolteMapper {
    Recolte recolteDtoToRecolte(RecolteDTO recolteDTO);
    RecolteDTO recolteToRecolteDto(Recolte recolte);
}
