package com.invoice.segundaPreEntrega.repository;

import com.invoice.segundaPreEntrega.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductModel, Integer> {
    Optional<ProductModel> findByCode(String code);
}
