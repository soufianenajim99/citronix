package com.springprojects.citronix.services;

import com.springprojects.citronix.dtos.ClientDTO;

import java.util.List;
import java.util.UUID;

public interface ClientService {
    ClientDTO createClient(ClientDTO clientDTO);
    ClientDTO updateClient(UUID id, ClientDTO clientDTO);
    void deleteClient(UUID id);
    ClientDTO getClientById(UUID id);
    List<ClientDTO> getAllClients();
}
