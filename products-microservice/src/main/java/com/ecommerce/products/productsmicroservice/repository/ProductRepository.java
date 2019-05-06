package com.ecommerce.products.productsmicroservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ecommerce.products.productsmicroservice.model.Products;


@Repository
public interface ProductRepository extends JpaRepository<Products, Long> {
	
}
