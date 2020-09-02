package com.ias.Ecommerce.models.service;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ias.Ecommerce.models.dao.UserDAO;
import com.ias.Ecommerce.models.entity.User;
import com.ias.Ecommerce.object.BlockUser;
import com.ias.Ecommerce.object.Response;
import com.ias.Ecommerce.object.UserType;


@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDAO userDAO;

	@Override
	public Response Create(User user) {
		Response response= new Response();
		if(user!=null&&user.getEmail()!=null) {
			try {
				Optional<User> confirmUser= userDAO.findById(user.getEmail());
				if(!confirmUser.isPresent()) {
					user.setPassword(Security.sha256(user.getPassword(), user.getEmail()));
					userDAO.save(user);
					response.setSuccess(true);
					response.setError("Usuario creado exitosamente");
				}else {
					response.setSuccess(false);
					response.setError("El email ya esta en uso");
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
	public Response BlockUser(BlockUser blockUser) {
		Response response= new Response();
		if(blockUser!=null&&blockUser.getAdmin()!=null&&blockUser.getUser()!=null) {
			try {
				Optional<User> confirmUser= userDAO.findById(blockUser.getAdmin());
				if(confirmUser.isPresent()&&!confirmUser.get().getType().equals(UserType.Cliente)) {
					confirmUser= userDAO.findById(blockUser.getUser());
					if(confirmUser.isPresent()) {
						confirmUser.get().setStatus(blockUser.isStatus());
						userDAO.save(confirmUser.get());
						response.setSuccess(true);
						response.setError("Usuario modificado exitosamente");
					}else {
						response.setSuccess(false);
						response.setError("El usuario a modificar no existe");
					}
					
				}else {
					response.setSuccess(false);
					response.setError("El usuario administrador no existe o no tiene permisos para esto");
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
			response.setError("No se enviaron los datos necesarios para la operación");
		}
		return response;
	}

	@Override
	public Response LogIn(User user) {
		Response response= new Response();
		if(user!=null&&user.getEmail()!=null) {
			try {
				Optional<User> confirmUser= userDAO.findById(user.getEmail());
				if(confirmUser.isPresent()) {
					if(Security.sha256(user.getPassword(), user.getEmail()).equals(confirmUser.get().getPassword())) {
						confirmUser.get().setAttempt(0);
						confirmUser.get().setTmpPassword(null);
						response.setSuccess(true);
						response.setError("Login exitoso");
						response.setResponse(confirmUser.get());
					}else {
						if(confirmUser.get().getAttempt()>=3) {
							confirmUser.get().setStatus(false);
							response.setSuccess(false);
							response.setError("El usuario se a bloqueado por favor comuniquese con el adminsitrador");
						}else {
							confirmUser.get().setAttempt(confirmUser.get().getAttempt()+1);
							response.setSuccess(false);
							response.setError("El usuario o la contraseña son incorrectos");
						}
					}
					userDAO.save(confirmUser.get());
					
				}else {
					response.setSuccess(false);
					response.setError("El usuario no existe");
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
	public Response ChangePassword(User user) {
		Response response= new Response();
		if(user!=null&&user.getEmail()!=null) {
			try {
				Optional<User> confirmUser= userDAO.findById(user.getEmail());
				if(confirmUser.isPresent()) {
					confirmUser.get().setPassword(Security.sha256(user.getPassword(), user.getEmail()));
					userDAO.save(confirmUser.get());
					response.setSuccess(true);
					response.setError("Contraseña actualizada");
				}else {
					response.setSuccess(false);
					response.setError("El usuario no existe");
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
	public Response forgotPassword(User user) {
		Response response= new Response();
		String subject;
		String body;
		String res;
		String code;
		if(user!=null&&user.getEmail()!=null) {
			try {
				Optional<User> confirmUser= userDAO.findById(user.getEmail());
				if(confirmUser.isPresent()) {
					Deliverymessage deliverymessage= new Deliverymessage();
					code=Security.generateTempPassword();
					subject="Solicitud cambio de contraseña";
					body="Si solicito un cambio de contraseña por favor ingrese el siguiente codigo. Si no porfavor omita este correo.\n\n"
							+ "Su codigo es: "+code;
					res=deliverymessage.enviarConGMail(user.getEmail(),subject , body);
					if(res==null) {
						confirmUser.get().setTmpPassword(Security.sha256(code,""));
						userDAO.save(confirmUser.get());
						response.setSuccess(true);
						response.setError("Solicitud de cambio de contraseña enviado correctamente");
					}else {
						response.setSuccess(false);
						response.setError("Error con el servidor de correos, por favor intente nuevamente");
					}
				}else {
					response.setSuccess(false);
					response.setError("El usuario no existe");
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
	public Response ConfirmNewpassword(User user) {
		Response response= new Response();
		if(user!=null&&user.getEmail()!=null&&user.getPassword()!=null) {
			try {
				Optional<User> confirmUser= userDAO.findById(user.getEmail());
				if(confirmUser.get().getTmpPassword()!=null) {
					if(confirmUser.isPresent()&&confirmUser.get().getTmpPassword().equals(Security.sha256(user.getTmpPassword(), ""))) {
						confirmUser.get().setPassword(Security.sha256(user.getPassword(), user.getEmail()));
						confirmUser.get().setTmpPassword(null);
						userDAO.save(confirmUser.get());
						response.setSuccess(true);
						response.setError("Contraseña actualizada");
					}else {
						response.setSuccess(false);
						response.setError("El usuario no existe o codigo de seguridad erroneo");
					}
				}else {
					response.setSuccess(false);
					response.setError("Este usuario no a solicitado un cambio de contraseña");
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
			response.setError("No se enviaron los datos suficientes para este mensaje");
		}
		
		return response;
	}
	
}
