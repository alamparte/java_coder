package com.invoice.segundaPreEntrega.service;

import com.invoice.segundaPreEntrega.model.ProductModel;
import com.invoice.segundaPreEntrega.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    // crear producto
    public ProductModel createProduct(ProductModel newProduct){
        return this.productRepository.save(newProduct);
    }
    // buscar producto por ID
    public ProductModel findById(Integer id){
        // en la variable productFound se guarda el producto encontrado
        Optional<ProductModel> productFound = this.productRepository.findById(id);
        // retorno el producto encontrado o null
        return productFound.orElse(null);
    }

    // buscar producto por codigo
    public ProductModel findByCode(String code){
        Optional<ProductModel> productFound = this.productRepository.findByCode(code);
        // retorno el producto encontrado o null
        return productFound.orElse(null);
    }

    // obtener la lista de todos los productos
    public List<ProductModel> findAll(){
        return this.productRepository.findAll();
    }

    // modificar producto
    public ProductModel update(ProductModel newProduct, Integer id){
        Optional<ProductModel> productDB = this.productRepository.findById(id);

        if(productDB.isEmpty()){
            return null;
        }
        ProductModel updatedProduct = productDB.get();
        updatedProduct.setDescription( newProduct.getDescription() );
        updatedProduct.setCode( newProduct.getCode() );
        updatedProduct.setStock( newProduct.getStock() );
        updatedProduct.setPrice( newProduct.getPrice() );
        return this.productRepository.save(updatedProduct);
    }
    // eliminar un producto
    public void delete(Integer id){
        this.productRepository.deleteById(id);
    }
    // actualizar stock
    public void updateStock(Integer id, int quantitySold) {
        Optional<ProductModel> productFound = productRepository.findById(id);

        if (productFound.isPresent()) {
            ProductModel product = productFound.get();
            int currentStock = product.getStock();
            int updatedStock = currentStock - quantitySold;

            if (updatedStock >= 0) {
                product.setStock(updatedStock);
                productRepository.save(product);
            } else {
                throw new IllegalArgumentException("No hay suficiente stock para realizar la venta.");
            }
        }else {
            throw new IllegalArgumentException("Producto no encontrado con el ID proporcionado: " + id);
        }
    }
}
