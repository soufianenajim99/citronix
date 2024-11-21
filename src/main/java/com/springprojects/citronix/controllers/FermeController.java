package com.springprojects.citronix.controllers;

import com.springprojects.citronix.dtos.FermeDTO;
import com.springprojects.citronix.mappers.FermeMapper;
import com.springprojects.citronix.services.FermeService;
import com.springprojects.citronix.utils.ApiResponse;
import com.springprojects.citronix.utils.ResponseUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/fermes")
@RequiredArgsConstructor
public class FermeController {
    private final FermeService fermeService;
    private final FermeMapper fermeMapper;


    @PostMapping
    public ResponseEntity<ApiResponse<FermeDTO>> createFerme(@RequestBody FermeDTO fermeDTO, HttpServletRequest request) {
        FermeDTO createdFerme = fermeService.createFerme(fermeDTO);
        return ResponseEntity.ok(ResponseUtil.success(createdFerme, "Ferme saved successfully", request.getRequestURI()));
    }


}
