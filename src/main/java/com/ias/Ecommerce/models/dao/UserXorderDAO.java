package com.ias.Ecommerce.models.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import com.ias.Ecommerce.models.entity.UserXorder;

public interface UserXorderDAO extends CrudRepository<UserXorder, Integer>,QueryByExampleExecutor<UserXorder>{

}
