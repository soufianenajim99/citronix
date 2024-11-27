package com.springprojects.citronix.controllers;

import com.springprojects.citronix.dtos.RecolteDTO;
import com.springprojects.citronix.services.RecolteService;
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
@RequestMapping("/api/recoltes")
@RequiredArgsConstructor
@Tag(name = "Recoltes", description = "Endpoints for managing recoltes")
public class RecolteController {

    private final RecolteService recolteService;

    @PostMapping
    @Operation(summary = "Create a new recolte", description = "Create a new recolte with the specified details")
    public ResponseEntity<ApiResponse<RecolteDTO>> createRecolte(@RequestBody @Valid RecolteDTO recolteDTO, HttpServletRequest request) {
        RecolteDTO createdRecolte = recolteService.createRecolte(recolteDTO);
        return ResponseEntity.ok(ResponseUtil.success(createdRecolte, "Récolte créée avec succès", request.getRequestURI()));
    }

    @GetMapping
    @Operation(summary = "Get all recoltes", description = "Retrieve a list of all recoltes")
    public ResponseEntity<ApiResponse<List<RecolteDTO>>> getAllRecoltes(HttpServletRequest request) {
        List<RecolteDTO> recoltes = recolteService.getAllRecoltes();
        return ResponseEntity.ok(ResponseUtil.success(recoltes, "Liste des récoltes récupérée avec succès", request.getRequestURI()));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update recolte", description = "Update the recolte with the specified id")
    public ResponseEntity<ApiResponse<RecolteDTO>> updateRecolte(@PathVariable Long id, @RequestBody @Valid RecolteDTO recolteDTO, HttpServletRequest request) {
        RecolteDTO updatedRecolte = recolteService.updateRecolte(id, recolteDTO);
        return ResponseEntity.ok(ResponseUtil.success(updatedRecolte, "Récolte mise à jour avec succès", request.getRequestURI()));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete recolte", description = "Delete the recolte with the specified id")
    public ResponseEntity<ApiResponse<String>> deleteRecolte(@PathVariable Long id, HttpServletRequest request) {
        recolteService.deleteRecolte(id);
        return ResponseEntity.ok(ResponseUtil.success("Récolte supprimée avec succès", request.getRequestURI()));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get recolte by ID", description = "Retrieve a recolte by its ID")
    public ResponseEntity<ApiResponse<RecolteDTO>> getRecolteById(@PathVariable Long id, HttpServletRequest request) {
        RecolteDTO recolte = recolteService.getRecolteById(id);
        return ResponseEntity.ok(ResponseUtil.success(recolte, "Récolte récupérée avec succès", request.getRequestURI()));
    }
}

