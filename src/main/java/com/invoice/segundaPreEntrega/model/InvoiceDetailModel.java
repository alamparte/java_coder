package com.invoice.segundaPreEntrega.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "invoice_details")
public class InvoiceDetailModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "invoice_id", nullable = false)
    private InvoiceModel invoice;
    private int quantity;
    @ManyToOne
    @JoinColumn(name = "products_id", nullable = false)
    private ProductModel product;
    private double price;
}
