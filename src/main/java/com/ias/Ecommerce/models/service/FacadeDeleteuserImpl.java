package com.ias.Ecommerce.models.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ias.Ecommerce.models.dao.UserDAO;
import com.ias.Ecommerce.object.Response;


@Service
public class FacadeDeleteuserImpl implements FacadeDeleteUser{
	
	@Autowired
	private UserDAO userDAO;
	
	/**
	 * Método para Eliminar un puerto 
	 * @param Email correo de quien esta haciendo la acción
	 * @param correo email del usuario a eliminar
	 * @return si la función funciono o no 
	 */
	@Transactional
	public Response Delete(String correo, String email) {
		Response res = new Response();
		/*
		if(Check(email, 1)) {
			if(correo==null) {
				res.setRequest(false);
				res.setRes("No se enivo email");
				return res;
			}else {
				User us = new User();
				List<User> u= new ArrayList<User>();
				Iterable<User>I;
				us.setEmail(correo);
				us.setName(null);
				us.setType(null);
				us.setPassword(null);
				Example<User>userExample=Example.of(us);
				I=userDAO.findAll(userExample);
				for(User usu:I) {
					u.add(usu);
				}
				if(u.size()>0) {
				userDAO.deleteById(u.get(0).getId());
				res.setRequest(true);
				res.setRes("Usuario eliminado exitosamente");
				return res;
				}else {
					res.setRequest(false);
					res.setRes("el email no se encuentra en el sistema");
					return res;
				}
			}
		}else {
			res.setRequest(false);
			res.setRes("El usuario no tiene permisos");
			return res;
		}*/
		return null;
	}

}
