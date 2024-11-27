package com.springprojects.citronix.mappers;

import com.springprojects.citronix.dtos.VenteDTO;
import com.springprojects.citronix.models.Vente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface VenteMapper {
    VenteMapper INSTANCE = Mappers.getMapper(VenteMapper.class);

    @Mapping(source = "recolteId", target = "recolte.id")
    Vente toEntity(VenteDTO venteDTO);

    @Mapping(source = "recolte.id", target = "recolteId")
    VenteDTO toDTO(Vente vente);
}

