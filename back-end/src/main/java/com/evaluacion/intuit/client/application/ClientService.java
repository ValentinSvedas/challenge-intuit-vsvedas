package com.evaluacion.intuit.client.application;

import com.evaluacion.intuit.client.domain.businessobject.ClientBo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ClientService {
    public List<ClientBo> findAll();
    public ClientBo findById(Integer clientId);
    public Page<ClientBo> findByName(String clientName, Pageable page);
    public ClientBo create(ClientBo clientBo);
    public ClientBo update(Integer id, ClientBo clientBo);
}
