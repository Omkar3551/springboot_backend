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
import com.lifeline.grocerystore.repository.ProductCartReository;



@RestController
@CrossOrigin("https://grocery-store-management-react.vercel.app/")//CONNECT FRONTEND-BACKEND
public class ProductCartController 
{
	@Autowired
	private ProductCartReository productCartReository;
	
	@PostMapping("/productcart")
		Product newProduct(@RequestBody Product newProduct)
		{
		return productCartReository.save(newProduct);
		}
	
	@GetMapping("/productcart")
	List<Product> getAllProducts()
	{
		return productCartReository.findAll();
	}
	
	@GetMapping("/productcart/{id}")
	Product getProductById(@PathVariable Long id)
	{
		return productCartReository.findById(id)
				.orElseThrow(()->new ProductNotFoundException(id));
	}
	
	@PutMapping("/productcart/{id}")
	Product updateProduct(@RequestBody Product newProduct, @PathVariable Long id)
	{
		return productCartReository.findById(id)
				.map(product ->{
					product.setProductName(newProduct.getProductName());
					product.setPrice(newProduct.getPrice());
					product.setQuantity(newProduct.getQuantity());
					return productCartReository.save(product);
					
				}).orElseThrow(()-> new ProductNotFoundException(id));
	}
	
	@DeleteMapping("/productcart/{id}")
	String deleteproduct(@PathVariable Long id) 
	{
		if (!productCartReository.existsById(id)) {
			throw new ProductNotFoundException(id);
		}
		productCartReository.deleteById(id);
		return "Product with id" + id + "has been deleted succesfully";
	}

}
