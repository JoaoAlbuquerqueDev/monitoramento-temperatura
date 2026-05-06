package br.com.taiff.mesadeteste.validacao;

import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class CheckIdValidator implements ConstraintValidator<CheckId, Object> {
    private String campo;
    private Class<?> classe;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void initialize(CheckId existeValid) {
        campo = existeValid.campo();
        classe = existeValid.Classe();
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext constraintValidatorContext) {

        if(obj != null) {
            List<?> valores = entityManager.createQuery("SELECT 1 FROM " + classe.getSimpleName() + " WHERE " +
                    campo + " = :valor")
                    .setParameter("valor", obj).getResultList();

            Assert.state(valores.size() <= 1, "O campo " + campo + " tem valores duplicados");

            return !valores.isEmpty();
        }

        return true;
    }
}