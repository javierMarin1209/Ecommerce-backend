package com.ias.Ecommerce.models.service;

import com.ias.Ecommerce.models.entity.Order;
import com.ias.Ecommerce.object.RequestOrder;
import com.ias.Ecommerce.object.Response;

public interface OrderService {
	
	public Response Create(RequestOrder order);
	
	public Response FindAll();
	
	public Response Delete(RequestOrder order);
	
	public Response FindByUser(RequestOrder email);
	
	public Response ChangeStatus(RequestOrder order);
	
	public Response AddProduct(RequestOrder order);
	
	public Response DeleteProduct(RequestOrder order);

}
