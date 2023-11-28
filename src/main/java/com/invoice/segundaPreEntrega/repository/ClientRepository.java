package com.invoice.segundaPreEntrega.repository;

import com.invoice.segundaPreEntrega.model.ClientModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<ClientModel, Integer> {
    Optional<ClientModel> findByDocNumber(String docNumber);
}
