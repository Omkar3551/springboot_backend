package com.lifeline.grocerystore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lifeline.grocerystore.model.Admin;

public interface AdminRepository extends  JpaRepository<Admin, Long>
{

}
