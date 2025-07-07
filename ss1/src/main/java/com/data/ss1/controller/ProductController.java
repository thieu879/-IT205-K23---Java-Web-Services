package com.data.ss1.controller;

import com.data.ss1.entity.Product;
import com.data.ss1.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private  ProductService productService;
    @GetMapping("/products")
    public String getProducts(Model model) {
        List<Product> products = productService.getProducts();
        if (products == null) {
            products = new ArrayList<>();
        }
        model.addAttribute("products", products);
        return "products";
    }
    @PostMapping("/products")
    public String saveProduct(Product product) {
        productService.saveProduct(product);
        return "redirect:/products";
    }
    @PostMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }

    @PostMapping("/products/{id}")
    public String updateProduct(@PathVariable Long id, Product product) {
        productService.updateProduct(id, product);
        return "redirect:/products";
    }

}
