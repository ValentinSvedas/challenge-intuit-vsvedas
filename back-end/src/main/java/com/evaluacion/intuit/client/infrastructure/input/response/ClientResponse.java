package com.evaluacion.intuit.client.infrastructure.input.response;

import com.evaluacion.intuit.client.domain.entity.Client;

import java.time.LocalDate;

public record ClientResponse(
        Integer id,
        String name,
        String lastName,
        LocalDate birthDate,
        String email,
        String cuit,
        String numberPhone,
        String address
) {

    public ClientResponse fromEntity(Client client) {
        return new ClientResponse(
                client.getId(),
                client.getName(),
                client.getLastName(),
                client.getBirthDate(),
                client.getEmail(),
                client.getCuit(),
                client.getNumberPhone(),
                client.getAddress()
        );
    }

}