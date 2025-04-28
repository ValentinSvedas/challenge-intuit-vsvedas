package com.evaluacion.intuit.client.infrastructure.input.request;

import com.evaluacion.intuit.client.domain.businessobject.ClientBo;
import com.evaluacion.intuit.client.domain.entity.Client;
import com.evaluacion.intuit.client.infrastructure.input.response.ClientResponse;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

public record ClientRequest(
        Integer id,
        @NotBlank(message = "El nombre no puede estar vacío.")
        String name,
        @NotBlank(message = "El apellido no puede estar vacío.")
        String lastName,
        LocalDate birthDate,
        String email,
        @NotNull(message = "El CUIT no puede estar vacío.")
        String cuit,
        String phoneNumber,
        @NotBlank(message = "La dirección no puede estar vacía.")
        String address
) {
        public ClientBo toBo() {
                return new ClientBo(
                        this.id(),
                        this.name(),
                        this.lastName(),
                        this.birthDate(),
                        this.email(),
                        this.cuit(),
                        this.phoneNumber(),
                        this.address()
                );
        }
}
