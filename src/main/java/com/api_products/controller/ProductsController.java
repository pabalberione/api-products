package com.api_products.controller;

import com.api_products.model.Products;
import com.api_products.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products/")
public class ProductsController {
    @Autowired
    ProductsRepository productsRepository;

    //Obtener todos los productos
    @GetMapping
    public List<Products> getAllProducts(){
        return productsRepository.findAll();
    }


    //Crear un nuevo producto
    @PostMapping
    public Products createProduct(@RequestBody Products products){
        return productsRepository.save(products);
    }

    //Obtener un producto por Id
    @GetMapping("{id}")
    public ResponseEntity<Products>getProductById(@PathVariable Integer id){
        Products products = productsRepository.findById(id).orElse(null);
        if(products == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(products);
    }

    //Actualizar un producto
    @PutMapping("{id}")
    public ResponseEntity<Products>updateProductById(@PathVariable Integer id, @RequestBody Products productsDetails){
        Products products = productsRepository.findById(id).orElse(null);
        if(products == null){
            return ResponseEntity.notFound().build();
        }
        products.setName(productsDetails.getName());
        products.setDescription(productsDetails.getDescription());
        products.setPrice(productsDetails.getPrice());
        products.setQuantity(productsDetails.getQuantity());
        Products newProduct = productsRepository.save(products);
        return ResponseEntity.ok(newProduct);
    }

    //Eliminar un producto
    @DeleteMapping("{id}")
    public ResponseEntity<Products> deleteProductById(@PathVariable Integer id){
        Products products = productsRepository.findById(id).orElse(null);
        if(products == null){
            return ResponseEntity.notFound().build();
        }
        productsRepository.delete(products);
        return ResponseEntity.noContent().build();
    }
}
