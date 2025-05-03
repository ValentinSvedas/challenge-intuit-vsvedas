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
        String phoneNumber,
        String address
) {

}