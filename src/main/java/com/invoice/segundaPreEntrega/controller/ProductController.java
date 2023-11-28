package com.invoice.segundaPreEntrega.controller;

import com.invoice.segundaPreEntrega.model.ProductModel;
import com.invoice.segundaPreEntrega.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RequestMapping(path = "api/product")
@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    // crear producto
    @PostMapping("/")
    public ResponseEntity<ProductModel> createProduct(@RequestBody ProductModel product){
        return new ResponseEntity<>(this.productService.createProduct(product), HttpStatus.CREATED);
    }
    // buscar producto por ID
    @GetMapping("/{id}")
    public ResponseEntity<ProductModel> findById(@PathVariable Integer id){
        // en la variable productFound se guarda el producto encontrado
        ProductModel productFound = productService.findById(id);
        //en caso de ser null
        if (productFound == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado. El número de id "+id+" no existe.");
        }
        // retorno el producto encontrado
        return new ResponseEntity<>(productFound, HttpStatus.OK);
    }
    // buscar producto por codigo
    @GetMapping("/code/{code}")
    public ResponseEntity<ProductModel> findByCode(@PathVariable String code) {
        // en la variable productFound se guarda el productp encontrado
        ProductModel productFound = productService.findByCode(code);
        //en caso de ser null
        if (productFound == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado. El código "+code+" no está relacionado con ningún producto.");
        }
        // retorno el producto encontrado
        return new ResponseEntity<>(productFound, HttpStatus.OK);
    }
    // obtener la lista de todos los productos directamente de (path = "api/product")
    @GetMapping
    public ResponseEntity<List<ProductModel>> findAll(){
        return new ResponseEntity<>(this.productService.findAll(), HttpStatus.OK);
    }
    // editar un producto
    @PutMapping("/{id}")
    public ResponseEntity<ProductModel> update(@RequestBody ProductModel productUpdate, @PathVariable Integer id) {
        // en la variable productFound se guarda el producto encontrado
        ProductModel productFound = productService.update(productUpdate, id);
        //en caso de ser null
        if (productFound == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado. El número de id "+id+" no existe.");
        }
        // retorno el producto encontrado
        return new ResponseEntity<>(productFound, HttpStatus.OK);
    }
    // elimnar un producto
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id){
        ProductModel productFound = productService.findById(id);
        if (productFound == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado. El número de id "+id+" no existe.");
        }
        this.productService.delete(id);
        return new ResponseEntity<>("Producto eliminado correctamente.", HttpStatus.OK);
    }
}
