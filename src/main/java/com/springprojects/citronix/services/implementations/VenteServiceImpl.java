package com.springprojects.citronix.services.implementations;

import com.springprojects.citronix.dtos.VenteDTO;
import com.springprojects.citronix.exception.CustomNotFoundException;
import com.springprojects.citronix.exception.ValidationException;
import com.springprojects.citronix.mappers.VenteMapper;
import com.springprojects.citronix.models.Recolte;
import com.springprojects.citronix.models.Vente;
import com.springprojects.citronix.repositories.RecolteRepository;
import com.springprojects.citronix.repositories.VenteRepository;
import com.springprojects.citronix.services.VenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VenteServiceImpl implements VenteService {

    @Autowired
    private VenteRepository venteRepository;

    @Autowired
    private RecolteRepository recolteRepository;


    @Override
    public VenteDTO createVente(VenteDTO venteDTO) {
        // Fetch the associated recolte using the recolteId
        Recolte recolte = recolteRepository.findById(Long.parseLong(venteDTO.recolteId()))  // Assuming recolteId is the ID of the Recolte entity
                .orElseThrow(() -> new CustomNotFoundException("Recolte not found with ID: " + venteDTO.recolteId()));

        // Check if the recolte has already been sold
        if (recolte.getVente() != null) {
            throw new ValidationException("This recolte is already sold to a client.");
        }

        // Create a new Vente (Sale) entity from the DTO
        Vente vente = VenteMapper.INSTANCE.toEntity(venteDTO);  // Assuming you have a VenteMapper class for mapping
        vente.setRecolte(recolte);
        vente.setQuantite(recolte.getQuantite());
        vente.setRevenu(vente.getQuantite() * vente.getPrixUnitaire());  // Calculate revenue

        // Save the Vente and return the DTO representation
        Vente savedVente = venteRepository.save(vente);
        return VenteMapper.INSTANCE.toDTO(savedVente);
    }

    @Override
    public List<VenteDTO> getAllVentes() {
        // Fetch all sales and map them to VenteDTO
        return venteRepository.findAll().stream()
                .map(VenteMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public VenteDTO updateVente(Long id, VenteDTO venteDTO) {
        // Fetch the existing Vente by ID
        Vente existingVente = venteRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("Sale not found with ID: " + id));

        // Fetch the new Recolte to be associated with the Vente
        Recolte recolte = recolteRepository.findById(Long.parseLong(venteDTO.recolteId()))
                .orElseThrow(() -> new CustomNotFoundException("Recolte not found with ID: " + venteDTO.recolteId()));

        // Validate if the recolte matches the current sale's recolte
        if (!existingVente.getRecolte().getId().equals(recolte.getId())) {
            throw new ValidationException("You cannot change the recolte for an existing sale.");
        }

        // Update the fields in the existing Vente
        existingVente.setPrixUnitaire(venteDTO.prixUnitaire());
        existingVente.setDate(venteDTO.dateVente());
        existingVente.setClient(venteDTO.clientId());  // Assuming this is a reference to the client ID

        // Recalculate revenue based on updated values
        existingVente.setRevenu(existingVente.getQuantite()* existingVente.getPrixUnitaire());

        // Save the updated Vente
        Vente updatedVente = venteRepository.save(existingVente);

        return VenteMapper.INSTANCE.toDTO(updatedVente);  // Return the updated VenteDTO
    }

    @Override
    public void deleteVente(Long id) {
        // Find and delete the Vente by ID
        Vente vente = venteRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("Sale not found with ID: " + id));

        venteRepository.delete(vente);
    }
}

