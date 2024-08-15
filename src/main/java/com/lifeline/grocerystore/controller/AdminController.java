package com.lifeline.grocerystore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lifeline.grocerystore.exception.UserNotFoundException;
import com.lifeline.grocerystore.model.Admin;
import com.lifeline.grocerystore.model.User;
import com.lifeline.grocerystore.repository.AdminRepository;

@RestController

@CrossOrigin("https://grocery-store-management-react.vercel.app//") //CONNECT FRONTEND-BACKEND
public class AdminController 
{
	@Autowired
	private AdminRepository adminRepository;
	
	@PostMapping("/admin")
	Admin newAdmin(@RequestBody Admin newAdmin) 
	{
		return adminRepository.save(newAdmin);
	}
	
	@GetMapping("/admins")
	List<Admin> getAllAdmin()
	{
		return adminRepository.findAll();
	}
	
	@GetMapping("/admin/{id}")
	Admin getAdminById(@PathVariable Long id) 
	{
		return adminRepository.findById(id)
		.orElseThrow(()->new UserNotFoundException(id)); //handling exception if user not found	
	}
	
	@PutMapping("/admin/{id}")
	Admin updateAdmin(@RequestBody Admin newAdmin, @PathVariable Long id) 
	{
		return adminRepository.findById(id)
				.map(admin ->{
					admin.setEmail(newAdmin.getEmail());
					admin.setName(newAdmin.getName());
					admin.setPassword(newAdmin.getPassword());
					return adminRepository.save(admin);
				}).orElseThrow(()-> new UserNotFoundException(id));
	}
	
	@DeleteMapping("/admin/{id}")
	String deleteAdmin(@PathVariable Long id)
	{
		if(!adminRepository.existsById(id)) {
			throw new UserNotFoundException(id);
		}
		adminRepository.deleteById(id);
		return "Admin with" + id + "Deleted Sucessfully";
	}
}
