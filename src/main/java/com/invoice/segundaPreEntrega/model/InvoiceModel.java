package com.invoice.segundaPreEntrega.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name= "invoice")
public class InvoiceModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "clients_id")
    private ClientModel client;
    @Column(name = "created_at")
    private LocalDate createdAt;
    private double total;
    @OneToMany(mappedBy = "invoice")
    @JsonManagedReference
    private List<InvoiceDetailModel> invoiceDetails;
}
