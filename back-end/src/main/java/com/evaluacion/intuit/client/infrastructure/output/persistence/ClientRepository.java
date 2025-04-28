package com.evaluacion.intuit.client.infrastructure.output.persistence;

import com.evaluacion.intuit.client.domain.entity.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    @Query(
            value = "SELECT c " +
                    "FROM  Client c " +
                    "WHERE UPPER(c.name) LIKE %:name% "
    )
    public Page<Client> findAllByName(@Param("name") String name, Pageable page);

    public Optional<Client> findByCuit(String cuit);
}
