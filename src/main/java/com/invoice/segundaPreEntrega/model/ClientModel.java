package com.invoice.segundaPreEntrega.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name= "clients")
public class ClientModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String surname;
    @Column(name = "doc_number")
    private String docNumber;
}
