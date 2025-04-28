package com.evaluacion.intuit.client.application.validations;

import com.evaluacion.intuit.client.application.exceptions.OperationNotValidException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

public class ClientValidator {

    public static void validateCuitFormat(String cuit) {
        if (cuit == null || cuit.isEmpty()) {
            throw new OperationNotValidException("invalid-cuit", "El CUIT no puede estar vacío");
        }

        // Validación solo dígitos
        String cuitPattern = "^[0-9]{11}$";
        if (!cuit.matches(cuitPattern)) {
            throw new OperationNotValidException("invalid-cuit",
                    "El CUIT debe contener exactamente 11 dígitos numéricos");
        }

        // Validación formato
        if (!validarDigitoVerificadorCuit(cuit)) {
            throw new OperationNotValidException("invalid-cuit",
                    "El CUIT no tiene un dígito verificador válido");
        }
    }

    private static boolean validarDigitoVerificadorCuit(String cuit) {
        if (cuit.length() != 11) return false;

        int[] factores = {5, 4, 3, 2, 7, 6, 5, 4, 3, 2};
        int suma = 0;

        for (int i = 0; i < 10; i++) {
            suma += Character.getNumericValue(cuit.charAt(i)) * factores[i];
        }

        int resto = suma % 11;
        int digitoVerificadorCalculado = (resto == 0) ? 0 : (11 - resto);

        if (digitoVerificadorCalculado == 11) digitoVerificadorCalculado = 0;

        int digitoVerificadorReal = Character.getNumericValue(cuit.charAt(10));

        return digitoVerificadorCalculado == digitoVerificadorReal;
    }

    public static void validateEmailFormat(String email) {
        if (email == null || email.isEmpty()) {
            throw new OperationNotValidException("invalid-email", "El email no puede estar vacío");
        }

        String emailPattern = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        if (!email.matches(emailPattern)) {
            throw new OperationNotValidException("invalid-email",
                    "El email no tiene un formato válido");
        }
    }

    public static void validateBirthDateFormat(LocalDate birthDate) {
        if (birthDate == null) {
            throw new OperationNotValidException("invalid-birthdate",
                    "La fecha de nacimiento no puede estar vacía");
        }

        if (birthDate.isAfter(LocalDate.now())) {
            throw new OperationNotValidException("invalid-birthdate",
                    "La fecha de nacimiento debe ser anterior a la fecha actual.");
        }

    }
}
