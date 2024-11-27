package com.springprojects.citronix.mappers;

import com.springprojects.citronix.dtos.ChampDTO;
import com.springprojects.citronix.dtos.FermeDTO;
import com.springprojects.citronix.models.Champ;
import com.springprojects.citronix.models.Ferme;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface FermeMapper {

    FermeMapper INSTANCE = Mappers.getMapper(FermeMapper.class);

    FermeDTO toDTO(Ferme ferme);

    Ferme toEntity(FermeDTO fermeDTO);
}

