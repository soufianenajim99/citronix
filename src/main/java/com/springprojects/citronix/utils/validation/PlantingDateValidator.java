package com.springprojects.citronix.utils.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class PlantingDateValidator implements ConstraintValidator<ValidPlantingDate, LocalDate> {

    public boolean isValid(LocalDate plantingDate, ConstraintValidatorContext context) {
        System.out.println("Validating plantingDate: " + plantingDate);
        if (plantingDate == null) return false;
    
        int month = plantingDate.getMonthValue();
        return month >= 3 && month <= 5;
    }
    
}
