package com.evaluacion.intuit.client.infrastructure.input.mappers;

import com.evaluacion.intuit.client.domain.businessobject.ClientBo;
import com.evaluacion.intuit.client.infrastructure.input.response.ClientResponse;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-03T13:31:49-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 18.0.2 (Amazon.com Inc.)"
)
@Component
public class ClientMapperImpl implements ClientMapper {

    @Override
    public ClientResponse toResponse(ClientBo clientBo) {
        if ( clientBo == null ) {
            return null;
        }

        Integer id = null;
        String name = null;
        String lastName = null;
        LocalDate birthDate = null;
        String email = null;
        String cuit = null;
        String phoneNumber = null;
        String address = null;

        id = clientBo.getId();
        name = clientBo.getName();
        lastName = clientBo.getLastName();
        birthDate = clientBo.getBirthDate();
        email = clientBo.getEmail();
        cuit = clientBo.getCuit();
        phoneNumber = clientBo.getPhoneNumber();
        address = clientBo.getAddress();

        ClientResponse clientResponse = new ClientResponse( id, name, lastName, birthDate, email, cuit, phoneNumber, address );

        return clientResponse;
    }

    @Override
    public List<ClientResponse> toResponseList(List<ClientBo> clientBo) {
        if ( clientBo == null ) {
            return null;
        }

        List<ClientResponse> list = new ArrayList<ClientResponse>( clientBo.size() );
        for ( ClientBo clientBo1 : clientBo ) {
            list.add( toResponse( clientBo1 ) );
        }

        return list;
    }
}
