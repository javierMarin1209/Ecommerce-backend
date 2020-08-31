package com.ias.Ecommerce.object;

import java.util.ArrayList;
import java.util.List;

import com.ias.Ecommerce.models.entity.ProductXorder;


public class RequestOrder {
	
	private int orderId;
	
	private OrderStatus state;
	
	private double totalBasePrice;

	private double totalTaxPrice;

	private double totalPrice;

	private boolean discount;
	
	private List<ProductXorder> productXorders;
	
	private List<String> userXorders;

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

	public List<ProductXorder> getProductXorders() {
		if(productXorders==null) {
			productXorders= new ArrayList<>();
		}
		return productXorders;
	}

	public void setProductXorders(List<ProductXorder> productXorders) {
		this.productXorders = productXorders;
	}

	public List<String> getUserXorders() {
		if(userXorders==null) {
			userXorders= new ArrayList<>();
		}
		return userXorders;
	}

	public void setUserXorders(List<String> userXorders) {
		this.userXorders = userXorders;
	}

	@Override
	public String toString() {
		return "RequestOrder [orderId=" + orderId + ", state=" + state + ", totalBasePrice=" + totalBasePrice
				+ ", totalTaxPrice=" + totalTaxPrice + ", totalPrice=" + totalPrice + ", discount=" + discount
				+ ", productXorders=" + productXorders + ", userXorders=" + userXorders + "]";
	}
	
	

}
