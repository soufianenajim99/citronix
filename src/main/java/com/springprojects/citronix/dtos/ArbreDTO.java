package com.springprojects.citronix.dtos;

import com.springprojects.citronix.models.DetailRecoltes;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Builder
@Data
public class ArbreDTO {
    private UUID id;
    @NotNull(message = "La date de plantation est obligatoire")
    @PastOrPresent(message = "La date de plantation doit être dans le passé ou aujourd'hui")
    private LocalDate dateCreation;
    @NotNull(message = "L'age de L'arbre doit être non null ")
    private int age;
    @NotNull(message = "L'arbre doit être associé à un champ")
    private ChampDTO champ;
    private Set<DetailRecoltes> recoltedetails;

    public ArbreDTO withChampId(String champId) {
        return ArbreDTO.builder()
                .id(this.id)
                .dateCreation(this.dateCreation)
                .age(this.age)
                .champ(ChampDTO.builder().id(UUID.fromString(champId)).build())
                .recoltedetails(this.recoltedetails)
                .build();
    }
}
