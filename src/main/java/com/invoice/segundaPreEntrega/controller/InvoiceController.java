package com.invoice.segundaPreEntrega.controller;

import com.invoice.segundaPreEntrega.model.InvoiceModel;
import com.invoice.segundaPreEntrega.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RequestMapping(path = "api/invoice")
@RestController
public class InvoiceController {
    @Autowired
    private InvoiceService invoiceService;

    //post método - crear factura
    @PostMapping("/")
    public ResponseEntity<InvoiceModel> createInvoice(@RequestBody InvoiceModel invoice){
        return new ResponseEntity<>(this.invoiceService.createInvoice(invoice), HttpStatus.CREATED);
    }
    // obtener factura por ID
    @GetMapping("/{id}")
    public ResponseEntity<InvoiceModel> findInvoiceById(@PathVariable Integer id) {
        InvoiceModel invoice = invoiceService.findInvoiceById(id);
        if (invoice == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Factura no encontrada. El número de id "+id+" no existe.");
        }
        return new ResponseEntity<>(invoice, HttpStatus.OK);
    }
}
