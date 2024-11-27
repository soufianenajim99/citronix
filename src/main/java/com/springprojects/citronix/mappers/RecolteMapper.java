package com.springprojects.citronix.mappers;

import com.springprojects.citronix.dtos.DetailRecolteArbreDTO;
import com.springprojects.citronix.dtos.RecolteDTO;
import com.springprojects.citronix.models.Arbre;
import com.springprojects.citronix.models.DetailRecolteArbre;
import com.springprojects.citronix.models.Recolte;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RecolteMapper {
    RecolteMapper INSTANCE = Mappers.getMapper(RecolteMapper.class);

    @Mapping(source = "champ.id", target = "champId")
    RecolteDTO toDTO(Recolte recolte);

    @Mapping(source = "champId", target = "champ.id")
    Recolte toEntity(RecolteDTO recolteDTO);

    @Mapping(source = "arbre.id", target = "arbreId")
    DetailRecolteArbreDTO toDTO(DetailRecolteArbre detail);

    @Mapping(target = "arbre", expression = "java(mapArbre(detailDTO.arbreId()))")
    DetailRecolteArbre toEntity(DetailRecolteArbreDTO detailDTO);

    default Arbre mapArbre(Long arbreId) {
        if (arbreId == null)
            return null;
        Arbre arbre = new Arbre();
        arbre.setId(arbreId);
        return arbre;
    }
}

