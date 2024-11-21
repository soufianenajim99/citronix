package com.springprojects.citronix.services.implementations;

import com.springprojects.citronix.dtos.FermeDTO;
import com.springprojects.citronix.mappers.FermeMapper;
import com.springprojects.citronix.models.Ferme;
import com.springprojects.citronix.repositories.FermeRepository;
import com.springprojects.citronix.services.FermeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FermeServiceImpl implements FermeService {

    private final FermeRepository fermeRepository;
    private final FermeMapper fermeMapper;


    @Override
    public FermeDTO createFerme(FermeDTO fermeDTO) {
        Ferme ferme = fermeMapper.fermeDtoToFerme(fermeDTO);
        Ferme savedFerme = fermeRepository.save(ferme);
        return fermeMapper.fermeToFermeDto(savedFerme);
    }

    @Override
    public FermeDTO updateFerme(UUID id, FermeDTO fermeDTO) {
        return null;
    }

    @Override
    public void deleteFerme(UUID id) {

    }

    @Override
    public FermeDTO getFermeById(UUID id) {
        return null;
    }

    @Override
    public List<FermeDTO> getAllFermes() {
        return List.of();
    }
}
