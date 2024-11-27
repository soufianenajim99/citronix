package com.springprojects.citronix.controllers;
import com.springprojects.citronix.dtos.RecolteDTO;
import com.springprojects.citronix.dtos.VenteDTO;
import com.springprojects.citronix.services.RecolteService;
import com.springprojects.citronix.services.VenteService;
import com.springprojects.citronix.utils.ApiResponse;
import com.springprojects.citronix.utils.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/ventes")
@RequiredArgsConstructor
@Tag(name = "Ventes", description = "Endpoints for managing ventes (sales)")
public class VenteController {

    private final VenteService venteService;

    @PostMapping
    @Operation(summary = "Create a new vente", description = "Create a new vente with the specified details")
    public ResponseEntity<ApiResponse<VenteDTO>> createVente(@RequestBody @Valid VenteDTO venteDTO, HttpServletRequest request) {
        VenteDTO createdVente = venteService.createVente(venteDTO);
        return ResponseEntity.ok(ResponseUtil.success(createdVente, "Vente créée avec succès", request.getRequestURI()));
    }

    @GetMapping
    @Operation(summary = "Get all ventes", description = "Retrieve a list of all ventes")
    public ResponseEntity<ApiResponse<List<VenteDTO>>> getAllVentes(HttpServletRequest request) {
        List<VenteDTO> ventes = venteService.getAllVentes();
        return ResponseEntity.ok(ResponseUtil.success(ventes, "Liste des ventes récupérée avec succès", request.getRequestURI()));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update vente", description = "Update the vente with the specified id")
    public ResponseEntity<ApiResponse<VenteDTO>> updateVente(@PathVariable Long id, @RequestBody @Valid VenteDTO venteDTO, HttpServletRequest request) {
        VenteDTO updatedVente = venteService.updateVente(id, venteDTO);
        return ResponseEntity.ok(ResponseUtil.success(updatedVente, "Vente mise à jour avec succès", request.getRequestURI()));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete vente", description = "Delete the vente with the specified id")
    public ResponseEntity<ApiResponse<String>> deleteVente(@PathVariable Long id, HttpServletRequest request) {
        venteService.deleteVente(id);
        return ResponseEntity.ok(ResponseUtil.success("Vente supprimée avec succès", request.getRequestURI()));
    }

}

