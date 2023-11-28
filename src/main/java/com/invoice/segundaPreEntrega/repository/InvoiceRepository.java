package com.invoice.segundaPreEntrega.repository;

import com.invoice.segundaPreEntrega.model.InvoiceModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<InvoiceModel, Integer> {
}
