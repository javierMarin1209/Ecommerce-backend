package com.ias.Ecommerce.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import com.ias.Ecommerce.models.entity.ProductXorder;

public interface ProductXorderDAO extends CrudRepository<ProductXorder, Integer>,QueryByExampleExecutor<ProductXorder>{
	
	@Query("SELECT p.id FROM ProductXorder p WHERE (p.orderOrderId) = (?1)")
	List<Integer> FindByorder(int id);
	
	@Query("SELECT p FROM ProductXorder p WHERE (p.orderOrderId) = (?1)")
	List<ProductXorder> FindProductsByorder(int id);

}
