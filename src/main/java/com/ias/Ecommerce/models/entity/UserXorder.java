package com.ias.Ecommerce.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="userxorder")
public class UserXorder {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	
	@Column(name="orderOrderId")
	private int orderOrderId;
	
	
	@Column(name="userEmail")
	private String userEmail;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

	public int getOrderOrderId() {
		return orderOrderId;
	}

	public void setOrderOrderId(int orderOrderId) {
		this.orderOrderId = orderOrderId;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	@Override
	public String toString() {
		return "UserXorder [id=" + id + ", orderOrderId=" + orderOrderId + ", userEmail=" + userEmail + "]";
	}

	
	
}
