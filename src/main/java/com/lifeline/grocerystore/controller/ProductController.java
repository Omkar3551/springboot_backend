package com.lifeline.grocerystore.controller;
import java.util.List;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lifeline.grocerystore.exception.ProductNotFoundException;
import com.lifeline.grocerystore.model.Product;
import com.lifeline.grocerystore.repository.ProductReository;


@RestController
@CrossOrigin("https://grocery-store-management-react.vercel.app/")//CONNECT FRONTEND-BACKEND
public class ProductController 
{
	@Autowired
	private ProductReository productReository;
	
	@PostMapping("/product")
		Product newProduct(@RequestBody Product newProduct)
		{
		return productReository.save(newProduct);
		}
	
	@GetMapping("/products")
	List<Product> getAllProducts()
	{
		return productReository.findAll();
	}
	
	@GetMapping("/product/{id}")
	Product getProductById(@PathVariable Long id)
	{
		return productReository.findById(id)
				.orElseThrow(()->new ProductNotFoundException(id));
	}
	
	@PutMapping("/product/{id}")
	Product updateProduct(@RequestBody Product newProduct, @PathVariable Long id)
	{
		return productReository.findById(id)
				.map(product ->{
					product.setProductName(newProduct.getProductName());
					product.setPrice(newProduct.getPrice());
					product.setQuantity(newProduct.getQuantity());
					return productReository.save(product);
					
				}).orElseThrow(()-> new ProductNotFoundException(id));
	}
	
	@DeleteMapping("/product/{id}")
	String deleteproduct(@PathVariable Long id) 
	{
		if (!productReository.existsById(id)) {
			throw new ProductNotFoundException(id);
		}
		productReository.deleteById(id);
		return "Product with id" + id + "has been deleted succesfully";
	}

}
