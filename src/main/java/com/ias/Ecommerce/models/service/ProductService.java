package com.ias.Ecommerce.models.service;

import com.ias.Ecommerce.object.RequestProduct;
import com.ias.Ecommerce.object.Response;

public interface ProductService {
	
	public Response Create(RequestProduct requestProduct);
	
	public Response FindAll();
	
	public Response	FindAllPublic();
	
	public Response Update(RequestProduct requestProduct);
	
	public Response Delete(RequestProduct requestProduct);
	
	public Response FindByName(RequestProduct requestProduct);

}
