package com.evaluacion.intuit.client.domain.businessobject;

import com.evaluacion.intuit.client.domain.entity.Client;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Objects;

@Data
@Builder
@AllArgsConstructor
public class ClientBo {
    Integer id;
    String name;
    String lastName;
    LocalDate birthDate;
    String email;
    String cuit;
    String phoneNumber;
    String address;

    public ClientBo(Client client) {
        this.id = Objects.isNull(client.getId()) ? null : client.getId();
        this.name = client.getName();
        this.lastName = client.getLastName();
        this.birthDate = client.getBirthDate();
        this.email = client.getEmail();
        this.cuit = client.getCuit();
        this.phoneNumber = client.getPhoneNumber();
        this.address = client.getAddress();
    }

    public Client toEntity() {
        return new Client(
                Objects.nonNull(this.getId()) ? this.getId() : null,
                this.getName(),
                this.getLastName(),
                this.getBirthDate(),
                this.getEmail(),
                this.getCuit(),
                this.getPhoneNumber(),
                this.getAddress()
        );
    }
}
