package com.invoice.segundaPreEntrega.service;

import com.invoice.segundaPreEntrega.model.InvoiceDetailModel;
import com.invoice.segundaPreEntrega.model.ProductModel;
import com.invoice.segundaPreEntrega.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceDetailService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductService productService;
   public double calculateTotal(List<InvoiceDetailModel> invoiceDetails) {
       double total = 0.0;

       for (InvoiceDetailModel detail : invoiceDetails) {
           // Verifica si el producto existe
           ProductModel product = productRepository.findById(detail.getProduct().getId()).orElse(null);
           if (product == null) {
               throw new IllegalArgumentException("El producto no existe.");
           }
           // Verifica si hay suficiente stock
           if (detail.getQuantity() > product.getStock()) {
               throw new IllegalArgumentException("Stock insuficiente para el producto: " + product.getDescription());
           }
           // Actualiza el stock del producto
           productService.updateStock(product.getId(), detail.getQuantity());
           // Calcula el subtotal del detalle
           double subtotal = detail.getPrice() * detail.getQuantity();
           // Acumula al total del invoice
           total += subtotal;
       }
       return total;
   }
}
