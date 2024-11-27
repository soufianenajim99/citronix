package com.springprojects.citronix.controllers;

import com.springprojects.citronix.dtos.ArbreDTO;
import com.springprojects.citronix.services.ArbreService;
import com.springprojects.citronix.utils.ApiResponse;
import com.springprojects.citronix.utils.ResponseUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/arbres")
@RequiredArgsConstructor
public class ArbreController {
    private final ArbreService arbreService;

    @PostMapping
    public ResponseEntity<ApiResponse<ArbreDTO>> createArbre(@Validated @RequestBody ArbreDTO arbreDTO, HttpServletRequest request) {
        ArbreDTO createdArbre = arbreService.createArbre(arbreDTO);
        return ResponseEntity.ok(ResponseUtil.success(createdArbre, "Arbre créé avec succès", request.getRequestURI()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ArbreDTO>> updateArbre(@PathVariable Long id, @RequestBody ArbreDTO arbreDTO, HttpServletRequest request) {
        ArbreDTO updatedArbre = arbreService.updateArbre(id, arbreDTO);
        return ResponseEntity.ok(ResponseUtil.success(updatedArbre, "Arbre mis à jour avec succès", request.getRequestURI()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteArbre(@PathVariable Long id, HttpServletRequest request) {
        arbreService.deleteArbre(id);
        return ResponseEntity.ok(ResponseUtil.success("Arbre supprimé avec succès", request.getRequestURI()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ArbreDTO>> getArbreById(@PathVariable Long id, HttpServletRequest request) {
        ArbreDTO arbre = arbreService.getArbreById(id);
        return ResponseEntity.ok(ResponseUtil.success(arbre, "Arbre récupéré avec succès", request.getRequestURI()));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ArbreDTO>>> getAllArbres(HttpServletRequest request) {
        List<ArbreDTO> arbres = arbreService.getAllArbres();
        return ResponseEntity.ok(ResponseUtil.success(arbres, "Liste des arbres récupérée avec succès", request.getRequestURI()));
    }

    @GetMapping("/champ/{champId}")
    public ResponseEntity<ApiResponse<List<ArbreDTO>>> getArbresByChamp(@PathVariable Long champId, HttpServletRequest request) {
        List<ArbreDTO> arbres = arbreService.getArbresByChamp(champId);
        return ResponseEntity.ok(ResponseUtil.success(arbres, "Liste des arbres pour le champ récupérée avec succès", request.getRequestURI()));
    }
}