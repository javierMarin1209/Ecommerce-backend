package com.ias.Ecommerce.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ias.Ecommerce.object.UserType;

@Entity
@Table(name="user")
public class User {
	
	@Id
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="email")
	private String email;
	
	@Column(name="name")
	private String name;
	
	@Column(name="attempt")
	private int attempt;
	
	@Column(name="type")
	private UserType type;
	
	@Column(name="orders")
	private int orders;
	
	@Column(name="password")
	private String password;
	
	@Column(name="status")
	private boolean status;
	
	@Column(name="tmpPassword")
	private String tmpPassword;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAttempt() {
		return attempt;
	}

	public void setAttempt(Integer attempt) {
		this.attempt = attempt;
	}

	public UserType getType() {
		return type;
	}

	public void setType(UserType type) {
		this.type = type;
	}

	public Integer getOrders() {
		return orders;
	}

	public void setOrders(Integer orders) {
		this.orders = orders;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getTmpPassword() {
		return tmpPassword;
	}

	public void setTmpPassword(String tmpPassword) {
		this.tmpPassword = tmpPassword;
	}

	@Override
	public String toString() {
		return "User [email=" + email + ", name=" + name + ", attempt=" + attempt + ", type=" + type + ", orders="
				+ orders + ", password=" + password + ", status=" + status + ", tmpPassword=" + tmpPassword + "]";
	}
	
	
}
