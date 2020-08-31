package com.ias.Ecommerce.models.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import com.ias.Ecommerce.models.entity.ProductXorder;

public interface ProductXorderDAO extends CrudRepository<ProductXorder, Integer>,QueryByExampleExecutor<ProductXorder>{

}
