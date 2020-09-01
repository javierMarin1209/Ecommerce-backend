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
import com.ias.Ecommerce.object.ResponseOrder;
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
			user= userDAO.findById(order.getUserXorders());
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
						newOrder=inicializarOrden(order, user.get(),products);
						newOrder=orderDAO.save(newOrder);
						for(int i=0;i<products.size();i++) {
							productDAO.save(products.get(i));
							productXorder=order.getProductXorders().get(i);
							productXorder.setTotalPrice(products.get(i).getBasePrice()+(products.get(i).getTaxRate()*products.get(i).getBasePrice()));
							productXorder.setTaxPrice(products.get(i).getTaxRate());
							productXorder.setBasePrice(products.get(i).getBasePrice());
							productXorder.setProductName(products.get(i).getName());
							productXorder.setQuantity(order.getProductXorders().get(i).getQuantity());
							productXorder.setOrderOrderId(newOrder.getOrderId());
							productXorderDAO.save(productXorder);
						}
						userXorder= new UserXorder();
						userXorder.setOrderOrderId(newOrder.getOrderId());
						userXorder.setUserEmail(order.getUserXorders());
						userxorderDAO.save(userXorder);
						response.setSuccess(true);
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
		Response response= new Response();
		try {
			response.setResponse(orderDAO.findAll());
			response.setError("Consulta exitosa");
			response.setSuccess(true);
		}catch (Exception e) {
			response.setSuccess(false);
			if(e.getCause().getCause()!=null) {
				response.setError(e.getCause().getCause().getMessage());
			}else {
				response.setError(e.getCause().getMessage());
			}
		}
		return response;
	}

	@Override
	public Response Delete(RequestOrder order) {
		Response response= new Response();
		Optional<Order> optional;
		List<Integer> productXorders;
		List<Integer> userXorders;
		if(order!=null&&order.getOrderId()!=0) {
			try {
				optional=orderDAO.findById(order.getOrderId());
				if(optional.isPresent()&&optional.get().getState().equals(OrderStatus.Registrada)) {
					productXorders=productXorderDAO.FindByorder(order.getOrderId());
					userXorders=userxorderDAO.FindByorder(order.getOrderId());
					for(Integer id : productXorders) {
						productXorderDAO.deleteById(id);
					}
					for(Integer id : userXorders) {
						userxorderDAO.deleteById(id);
					}
					orderDAO.deleteById(order.getOrderId());
					response.setError("Orden eliminada");
					response.setSuccess(true);
				}else {
					response.setError("No existe la orden a eliminar");
					response.setSuccess(false);
				}
			}catch (Exception e) {
				response.setSuccess(false);
				if(e.getCause().getCause()!=null) {
					response.setError(e.getCause().getCause().getMessage());
				}else {
					response.setError(e.getCause().getMessage());
				}
			}
		}else {
			response.setError("No se a enviado una orden a eliminar");
			response.setSuccess(false);
		}
		return response;
	}

	@Override
	public Response FindByUser(RequestOrder order) {
		Response response= new Response();
		List<Integer> ids;
		List<Order> orders;
		List<ResponseOrder> responseOrders= new ArrayList<>();
		if(order!=null&&order.getUserXorders()!=null) {
			try {
				ids=userxorderDAO.FindByuser(order.getUserXorders());
				orders=(List<Order>) orderDAO.findAllById(ids);
				for(Order newOrder :orders) {
					responseOrders.add(crearOrden(newOrder,false));
				}
				response.setResponse(responseOrders);
				response.setError("Consulta exitosa");
				response.setSuccess(true);
			}catch (Exception e) {
				response.setSuccess(false);
				if(e.getCause().getCause()!=null) {
					response.setError(e.getCause().getCause().getMessage());
				}else {
					response.setError(e.getCause().getMessage());
				}
			}
		}else {
			response.setError("No se a enviado un usario para consultar");
			response.setSuccess(false);
		}
		
		return response;
	}
	
	@Override
	public Response FindOne(RequestOrder order) {
		Response response= new Response();
		Optional<Order> optional;
		if(order!=null&&order.getOrderId()!=0) {
			try {
				optional=orderDAO.findById(order.getOrderId());
				if(optional.isPresent()) {
					response.setResponse(crearOrden(optional.get(),true));
					response.setError("Consulta exitosa");
					response.setSuccess(true);
				}else {
					response.setError("No se a encontrado la orden a consultar");
					response.setSuccess(false);
				}
			}catch (Exception e) {
				response.setSuccess(false);
				if(e.getCause().getCause()!=null) {
					response.setError(e.getCause().getCause().getMessage());
				}else {
					response.setError(e.getCause().getMessage());
				}
			}
		}else {
			response.setError("No se a enviado una orden para consultar");
			response.setSuccess(false);
		}
		
		return response;
	}
	
	@Override
	@Transactional
	public Response PayOrder(RequestOrder order) {
		Response response= new Response();
		Optional<Order> optional;
		Optional<User> user;
		if(order!=null&&order.getOrderId()!=0&&order.getUserXorders()!=null) {
			try {
				user=userDAO.findById(order.getUserXorders());
				if(user.isPresent()&&user.get().getType().equals(UserType.Cliente)) {
					optional=orderDAO.findById(order.getOrderId());
					if(optional.isPresent()) {
						optional.get().setState(OrderStatus.Pagada);
						user.get().setOrders(user.get().getOrders()+1);
						if(user.get().getOrders()%3==0&&user.get().getOrders()!=0) {
							optional.get().setTotalPrice(optional.get().getTotalPrice()-(optional.get().getTotalPrice()*0.1));
							optional.get().setDiscount(true);
						}
						orderDAO.save(optional.get());
						userDAO.save(user.get());
						response.setError("orden pagada exitosamente");
						response.setSuccess(true);
					}else {
						response.setError("No se a encontrado la orden a pagar");
						response.setSuccess(false);
					}
				}else {
					response.setError("El cliente no existe o no tiene permisos para esto");
					response.setSuccess(false);
				}
				
			}catch (Exception e) {
				response.setSuccess(false);
				if(e.getCause().getCause()!=null) {
					response.setError(e.getCause().getCause().getMessage());
				}else {
					response.setError(e.getCause().getMessage());
				}
			}
		}else {
			response.setError("No se a enviado una orden para pagar");
			response.setSuccess(false);
		}
		
		return response;
	}

	@Override
	@Transactional
	public Response DeliveryOrder(RequestOrder order) {
		Response response= new Response();
		Optional<Order> optional;
		Optional<User> user;
		UserXorder xorder= new UserXorder();
		String body;
		Deliverymessage deliverymessage= new Deliverymessage();
		if(order!=null&&order.getOrderId()!=0&&order.getUserXorders()!=null) {
			try {
				user=userDAO.findById(order.getUserXorders());
				if(user.isPresent()&&!user.get().getType().equals(UserType.Cliente)) {
					optional=orderDAO.findById(order.getOrderId());
					if(optional.isPresent()&&optional.get().getState().equals(OrderStatus.Pagada)) {
						optional.get().setState(OrderStatus.Entregada);
						
						xorder=userxorderDAO.FindOneByorder(optional.get().getOrderId());
						body="Buen dia le informamos que su orden numero: "+order.getOrderId()+"A sido entregada.\nEsperamos que la disfute";
						deliverymessage.enviarConGMail(xorder.getUserEmail(),"Entrega de orden: "+order.getOrderId(),body);
						xorder= new UserXorder();
						xorder.setOrderOrderId(optional.get().getOrderId());
						xorder.setUserEmail(order.getUserXorders());
						userxorderDAO.save(xorder);
						orderDAO.save(optional.get());
						response.setError("orden entregada exitosamente");
						response.setSuccess(true);
					}else {
						response.setError("No se a encontrado la orden a entregar");
						response.setSuccess(false);
					}
				}else {
					response.setError("El usuario no existe o no tiene permisos para esto");
					response.setSuccess(false);
				}
				
			}catch (Exception e) {
				response.setSuccess(false);
				if(e.getCause().getCause()!=null) {
					response.setError(e.getCause().getCause().getMessage());
				}else {
					response.setError(e.getCause().getMessage());
				}
			}
		}else {
			response.setError("No se a enviado una orden para pagar");
			response.setSuccess(false);
		}
		
		return response;
	}

	@Override
	public Response AddProduct(RequestOrder order) {
		Response response= new Response();
		Optional<Order> optional;
		List<Product> products= new ArrayList<>();
		Optional<Product> product;
		ProductXorder productXorder;
		boolean error=false;
		if(order!=null&&order.getOrderId()!=0&&!order.getProductXorders().isEmpty()) {
			try {
				optional=orderDAO.findById(order.getOrderId());
				if(optional.isPresent()&&optional.get().getState().equals(OrderStatus.Registrada)) {
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
						orderDAO.save(addDeleteProductOrden(order, optional.get(), products, false));
						for(int i=0;i<products.size();i++) {
							productDAO.save(products.get(i));
							productXorder=order.getProductXorders().get(i);
							productXorder.setTotalPrice(products.get(i).getBasePrice()+(products.get(i).getTaxRate()*products.get(i).getBasePrice()));
							productXorder.setTaxPrice(products.get(i).getTaxRate());
							productXorder.setBasePrice(products.get(i).getBasePrice());
							productXorder.setProductName(products.get(i).getName());
							productXorder.setQuantity(order.getProductXorders().get(i).getQuantity());
							productXorder.setOrderOrderId(order.getOrderId());
							productXorderDAO.save(productXorder);
							response.setSuccess(true);
							response.setError("Productos agregados a la orden");
						}
					}
				}else {
					response.setError("No se a encontrado la orden o esta ya no permite agregar productos");
					response.setSuccess(false);
				}
			}catch (Exception e) {
				response.setSuccess(false);
				if(e.getCause().getCause()!=null) {
					response.setError(e.getCause().getCause().getMessage());
				}else {
					response.setError(e.getCause().getMessage());
				}
			}
		}else {
			response.setError("No se a enviado una orden o producto");
			response.setSuccess(false);
		}
		return response;
	}

	@Override
	public Response DeleteProduct(RequestOrder order) {
		Response response= new Response();
		Optional<Order> optional;
		List<Product> products= new ArrayList<>();
		Optional<Product> product;
		boolean error=false;
		if(order!=null&&order.getOrderId()!=0&&!order.getProductXorders().isEmpty()) {
			try {
				optional=orderDAO.findById(order.getOrderId());
				if(optional.isPresent()&&optional.get().getState().equals(OrderStatus.Registrada)) {
					for(int i=0;i<order.getProductXorders().size();i++) {
						product=productDAO.findById(order.getProductXorders().get(i).getProductProductId());
						if(product.isPresent()) {
							product.get().setInventoryQuantity(product.get().getInventoryQuantity()+order.getProductXorders().get(i).getQuantity());
							products.add(product.get());
						}else {
							error=true;
							response.setSuccess(false);
							response.setError("El producto con id:"+order.getProductXorders().get(i).getProductProductId()+" no existe, "+response.getError());
						}
					}
					if(!error) {
						orderDAO.save(addDeleteProductOrden(order, optional.get(), products, true));
						for(int i=0;i<order.getProductXorders().size();i++) {
							productDAO.save(products.get(i));
							productXorderDAO.deleteById(order.getProductXorders().get(i).getId());
							response.setSuccess(true);
							response.setError("Productos eliminados de la orden");
						}
					}
				}else {
					response.setError("No se a encontrado la orden o esta ya no permite eliminar productos");
					response.setSuccess(false);
				}
			}catch (Exception e) {
				response.setSuccess(false);
				if(e.getCause().getCause()!=null) {
					response.setError(e.getCause().getCause().getMessage());
				}else {
					response.setError(e.getCause().getMessage());
				}
			}
		}else {
			response.setError("No se a enviado una orden o producto");
			response.setSuccess(false);
		}
		return response;
	}

	@Override
	public Response FindAllByStatus(Order order) {
		Response response= new Response();
		try {
			response.setResponse(orderDAO.FindBystatus(order.getState()));
			response.setError("Consulta exitosa");
			response.setSuccess(true);
		}catch (Exception e) {
			response.setSuccess(false);
			if(e.getCause().getCause()!=null) {
				response.setError(e.getCause().getCause().getMessage());
			}else {
				response.setError(e.getCause().getMessage());
			}
		}
		return response;
	}

	@Override
	public Response CountAllByStatus(Order order) {
		Response response= new Response();
		try {
			response.setResponse(orderDAO.CountAllByStatus(order.getState()));
			response.setError("Consulta exitosa");
			response.setSuccess(true);
		}catch (Exception e) {
			response.setSuccess(false);
			if(e.getCause().getCause()!=null) {
				response.setError(e.getCause().getCause().getMessage());
			}else {
				response.setError(e.getCause().getMessage());
			}
		}
		return response;
	}
	
	private Order inicializarOrden(RequestOrder requestOrder, User user,List<Product> products) {
		Order order= new Order();
		double totalPrice=0,totalBase=0,totalTax=0;
		double auxTotalTax=0;
		if(user.getOrders()%3==0&&user.getOrders()!=0) {
			order.setDiscount(true);
		}
		for(Product product:products) {
			auxTotalTax=0;
			auxTotalTax=(product.getBasePrice()*product.getTaxRate());
			totalPrice+=(product.getBasePrice()+auxTotalTax);
			totalBase+=product.getBasePrice();
			totalTax+=auxTotalTax;
		}
		order.setState(OrderStatus.Registrada);
		order.setTotalBasePrice(totalBase);
		order.setTotalPrice(totalPrice);
		order.setTotalTaxPrice(totalTax);
		return order;
	}

	private ResponseOrder crearOrden(Order order,boolean productos) {
		ResponseOrder responseOrder= new ResponseOrder();
		if(productos) {
			List<ProductXorder> xorders= productXorderDAO.FindProductsByorder(order.getOrderId());
			responseOrder.getProductXorders().addAll(xorders);
		}
		responseOrder.setDiscount(order.isDiscount());
		responseOrder.setState(order.getState());
		responseOrder.setTotalBasePrice(order.getTotalBasePrice());
		responseOrder.setTotalTaxPrice(order.getTotalTaxPrice());
		responseOrder.setOrderId(order.getOrderId());
		if(order.isDiscount()) {
			responseOrder.setTotalBasePrice(order.getTotalPrice()-(order.getTotalPrice()*0.1));
		}else {
			responseOrder.setTotalPrice(order.getTotalPrice());
		}
		return responseOrder;
	}
	
	private Order addDeleteProductOrden(RequestOrder requestOrder,Order order,List<Product> products,boolean delete) {
		double totalPrice=0,totalBase=0,totalTax=0;
		double auxTotalTax=0;
		for(Product product:products) {
			auxTotalTax=0;
			auxTotalTax=(product.getBasePrice()*product.getTaxRate());
			totalPrice+=(product.getBasePrice()+auxTotalTax);
			totalBase+=product.getBasePrice();
			totalTax+=auxTotalTax;
		}
		if(!delete) {
			order.setTotalBasePrice(order.getTotalBasePrice()+totalBase);
			order.setTotalPrice(order.getTotalPrice()+totalPrice);
			order.setTotalTaxPrice(order.getTotalTaxPrice()+totalTax);
		}else {
			order.setTotalBasePrice(order.getTotalBasePrice()-totalBase);
			order.setTotalPrice(order.getTotalPrice()-totalPrice);
			order.setTotalTaxPrice(order.getTotalTaxPrice()-totalTax);
		}
		return order;
	}



	


}
