package com.springprojects.citronix.controllers;

import com.springprojects.citronix.dtos.ChampDTO;
import com.springprojects.citronix.dtos.FermeDTO;
import com.springprojects.citronix.exception.ResourceNotFoundException;
import com.springprojects.citronix.models.Ferme;
import com.springprojects.citronix.repositories.FermeRepository;
import com.springprojects.citronix.services.ChampService;
import com.springprojects.citronix.utils.ApiResponse;
import com.springprojects.citronix.utils.ResponseUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/champs")
@RequiredArgsConstructor
public class ChampController {

    private final ChampService champService;
    private final FermeRepository fermeRepository;

    @PostMapping
    public ResponseEntity<ApiResponse<ChampDTO>> createChamp(
            @Validated @RequestBody ChampDTO champDTO,
            HttpServletRequest request) {
        // Validate and fetch the Ferme using the provided ferme ID
        UUID fermeId = champDTO.getFerme().getId();
        fermeRepository.findById(fermeId)
                .orElseThrow(() -> new ResourceNotFoundException("Ferme introuvable avec l'ID : " + fermeId));

        // Use withFermeId to associate the Ferme with ChampDTO
        ChampDTO updatedChampDTO = champDTO.withFermeId(fermeId.toString());

        // Call the service to create the Champ
        ChampDTO createdChamp = champService.createChamp(updatedChampDTO);
        return ResponseEntity.ok(ResponseUtil.success(createdChamp, "Champ créé avec succès", request.getRequestURI()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ChampDTO>> updateChamp(
            @Validated @PathVariable UUID id,
            @RequestBody ChampDTO champDTO,
            HttpServletRequest request) {
        // Pass the DTO directly to the service for updating
        ChampDTO updatedChamp = champService.updateChamp(id, champDTO);
        return ResponseEntity.ok(ResponseUtil.success(updatedChamp, "Champ mis à jour avec succès", request.getRequestURI()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteChamp(
            @Validated @PathVariable UUID id,
            HttpServletRequest request) {
        // Delete the Champ by ID
        champService.deleteChamp(id);
        return ResponseEntity.ok(ResponseUtil.success("Champ supprimé avec succès", request.getRequestURI()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ChampDTO>> getChampById(
            @Validated @PathVariable UUID id,
            HttpServletRequest request) {
        // Retrieve a single Champ by ID
        ChampDTO champDTO = champService.getChampById(id);
        return ResponseEntity.ok(ResponseUtil.success(champDTO, "Champ récupéré avec succès", request.getRequestURI()));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ChampDTO>>> getAllChamps(HttpServletRequest request) {
        // Retrieve all Champs
        List<ChampDTO> champs = champService.getAllChamps();
        return ResponseEntity.ok(ResponseUtil.success(champs, "Liste des champs récupérée avec succès", request.getRequestURI()));
    }
}
