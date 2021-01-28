package org.curso.validador;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.TYPE, ElementType.CONSTRUCTOR,
        ElementType.PARAMETER, ElementType.TYPE_USE })
@Retention(RetentionPolicy.RUNTIME)
//esta anotacion se utiliza en concreto en
@Constraint(validatedBy = {NotExpiredValidator.class})
public @interface NotExpired {

    String message() default "Beer must not be expired";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };

}
