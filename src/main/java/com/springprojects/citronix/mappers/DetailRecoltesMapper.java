package com.springprojects.citronix.mappers;

import com.springprojects.citronix.dtos.ClientDTO;
import com.springprojects.citronix.dtos.DetailRecoltesDTO;
import com.springprojects.citronix.models.Client;
import com.springprojects.citronix.models.DetailRecoltes;
import org.mapstruct.Mapper;


@Mapper
public interface DetailRecoltesMapper {
    DetailRecoltes detailRecoltesDtoToDetailRecoltes(DetailRecoltesDTO detailRecoltesDTO);
    DetailRecoltesDTO detailRecoltesToDetailRecoltesDto(DetailRecoltes detailRecoltes);
}
