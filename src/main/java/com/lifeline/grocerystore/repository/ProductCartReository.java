package com.lifeline.grocerystore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lifeline.grocerystore.model.Product;

public interface ProductCartReository extends JpaRepository<Product, Long>
{

}
