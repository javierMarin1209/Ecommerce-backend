package com.ias.Ecommerce.models.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import com.ias.Ecommerce.models.entity.User;

public interface UserDAO extends CrudRepository<User, String>,QueryByExampleExecutor<String> {
	

}
