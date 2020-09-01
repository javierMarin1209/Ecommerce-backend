package com.ias.Ecommerce.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import com.ias.Ecommerce.models.entity.UserXorder;

public interface UserXorderDAO extends CrudRepository<UserXorder, Integer>,QueryByExampleExecutor<UserXorder>{

	@Query("SELECT p.id FROM UserXorder p WHERE (p.orderOrderId) = (?1)")
	List<Integer> FindByorder(int id);
	
	@Query("SELECT p.orderOrderId FROM UserXorder p WHERE (p.userEmail) = (?1)")
	List<Integer> FindByuser(String email);
	
	@Query("SELECT p FROM UserXorder p WHERE (p.orderOrderId) = (?1)")
	UserXorder FindOneByorder(int id);
	
}
