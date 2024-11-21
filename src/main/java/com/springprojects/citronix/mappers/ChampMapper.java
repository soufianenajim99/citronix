package com.springprojects.citronix.mappers;



import com.springprojects.citronix.dtos.ChampDTO;
import com.springprojects.citronix.models.Champ;
import org.mapstruct.Mapper;

@Mapper
public interface ChampMapper {
    Champ champDtoToChamp(ChampDTO champDTO);
    ChampDTO champToChampDto(Champ champ);
}
