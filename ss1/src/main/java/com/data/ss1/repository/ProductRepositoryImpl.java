package com.data.ss1.repository;

import com.data.ss1.entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<Product> getProducts() {
        return entityManager.createQuery("SELECT p FROM Product p", Product.class).getResultList();
    }

    @Override
    public Product getProductById(Long id) {
        return entityManager.find(Product.class, id);
    }

    @Override
    public Product saveProduct(Product product) {
        entityManager.persist(product);
        return product;
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        Product existingProduct = entityManager.find(Product.class, id);
        if (existingProduct != null) {
            existingProduct.setName(product.getName());
            existingProduct.setPrice(product.getPrice());
            entityManager.merge(existingProduct);
            return existingProduct;
        }
        return null;
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = entityManager.find(Product.class, id);
        if (product != null) {
            entityManager.remove(product);
        }
    }
}
