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
    date = "2025-04-28T10:48:51-0300",
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
        String numberPhone = null;
        String address = null;

        ClientResponse clientResponse = new ClientResponse( id, name, lastName, birthDate, email, cuit, numberPhone, address );

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
