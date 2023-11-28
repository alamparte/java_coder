package com.invoice.segundaPreEntrega;

import com.invoice.segundaPreEntrega.repository.ClientRepository;
import com.invoice.segundaPreEntrega.repository.InvoiceDetailRepository;
import com.invoice.segundaPreEntrega.repository.InvoiceRepository;
import com.invoice.segundaPreEntrega.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SegundaPreEntregaApplication {

	@Autowired
	ClientRepository clientRepository;
	@Autowired
	InvoiceDetailRepository invoiceDetailRepository;
	@Autowired
	InvoiceRepository invoiceRepository;
	@Autowired
	ProductRepository productRepository;

	public static void main(String[] args) {
		SpringApplication.run(SegundaPreEntregaApplication.class, args);
	}

}
