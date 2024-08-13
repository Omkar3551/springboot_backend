package com.lifeline.grocerystore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lifeline.grocerystore.model.User;



public interface UserRepository extends JpaRepository<User, Long>
{
	

}
