package com.springprojects.citronix.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;



public record ArbreDTO(
        @NotNull(message = "La date de plantation est obligatoire") LocalDate dateCreation,
        @PositiveOrZero(message = "L'âge de l'arbre doit être nul ou positif") int age,
        @NotNull(message = "L'ID du champ est obligatoire") String champId) {
}
//@Data
//@Builder
//public class ArbreDTO {
//    private UUID id;
//    @JsonFormat(pattern="yyyy-MM-dd")
//    private LocalDate dateCreation;
//    private int age;
//    private UUID champId; // Reference to Champ
//    public ArbreDTO withChampId(String champId) {
//        this.champId = UUID.fromString(champId);
//        return this;
//    }
//}


