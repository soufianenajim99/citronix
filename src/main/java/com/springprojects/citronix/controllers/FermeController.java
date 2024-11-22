package com.springprojects.citronix.controllers;

import com.springprojects.citronix.dtos.FermeDTO;
import com.springprojects.citronix.mappers.FermeMapper;
import com.springprojects.citronix.services.FermeService;
import com.springprojects.citronix.utils.ApiResponse;
import com.springprojects.citronix.utils.ResponseUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/fermes")
@RequiredArgsConstructor
public class FermeController {
    private final FermeService fermeService;
    private final FermeMapper fermeMapper;


    @PostMapping
    public ResponseEntity<ApiResponse<FermeDTO>> createFerme(@Validated @RequestBody FermeDTO fermeDTO, HttpServletRequest request) {
        FermeDTO createdFerme = fermeService.createFerme(fermeDTO);
        return ResponseEntity.ok(ResponseUtil.success(createdFerme, "Ferme saved successfully", request.getRequestURI()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<FermeDTO>> updateFerme(@Validated @PathVariable UUID id, @RequestBody FermeDTO fermeDTO,HttpServletRequest request) {
        FermeDTO updatedFerme = fermeService.updateFerme(id, fermeDTO);
        return ResponseEntity.ok(ResponseUtil.success(updatedFerme, "Ferme updated successfully", request.getRequestURI()));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<FermeDTO>> deleteFerme(@Validated @PathVariable UUID id,HttpServletRequest request) {
         fermeService.deleteFerme(id);
        return ResponseEntity.ok(ResponseUtil.success( "Ferme Deleted successfully", request.getRequestURI()));


    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<FermeDTO>> getFermeById(@Validated @PathVariable UUID id,HttpServletRequest request) {
        FermeDTO fermeDTO = fermeService.getFermeById(id);
        return ResponseEntity.ok(ResponseUtil.success(fermeDTO, "Ferme retrived successfully", request.getRequestURI()));

    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<FermeDTO>>> getAllFermes(@Validated HttpServletRequest request) {
        List<FermeDTO> fermes = fermeService.getAllFermes();
        return ResponseEntity.ok(ResponseUtil.success(fermes, "Fermes List retrived successfully", request.getRequestURI()));

    }


}
