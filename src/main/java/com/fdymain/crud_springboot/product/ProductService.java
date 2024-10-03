package com.fdymain.crud_springboot.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    HashMap<String, Object> datos;

    @Autowired
    public ProductService (ProductRepository productRepository){
        this.productRepository=productRepository;
    }

    public List<Products> getProducts(){
        return this.productRepository.findAll();
    }

    public ResponseEntity<Object> newProduct(Products products) {
        Optional<Products> response = productRepository.findProductByName(products.getName());
        datos = new HashMap<>();

        if (response.isPresent() && products.getId() == null){
            datos.put("Error",true);
            datos.put("mensaje","Ya existe un producto con ese nombre");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        if (products.getId() == null){
            datos.put("mensaje","Se creó el producto con exito");
        }
        else if (products.getId() != null){
            datos.put("mensaje","Se actualizó el producto con exito");
        }

        productRepository.save(products);
        datos.put("data",products);
        return new ResponseEntity<>(
                datos,
                HttpStatus.CREATED
        );
    }
    public ResponseEntity<Object> deleteProduct(Long productId){
        datos = new HashMap<>();
        boolean existe = this.productRepository.existsById(productId);
        if (!existe){
            datos.put("Error",true);
            datos.put("mensaje","No existe un producto con ese ID");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        } else {
            productRepository.deleteById(productId);
            datos.put("mensaje","Producto eliminado");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.ACCEPTED
            );
        }
    }
}
