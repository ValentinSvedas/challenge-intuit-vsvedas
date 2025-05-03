package com.evaluacion.intuit.client.application;


import com.evaluacion.intuit.client.application.exceptions.NotFoundException;
import com.evaluacion.intuit.client.application.exceptions.OperationNotValidException;
import com.evaluacion.intuit.client.domain.businessobject.ClientBo;
import com.evaluacion.intuit.client.domain.entity.Client;
import com.evaluacion.intuit.client.infrastructure.output.persistence.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientServiceImplTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientServiceImpl clientService;

    private ClientBo validClientBo;
    private Client validClientEntity;

    @BeforeEach
    void setUp() {
        validClientBo = new ClientBo(1, "Lucas", "Rodriguez", LocalDate.of(1990, 10, 1),"example@gmail.com",
                "20420833173", "2494496847", "Avenida Callao 222");

        validClientEntity = validClientBo.toEntity();
    }

    @Test
    void findAll_ShouldReturnAllClients() {
        // Arrange
        Pageable pageable = PageRequest.of(0, 10);
        List<Client> mockEntities = List.of(validClientEntity);
        Page<Client> mockPage = new PageImpl<>(mockEntities, pageable, 1);

        when(clientRepository.findAll(any(Pageable.class))).thenReturn(mockPage);

        // Act
        Page<ClientBo> result = clientService.findAll(pageable);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals(validClientBo.getId(), result.getContent().get(0).getId());
        verify(clientRepository, times(1)).findAll(pageable);
    }

    @Test
    void findById_WithExistingId_ShouldReturnClient() {
        // Arrange
        when(clientRepository.findById(1)).thenReturn(Optional.of(validClientEntity));

        // Act
        ClientBo result = clientService.findById(1);

        // Assert
        assertNotNull(result);
        assertEquals(validClientBo.getId(), result.getId());
        verify(clientRepository, times(1)).findById(1);
    }

    @Test
    void findById_WithNonExistingId_ShouldThrowNotFoundException() {
        // Arrange
        when(clientRepository.findById(99)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NotFoundException.class, () -> clientService.findById(99));
        verify(clientRepository, times(1)).findById(99);
    }

    @Test
    void findByName_ShouldReturnPagedResults() {
        // Arrange
        Pageable pageable = PageRequest.of(0, 10);
        when(clientRepository.findAllByName("JORGE AVAST", pageable))
                .thenReturn(new PageImpl<>(List.of(validClientEntity)));

        // Act
        Page<ClientBo> result = clientService.findByName("Jorge Avast", pageable);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals(validClientBo.getName(), result.getContent().get(0).getName());
        verify(clientRepository, times(1)).findAllByName("JORGE AVAST", pageable);
    }

    @Test
    void create_WithValidClient_ShouldReturnSavedClient() {
        // Arrange
        when(clientRepository.save(any(Client.class))).thenReturn(validClientEntity);
        when(clientRepository.findByCuit(validClientBo.getCuit())).thenReturn(Optional.empty());

        // Act
        ClientBo result = clientService.create(validClientBo);

        // Assert
        assertNotNull(result);
        assertEquals(validClientBo.getId(), result.getId());
        verify(clientRepository, times(1)).save(any(Client.class));
    }

    @Test
    void create_WithExistingCuit_ShouldThrowOperationNotValidException() {
        // Arrange
        when(clientRepository.findByCuit(validClientBo.getCuit())).thenReturn(Optional.of(validClientEntity));

        // Act & Assert
        assertThrows(OperationNotValidException.class, () -> clientService.create(validClientBo));
        verify(clientRepository, never()).save(any(Client.class));
    }

    @Test
    void create_WithInvalidCuit_ShouldThrowOperationNotValidException() {
        // Arrange
        ClientBo invalidClient = ClientBo.builder()
                .cuit("123")
                .build();

        // Act & Assert
        assertThrows(OperationNotValidException.class, () -> clientService.create(invalidClient));
        verify(clientRepository, never()).save(any(Client.class));
    }

    @Test
    void update_WithValidClient_ShouldReturnUpdatedClient() {
        // Arrange
        when(clientRepository.findById(1)).thenReturn(Optional.of(validClientEntity));
        when(clientRepository.save(any(Client.class))).thenReturn(validClientEntity);

        // Act
        ClientBo result = clientService.update(1, validClientBo);

        // Assert
        assertNotNull(result);
        assertEquals(validClientBo.getId(), result.getId());
        verify(clientRepository, times(1)).save(any(Client.class));
    }

    @Test
    void update_WithNonExistingId_ShouldThrowNotFoundException() {
        // Arrange
        when(clientRepository.findById(1)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NotFoundException.class, () -> clientService.update(1, validClientBo));
        verify(clientRepository, never()).save(any(Client.class));
    }

    @Test
    void update_WithExistingCuitForDifferentClient_ShouldThrowOperationNotValidException() {
        // Arrange
        ClientBo existingClientWithSameCuit = ClientBo.builder().id(2).cuit(validClientBo.getCuit()).build();
        var client = new Client();
        client.setId(2);
        client.setCuit(existingClientWithSameCuit.getCuit());

        when(clientRepository.findById(existingClientWithSameCuit.getId())).thenReturn(Optional.of(client));

        // Act & Assert
        assertThrows(OperationNotValidException.class, () -> clientService.update(2, existingClientWithSameCuit));
        verify(clientRepository, never()).save(any(Client.class));
    }
}