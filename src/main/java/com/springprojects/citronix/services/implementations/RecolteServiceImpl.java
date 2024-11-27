package com.springprojects.citronix.services.implementations;

import com.springprojects.citronix.dtos.FermeDTO;
import com.springprojects.citronix.dtos.RecolteDTO;
import com.springprojects.citronix.exception.CustomNotFoundException;
import com.springprojects.citronix.exception.ValidationException;
import com.springprojects.citronix.mappers.FermeMapper;
import com.springprojects.citronix.mappers.RecolteMapper;
import com.springprojects.citronix.models.*;
import com.springprojects.citronix.repositories.ArbreRepository;
import com.springprojects.citronix.repositories.ChampRepository;
import com.springprojects.citronix.repositories.FermeRepository;
import com.springprojects.citronix.repositories.RecolteRepository;
import com.springprojects.citronix.services.FermeService;
import com.springprojects.citronix.services.RecolteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecolteServiceImpl implements RecolteService {

    @Autowired
    private RecolteRepository recolteRepository;

    @Autowired
    private ChampRepository champRepository;

    @Autowired
    private ArbreRepository arbreRepository;

    @Transactional
    @Override
    public RecolteDTO createRecolte(RecolteDTO recolteDTO) {
        // Récupérer le champ associé
        Champ champ = champRepository.findById(recolteDTO.champId())
                .orElseThrow(() -> new CustomNotFoundException("Champ non trouvé avec l'ID : " + recolteDTO.champId()));

        // Vérifier si une récolte existe déjà pour la saison et le champ
        if (recolteRepository.existsBySaisonAndAndChampId(recolteDTO.saison(), recolteDTO.champId())) {
            throw new IllegalArgumentException("Une récolte existe déjà pour cette saison et ce champ.");
        }

        // Récupérer les arbres associés au champ
        List<Arbre> arbres = arbreRepository.findByChampId(champ.getId());
        if (arbres.isEmpty()) {
            throw new ValidationException("Aucun arbre trouvé pour le champ spécifié (ID : " + recolteDTO.champId() + ").");
        }

        // Créer l'entité Recolte et associer le champ
        Recolte recolte = RecolteMapper.INSTANCE.toEntity(recolteDTO);
        recolte.setChamp(champ);

        double totalQuantite = 0.0;

        // Traiter chaque détail de récolte d'arbre
        // Ensure that you are properly accessing the details and related objects from RecolteDTO and the Recolte entity.

        if (recolte.getDetailsRecolteArbres() != null) {
            for (DetailRecolteArbre detail : recolte.getDetailsRecolteArbres()) {
                // Find the Arbre entity by its ID from the repository.
                Arbre arbre = arbreRepository.findById(detail.getArbre().getId())
                        .orElseThrow(() -> new CustomNotFoundException("Arbre non trouvé avec l'ID : " + detail.getArbre().getId()));

                // Calculate the productivity of the tree.
                double productiviteArbre = arbre.calculerProductivite();

                // Set the Arbre and Recolte objects to the DetailRecolteArbre object.
                detail.setArbre(arbre);
                detail.setRecolte(recolte); // assuming recolte is already instantiated
                detail.setQuantite(productiviteArbre);

                // Accumulate the total quantity.
                totalQuantite += productiviteArbre;
            }

            // Now you can use totalQuantite if needed
        }


        recolte.setQuantite(totalQuantite);

        // Sauvegarder et retourner l'entité Recolte sauvegardée
        Recolte recolteSauvegarde = recolteRepository.save(recolte);
        return RecolteMapper.INSTANCE.toDTO(recolteSauvegarde);
    }

    @Transactional
    @Override
    public RecolteDTO updateRecolte(Long id, RecolteDTO recolteDTO) {
        // Trouver la récolte existante
        Recolte recolteExistante = recolteRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("Récolte non trouvée avec l'ID : " + id));

        // Récupérer le champ associé
        Champ champ = champRepository.findById(recolteDTO.champId())
                .orElseThrow(() -> new CustomNotFoundException("Champ non trouvé avec l'ID : " + recolteDTO.champId()));

        // Mettre à jour les attributs de la récolte
        recolteExistante.setSaison(recolteDTO.saison());
        recolteExistante.setDate(recolteDTO.date());
        recolteExistante.setChamp(champ);

        double totalQuantite = 0.0;
        recolteExistante.getDetailsRecolteArbres().clear();

        // Traiter chaque détail de récolte
        for (DetailRecolteArbre detailDTO : recolteDTO.detailsRecolteArbres()) {
            Long arbreId = detailDTO.getArbre().getId();
            Arbre arbre = arbreRepository.findById(arbreId)
                    .orElseThrow(() -> new ValidationException("Arbre avec l'ID " + arbreId + " n'existe pas."));

            // Vérifier que l'arbre appartient bien au champ spécifié
            if (!arbre.getChamp().getId().equals(recolteDTO.champId())) {
                throw new ValidationException(
                        "L'arbre avec l'ID " + arbreId + " n'appartient pas au champ spécifié (ID : " + recolteDTO.champId() + ").");
            }

            // Créer et ajouter un nouveau détail de récolte
            DetailRecolteArbre detail = new DetailRecolteArbre();
            detail.setArbre(arbre);
            detail.setRecolte(recolteExistante);

            double productiviteArbre = arbre.calculerProductivite();
            detail.setQuantite(productiviteArbre);

            totalQuantite += productiviteArbre;
            recolteExistante.getDetailsRecolteArbres().add(detail);
        }

        recolteExistante.setQuantite(totalQuantite);

        // Sauvegarder et retourner la récolte mise à jour
        Recolte recolteMiseAJour = recolteRepository.save(recolteExistante);
        return RecolteMapper.INSTANCE.toDTO(recolteMiseAJour);
    }

    @Transactional
    @Override
    public void deleteRecolte(Long id) {
        // Trouver la récolte à supprimer
        Recolte recolte = recolteRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("Récolte non trouvée avec l'ID : " + id));

        // Supprimer la récolte
        recolteRepository.delete(recolte);
    }

    @Override
    public RecolteDTO getRecolteById(Long id) {
        Recolte recolte = recolteRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("Récolte non trouvée avec l'ID : " + id));
        return RecolteMapper.INSTANCE.toDTO(recolte);
    }

    @Override
    public List<RecolteDTO> getAllRecoltes() {
        return recolteRepository.findAll().stream()
                .map(RecolteMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }
}

