package com.ias.Ecommerce.models.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ias.Ecommerce.models.dao.ProductDAO;
import com.ias.Ecommerce.models.dao.UserDAO;
import com.ias.Ecommerce.models.entity.Product;
import com.ias.Ecommerce.models.entity.User;
import com.ias.Ecommerce.object.RequestProduct;
import com.ias.Ecommerce.object.Response;
import com.ias.Ecommerce.object.UserType;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private UserDAO userDAO;
	
	@Override
	public Response Create(RequestProduct requestProduct) {
		Response response= new Response();
		Optional<User> confirmUser;
		Object product;
		if(requestProduct!=null&&requestProduct.getEmail()!=null) {
			try {
				confirmUser = userDAO.findById(requestProduct.getEmail());
				if(confirmUser.isPresent()&&!confirmUser.get().getType().equals(UserType.Cliente)) {
					product= inicializarProducto(requestProduct);
					if(product.getClass().equals(Product.class)) {
						productDAO.save((Product)product);
						response.setSuccess(true);
						response.setError("Producto creado exitosamente");
					}else {
						response.setSuccess(false);
						response.setError((String)product);
					}
				}else {
					response.setSuccess(false);
					response.setError("El usuario no existe o no tiene permisos para esto");
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
			response.setSuccess(false);
			response.setError("No se envio un usuario o un email valido");
		}
		
		return response;
	}

	@Override
	public Response FindAll() {
		Response response= new Response();
		List<Product> products;
			try {
				products=(List<Product>) productDAO.findAll();
				response.setSuccess(true);
				response.setResponse(products);
				response.setError("Consulta exitosa");
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
	public Response Update(RequestProduct requestProduct) {
		Response response= new Response();
		Optional<User> confirmUser;
		Optional<Product> optional;
		Object product;
		if(requestProduct!=null&&requestProduct.getEmail()!=null) {
			try {
				confirmUser = userDAO.findById(requestProduct.getEmail());
				if(confirmUser.isPresent()&&!confirmUser.get().getType().equals(UserType.Cliente)) {
					optional=productDAO.findById(requestProduct.getProductId());
					if(optional.isPresent()) {
						product= ActualizarProducto(requestProduct,optional.get());
						if(product.getClass().equals(Product.class)) {
							productDAO.save((Product)product);
							response.setSuccess(true);
							response.setError("Producto sea actualizado exitosamente");
						}else {
							response.setSuccess(false);
							response.setError((String)product);
						}
					}else {
						response.setSuccess(false);
						response.setError("El ID del producto no existe");
					}
				}else {
					response.setSuccess(false);
					response.setError("El usuario no existe o no tiene permisos para esto");
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
			response.setSuccess(false);
			response.setError("No se envio un usuario o un email valido");
		}
		
		return response;
	}

	@Override
	public Response Delete(RequestProduct requestProduct) {
		Response response= new Response();
		Optional<User> confirmUser;
		Optional<Product> optional;
		if(requestProduct!=null&&requestProduct.getEmail()!=null) {
			try {
				confirmUser = userDAO.findById(requestProduct.getEmail());
				if(confirmUser.isPresent()&&!confirmUser.get().getType().equals(UserType.Cliente)) {
					optional=productDAO.findById(requestProduct.getProductId());
					if(optional.isPresent()) {
						productDAO.deleteById(requestProduct.getProductId());
						response.setSuccess(true);
						response.setError("Producto sea eliminado exitosamente");
					}else {
						response.setSuccess(false);
						response.setError("El ID del producto no existe");
					}
				}else {
					response.setSuccess(false);
					response.setError("El usuario no existe o no tiene permisos para esto");
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
			response.setSuccess(false);
			response.setError("No se envio un usuario o un email valido");
		}
		
		return response;
	}

	@Override
	public Response FindByName(RequestProduct requestProduct) {
		Response response= new Response();
		if(requestProduct.getName()!=null) {
			List<Product> products;
			try {
				products=(List<Product>) productDAO.FindByName('%'+requestProduct.getName()+'%');
				response.setSuccess(true);
				response.setResponse(products);
				response.setError("Consulta exitosa");
			}catch (Exception e) {
				response.setSuccess(false);
				if(e.getCause().getCause()!=null) {
					response.setError(e.getCause().getCause().getMessage());
				}else {
					response.setError(e.getCause().getMessage());
				}
				
			}	
		}else {
			response.setSuccess(false);
			response.setError("No se a enviado ningun nombre");
		}
		return response;
	}
	
	private Object inicializarProducto(RequestProduct requestProduct) {
		Product product= new Product();
		if(requestProduct.getBasePrice()!=null&&requestProduct.getBasePrice()>=0) {
			product.setBasePrice(requestProduct.getBasePrice());
		}else {
			return "El precio base es nulo o negativo";
		}
		if(requestProduct.getName()!=null&&requestProduct.getName().length()<100&&requestProduct.getName().length()!=0) {
			product.setName(requestProduct.getName());
		}else {
			return "El nombre del producto es nulo o tiene mas de 100 carcteres o esta vacio";
		}
		if(requestProduct.getDescription()!=null&&requestProduct.getDescription().length()<280&&requestProduct.getName().length()!=0) {
			product.setDescription(requestProduct.getDescription());
		}else {
			return "La descprición del producto esta vacia o supera los 280 caracteres";
		}
		if(requestProduct.getTaxRate()!=null&&requestProduct.getTaxRate()>0&&requestProduct.getTaxRate()<1) {
			product.setTaxRate(requestProduct.getTaxRate());
		}else {
			return "los impuestos del producto son nulos o superan los limites";
		}
		if(requestProduct.getInventoryQuantity()!=null&&requestProduct.getInventoryQuantity()>=0) {
			product.setInventoryQuantity(requestProduct.getInventoryQuantity());
		}else {
			return "El stock del producto no puede ser menor a 0";
		}
		if(requestProduct.getProductStatus()!=null) {
			product.setProductStatus(requestProduct.getProductStatus());
		}else {
			return "El estado del producto no es valido";
		}
		return product;
	}
	
	private Object ActualizarProducto(RequestProduct requestProduct,Product old) {
		Product product= new Product();
		product.setProductId(requestProduct.getProductId());
		if(requestProduct.getBasePrice()!=null){
			product.setBasePrice(requestProduct.getBasePrice());
		}else {
			product.setBasePrice(old.getBasePrice());
		}
		if(requestProduct.getName()!=null) {
			product.setName(requestProduct.getName());
		}else {
			product.setName(old.getName());
		}
		if(requestProduct.getDescription()!=null) {
			product.setDescription(requestProduct.getDescription());
		}else {
			product.setDescription(old.getDescription());
		}
		if(requestProduct.getTaxRate()!=null) {
			product.setTaxRate(requestProduct.getTaxRate());
		}else {
			product.setTaxRate(old.getTaxRate());
		}
		if(requestProduct.getInventoryQuantity()!=null) {
			product.setInventoryQuantity(requestProduct.getInventoryQuantity());
		}else {
			product.setInventoryQuantity(old.getInventoryQuantity());
		}
		if(requestProduct.getProductStatus()!=null) {
			product.setProductStatus(requestProduct.getProductStatus());
		}else {
			product.setProductStatus(old.getProductStatus());
		}
		return product;
	}

}
