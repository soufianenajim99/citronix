package com.springprojects.citronix.mappers;

import com.springprojects.citronix.dtos.ArbreDTO;
import com.springprojects.citronix.models.Arbre;
import org.mapstruct.Mapper;

@Mapper
public interface ArbreMapper {
    Arbre arbreDtoToArbre(ArbreDTO arbreDTO);
    ArbreDTO arbreToArbreDto(Arbre arbre);
}
