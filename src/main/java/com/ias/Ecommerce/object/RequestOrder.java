package com.ias.Ecommerce.object;

import java.util.ArrayList;
import java.util.List;

import com.ias.Ecommerce.models.entity.ProductXorder;


public class RequestOrder {
	
	private int orderId;
	
	private List<ProductXorder> productXorders;
	
	private String userXorders;

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

	@Override
	public String toString() {
		return "RequestOrder [orderId=" + orderId + ", productXorders=" + productXorders + ", userXorders="
				+ userXorders + "]";
	}

	
	

}
