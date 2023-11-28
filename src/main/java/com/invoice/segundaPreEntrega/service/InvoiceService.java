package com.invoice.segundaPreEntrega.service;

import com.invoice.segundaPreEntrega.model.ClientModel;
import com.invoice.segundaPreEntrega.model.InvoiceDetailModel;
import com.invoice.segundaPreEntrega.model.InvoiceModel;
import com.invoice.segundaPreEntrega.repository.ClientRepository;
import com.invoice.segundaPreEntrega.repository.InvoiceDetailRepository;
import com.invoice.segundaPreEntrega.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class InvoiceService {
    @Autowired
    InvoiceRepository invoiceRepository;
    @Autowired
    InvoiceDetailService invoiceDetailService;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private InvoiceDetailRepository invoiceDetailRepository;

    //post método - crear factura
    public InvoiceModel createInvoice(InvoiceModel newInvoice){
        // Verifica si el cliente existe
        ClientModel invoiceClient = newInvoice.getClient();
        if (invoiceClient == null || invoiceClient.getId() == null) {
            throw new IllegalArgumentException("El cliente en la factura es nulo o no tiene un ID válido.");
        }
        Optional<ClientModel> client = clientRepository.findById(invoiceClient.getId());
        if (client.isEmpty()) {
            throw new IllegalArgumentException("El cliente no existe.");
        }
        // se asigna el cliente al invoice
        newInvoice.setClient(client.get());
        // se asigna la fecha al momento de creación
        newInvoice.setCreatedAt(LocalDate.now());

        // se estable relación antes de guardar el invoice completo
        for (InvoiceDetailModel detail : newInvoice.getInvoiceDetails()) {
            detail.setInvoice(newInvoice);
        }
        // el total del invoice
        double total = invoiceDetailService.calculateTotal(newInvoice.getInvoiceDetails());
        newInvoice.setTotal(total);

        // se guarda el invoice en la base de datos
        InvoiceModel savedInvoice = invoiceRepository.save(newInvoice);

        // se guardan los detalles de la factura después de haber guardado el invoice completo
        for (InvoiceDetailModel detail : newInvoice.getInvoiceDetails()) {
            detail.setInvoice(savedInvoice);
            invoiceDetailRepository.save(detail);
        }
        return savedInvoice;
    }
    // obtener factura por ID
    public InvoiceModel findInvoiceById(Integer id) {
        return invoiceRepository.findById(id).orElse(null);
    }
}
