package com.springprojects.citronix.mappers;

import com.springprojects.citronix.dtos.FermeDTO;
import com.springprojects.citronix.models.Ferme;
import org.mapstruct.Mapper;

@Mapper
public interface FermeMapper {
    Ferme fermeDtoToFerme(FermeDTO fermeDTO);
    FermeDTO fermeToFermeDto(Ferme ferme);
}
