package com.ias.Ecommerce.models.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;


import com.ias.Ecommerce.object.OrderStatus;

@Entity
@Table(name="[order]")
@PersistenceContext
public class Order {

	@Id
	@Column(name="orderId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderId;
	
	@Column(name="state")
	private OrderStatus state;
	
	@Column(name="totalBasePrice")
	private double totalBasePrice;
	
	@Column(name="totalTaxPrice")
	private double totalTaxPrice;
	
	@Column(name="totalPrice")
	private double totalPrice;
	
	@Column(name="discount")
	private boolean discount;

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public OrderStatus getState() {
		return state;
	}

	public void setState(OrderStatus state) {
		this.state = state;
	}

	public double getTotalBasePrice() {
		return totalBasePrice;
	}

	public void setTotalBasePrice(double totalBasePrice) {
		this.totalBasePrice = totalBasePrice;
	}

	public double getTotalTaxPrice() {
		return totalTaxPrice;
	}

	public void setTotalTaxPrice(double totalTaxPrice) {
		this.totalTaxPrice = totalTaxPrice;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public boolean isDiscount() {
		return discount;
	}

	public void setDiscount(boolean discount) {
		this.discount = discount;
	}


	
	
	
	
}
