package com.evaluacion.intuit.client.infrastructure.input;

import com.evaluacion.intuit.client.application.ClientService;
import com.evaluacion.intuit.client.infrastructure.input.mappers.ClientMapper;
import com.evaluacion.intuit.client.infrastructure.input.request.ClientRequest;
import com.evaluacion.intuit.client.infrastructure.input.response.ClientResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/client")
public class ClientController {

    private final ClientService clientService;
    private final ClientMapper clientMapper;

    @GetMapping
    public ResponseEntity<List<ClientResponse>> getAll() {
        var allClients = clientService.findAll();
        return ResponseEntity.ok(clientMapper.toResponseList(allClients));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientResponse> getById(@PathVariable("id") Integer id) {
        ClientResponse response = clientMapper.toResponse(clientService.findById(id));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/by-name/{name}")
    public ResponseEntity<Page<ClientResponse>> getByName(
            @PathVariable("name") String name,
            Pageable page) {
        Page<ClientResponse> response = clientService.findByName(name, page)
                .map(clientMapper::toResponse);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody ClientRequest clientRequest) {
        var clientBo = clientRequest.toBo();
        clientService.create(clientBo);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(
            @PathVariable("id") Integer id,
            @Valid @RequestBody ClientRequest clientRequest) {
        var clientBo = clientRequest.toBo();
        clientService.update(id, clientBo);
        return ResponseEntity.noContent().build();
    }
}
