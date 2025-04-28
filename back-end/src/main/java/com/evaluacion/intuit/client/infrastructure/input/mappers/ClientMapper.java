package com.evaluacion.intuit.client.infrastructure.input.mappers;

import com.evaluacion.intuit.client.domain.businessobject.ClientBo;
import com.evaluacion.intuit.client.infrastructure.input.response.ClientResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    ClientResponse toResponse(ClientBo clientBo);
    List<ClientResponse> toResponseList(List<ClientBo> clientBo);
}