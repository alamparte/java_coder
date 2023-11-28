package com.invoice.segundaPreEntrega.repository;

import com.invoice.segundaPreEntrega.model.InvoiceDetailModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceDetailRepository extends JpaRepository<InvoiceDetailModel, Integer> {
}
