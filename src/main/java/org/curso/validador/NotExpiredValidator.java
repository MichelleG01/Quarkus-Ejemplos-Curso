package org.curso.validador;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

//Aqui es la implementacion

// <Que anotacion estamos usando para esta validacion, que tipo de datos(atributo) queremos validar>
public class NotExpiredValidator implements ConstraintValidator<NotExpired, LocalDate> {
    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {

        //Si el elemento nunca caduca
        if (value == null) return true;

        LocalDate now = LocalDate.now();
        //que el año de ahora sea menor que cuando expira
        return ChronoUnit.YEARS.between(now, value) > 0;
    }
}
