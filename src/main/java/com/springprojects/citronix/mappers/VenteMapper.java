package com.springprojects.citronix.mappers;

import com.springprojects.citronix.dtos.VenteDTO;
import com.springprojects.citronix.models.Vente;
import org.mapstruct.Mapper;

@Mapper
public interface VenteMapper {
    Vente venteDtoToVente(VenteDTO venteDTO);
    VenteDTO venteToVenteDto(Vente vente);
}
