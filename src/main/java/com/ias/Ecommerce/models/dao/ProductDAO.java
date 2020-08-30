package com.ias.Ecommerce.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import com.ias.Ecommerce.models.entity.Product;

public interface ProductDAO extends CrudRepository<Product, Integer>,QueryByExampleExecutor<Product>{
	
	@Query("SELECT p FROM Product p WHERE (p.name) LIKE (?1)")
	List<Product> FindByName(String name);
}
