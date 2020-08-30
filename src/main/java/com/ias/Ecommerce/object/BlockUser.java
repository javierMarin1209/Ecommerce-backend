package com.ias.Ecommerce.object;

public class BlockUser {
	private String user;
	private String admin;
	private boolean status;
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getAdmin() {
		return admin;
	}
	public void setAdmin(String admin) {
		this.admin = admin;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "BlockUser [user=" + user + ", admin=" + admin + ", status=" + status + "]";
	}
	
	

}
