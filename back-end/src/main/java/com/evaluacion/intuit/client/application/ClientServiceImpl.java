package com.evaluacion.intuit.client.application;

import com.evaluacion.intuit.client.application.exceptions.NotFoundException;
import com.evaluacion.intuit.client.application.exceptions.OperationNotValidException;
import com.evaluacion.intuit.client.application.validations.ClientValidator;
import com.evaluacion.intuit.client.domain.businessobject.ClientBo;
import com.evaluacion.intuit.client.domain.entity.Client;
import com.evaluacion.intuit.client.infrastructure.output.persistence.ClientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Override
    public List<ClientBo> findAll() {
        return clientRepository
                .findAll().stream()
                .map(ClientBo::new)
                .toList();
    }

    @Override
    public ClientBo findById(Integer clientId) {
        log.trace("Looking for client by id -> {}", clientId);
        var client = clientRepository.findById(clientId)
                .orElseThrow(() ->
                        new NotFoundException("client-not-found",
                                String.format("No se encontró el cliente con identificador %s", clientId))
                );
        return new ClientBo(client);
    }

    @Override
    public Page<ClientBo> findByName(String clientName, Pageable page) {
        log.trace("Looking for client by name -> {}", clientName);
        var clientListByName = clientRepository.findAllByName(clientName.toUpperCase(), page).map(ClientBo::new);
        log.info("Clients founded {}", clientListByName);
        return clientListByName;
    }

    @Override
    @Transactional
    public ClientBo create(ClientBo clientBo) {
        validateCreateClient(clientBo);
        log.trace("Saving client -> {}", clientBo);
        var clientEntity = clientRepository.save(clientBo.toEntity());
        log.info("Client saved successfully with ID {}", clientBo.getId());
        return new ClientBo(clientEntity);
    }

    @Override
    @Transactional
    public ClientBo update(Integer clientId, ClientBo clientBo) {
        var client = clientRepository.findById(clientId)
                .orElseThrow(() ->
                        new NotFoundException("client-not-found",
                                String.format("No se encontró el cliente con identificador %s", clientId))
                );
        validateClientUpdate(clientBo, client);

        log.trace("Saving client -> {}", client);
        var entity = clientBo.toEntity();
        entity.setId(client.getId());
        var clientEntity = clientRepository.save(entity);
        log.info("Client updated successfully with ID {}", clientId);
        return new ClientBo(clientEntity);
    }

    //Método para validaciones básicas cliente
    private void validateClientCommons(ClientBo clientBo) {
        ClientValidator.validateCuitFormat(clientBo.getCuit());
        ClientValidator.validateEmailFormat(clientBo.getEmail());
        ClientValidator.validateBirthDateFormat(clientBo.getBirthDate());
    }

    private void validateClientUpdate(ClientBo clientBo, Client saved){
        validateClientCommons(clientBo);
        var cuit = clientBo.getCuit();
        if (Objects.nonNull(cuit) && !clientBo.getCuit().equals(saved.getCuit()) && clientRepository.findByCuit(cuit).isPresent())
            throw new OperationNotValidException("client-already-exist",
                    String.format("Ya existe el cliente con cuit %s", cuit));
    }

    private void validateCreateClient(ClientBo clientBo) {
        validateClientCommons(clientBo);
        var clientId = clientBo.getId();
        var cuit = clientBo.getCuit();
        if (Objects.nonNull(cuit) && clientRepository.findByCuit(cuit).isPresent())
            throw new OperationNotValidException("client-already-exist",
                    String.format("Ya existe el cliente con cuit %s", cuit));
        if (Objects.nonNull(clientId) && clientRepository.findById(clientId).isPresent())
            throw new OperationNotValidException("client-already-exist",
                    String.format("Ya existe el cliente con id %s", clientId));
    }


}
