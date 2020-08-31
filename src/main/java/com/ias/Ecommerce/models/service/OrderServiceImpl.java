package com.ias.Ecommerce.models.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ias.Ecommerce.models.dao.OrderDAO;
import com.ias.Ecommerce.models.dao.ProductDAO;
import com.ias.Ecommerce.models.dao.ProductXorderDAO;
import com.ias.Ecommerce.models.dao.UserDAO;
import com.ias.Ecommerce.models.dao.UserXorderDAO;
import com.ias.Ecommerce.models.entity.Order;
import com.ias.Ecommerce.models.entity.Product;
import com.ias.Ecommerce.models.entity.ProductXorder;
import com.ias.Ecommerce.models.entity.User;
import com.ias.Ecommerce.models.entity.UserXorder;
import com.ias.Ecommerce.object.OrderStatus;
import com.ias.Ecommerce.object.RequestOrder;
import com.ias.Ecommerce.object.Response;
import com.ias.Ecommerce.object.UserType;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderDAO orderDAO;
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private ProductDAO productDAO;

	@Autowired
	private ProductXorderDAO productXorderDAO;
	
	@Autowired
	private UserXorderDAO userxorderDAO;

	@Override
	@Transactional
	public Response Create(RequestOrder order) {
		Response response= new Response();
		Optional<User> user;
		Optional<Product> product;
		Order newOrder= new Order();
		UserXorder userXorder;
		ProductXorder productXorder;
		List<Product> products= new ArrayList<>();
		boolean error=false;
		response.setError("");
		if(order!=null&&!order.getProductXorders().isEmpty()) {
			user= userDAO.findById(order.getUserXorders().get(0));
			try {
				if(user.isPresent()&&user.get().getType().equals(UserType.Cliente)) {
					for(int i=0;i<order.getProductXorders().size();i++) {
						product=productDAO.findById(order.getProductXorders().get(i).getProductProductId());
						if(product.isPresent()) {
							if(product.get().getInventoryQuantity()-order.getProductXorders().get(i).getQuantity()>=0) {
								product.get().setInventoryQuantity(product.get().getInventoryQuantity()-order.getProductXorders().get(i).getQuantity());
								products.add(product.get());
							}else{
								error=true;
								response.setSuccess(false);
								response.setError("El producto: "+product.get().getName()+" no tiene la suficiente canitdad en stock, "+response.getError());
							}
						}else {
							error=true;
							response.setSuccess(false);
							response.setError("El producto con id:"+order.getProductXorders().get(i).getProductProductId()+" no existe, "+response.getError());
						}
					}
					if(!error) {
						newOrder=inicializarOrden(order, user.get());
						newOrder=orderDAO.save(newOrder);
						for(int i=0;i<products.size();i++) {
							productDAO.save(products.get(i));
							productXorder=order.getProductXorders().get(i);
							productXorder.setOrderOrderId(newOrder.getOrderId());
							productXorderDAO.save(productXorder);
						}
						userXorder= new UserXorder();
						userXorder.setOrderOrderId(newOrder.getOrderId());
						userXorder.setUserEmail(order.getUserXorders().get(0));
						userxorderDAO.save(userXorder);
						response.setSuccess(false);
						response.setError("Orden creada");
					}
				}else {
					response.setSuccess(false);
					response.setError("El usuario no tiene permiso para realizar esta operación");
				}
				
			}
			catch (Exception e) {
				response.setSuccess(false);
				if(e.getCause().getCause()!=null) {
					response.setError(e.getCause().getCause().getMessage());
				}else {
					response.setError(e.getCause().getMessage());
				}
			}
		}else {
			response.setSuccess(false);
			response.setError("La orden esta nula o vacia");
		}
		
		return response;
	}

	@Override
	public Response FindAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response Delete(RequestOrder order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response FindByUser(RequestOrder order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response ChangeStatus(RequestOrder order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response AddProduct(RequestOrder order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response DeleteProduct(RequestOrder order) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private Order inicializarOrden(RequestOrder requestOrder, User user) {
		Order order= new Order();
		if(user.getOrders()%3==0) {
			order.setDiscount(true);
		}
		order.setState(OrderStatus.Registrada);
		order.setTotalBasePrice(requestOrder.getTotalBasePrice());
		order.setTotalPrice(requestOrder.getTotalPrice());
		order.setTotalTaxPrice(requestOrder.getTotalTaxPrice());
		return order;
	}

}
