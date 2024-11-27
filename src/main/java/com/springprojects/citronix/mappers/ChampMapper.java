package com.springprojects.citronix.mappers;



import com.springprojects.citronix.dtos.ChampDTO;
import com.springprojects.citronix.models.Champ;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ChampMapper {

    ChampMapper INSTANCE = Mappers.getMapper(ChampMapper.class);

    @Mapping(source = "ferme.id", target = "fermeId")
    ChampDTO toDTO(Champ champ);

    @Mapping(source = "fermeId", target = "ferme.id")
    Champ toEntity(ChampDTO champDTO);

    List<ChampDTO> toDTOList(List<Champ> champs);
}



