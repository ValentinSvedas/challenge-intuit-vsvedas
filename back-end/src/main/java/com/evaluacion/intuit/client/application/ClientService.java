package com.evaluacion.intuit.client.application;

import com.evaluacion.intuit.client.domain.businessobject.ClientBo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ClientService {
    Page<ClientBo> findAll(Pageable pageable);
    ClientBo findById(Integer clientId);
    Page<ClientBo> findByName(String clientName, Pageable page);
    ClientBo create(ClientBo clientBo);
    ClientBo update(Integer id, ClientBo clientBo);
}
