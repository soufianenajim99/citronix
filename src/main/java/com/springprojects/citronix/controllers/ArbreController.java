package com.springprojects.citronix.controllers;

import com.springprojects.citronix.dtos.ArbreDTO;
import com.springprojects.citronix.services.ArbreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<ArbreDTO> createArbre(@RequestBody ArbreDTO arbreDTO) {
        ArbreDTO createdArbre = arbreService.createArbre(arbreDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdArbre);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArbreDTO> updateArbre(@PathVariable Long id, @RequestBody ArbreDTO arbreDTO) {
        ArbreDTO updatedArbre = arbreService.updateArbre(id, arbreDTO);
        return ResponseEntity.ok(updatedArbre);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArbre(@PathVariable Long id) {
        arbreService.deleteArbre(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArbreDTO> getArbreById(@PathVariable Long id) {
        ArbreDTO arbre = arbreService.getArbreById(id);
        return ResponseEntity.ok(arbre);
    }

    @GetMapping
    public ResponseEntity<List<ArbreDTO>> getAllArbres() {
        List<ArbreDTO> arbres = arbreService.getAllArbres();
        return ResponseEntity.ok(arbres);
    }

    @GetMapping("/champ/{champId}")
    public ResponseEntity<List<ArbreDTO>> getArbresByChamp(@PathVariable Long champId) {
        List<ArbreDTO> arbres = arbreService.getArbresByChamp(champId);
        return ResponseEntity.ok(arbres);
    }
}