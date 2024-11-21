package com.springprojects.citronix.mappers;


import com.springprojects.citronix.dtos.ClientDTO;
import com.springprojects.citronix.models.Client;
import org.mapstruct.Mapper;

@Mapper
public interface ClientMapper {
    Client clientDtoToClient(ClientDTO clientDTO);
    ClientDTO clientToClientDto(Client client);
}
