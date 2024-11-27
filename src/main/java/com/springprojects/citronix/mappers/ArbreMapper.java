package com.springprojects.citronix.mappers;

import com.springprojects.citronix.dtos.ArbreDTO;
import com.springprojects.citronix.models.Arbre;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ArbreMapper {
    ArbreMapper INSTANCE = Mappers.getMapper(ArbreMapper.class);

    @Mapping(source = "champ.id", target = "champId")
    ArbreDTO toDTO(Arbre arbre);

    @Mapping(source = "champId", target = "champ.id")
    Arbre toEntity(ArbreDTO arbreDTO);
}

