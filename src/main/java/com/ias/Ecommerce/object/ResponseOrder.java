package com.ias.Ecommerce.object;

import java.util.ArrayList;
import java.util.List;

import com.ias.Ecommerce.models.entity.ProductXorder;

public class ResponseOrder {
	
	private List<ProductXorder> productXorders;
	
	private String userXorders;
	
	private OrderStatus state;
	
	private double totalBasePrice;
	
	private double totalTaxPrice;
	
	private double totalPrice;
	
	private boolean discount;
	
	private int orderId;
	
	

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
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

	public String getUserXorders() {
		return userXorders;
	}

	public void setUserXorders(String userXorders) {
		this.userXorders = userXorders;
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

	@Override
	public String toString() {
		return "ResponseOrder [productXorders=" + productXorders + ", userXorders=" + userXorders + ", state=" + state
				+ ", totalBasePrice=" + totalBasePrice + ", totalTaxPrice=" + totalTaxPrice + ", totalPrice="
				+ totalPrice + ", discount=" + discount + ", orderId=" + orderId + "]";
	}

	
	
	
}
