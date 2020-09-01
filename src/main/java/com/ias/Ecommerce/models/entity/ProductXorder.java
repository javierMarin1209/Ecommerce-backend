package com.ias.Ecommerce.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="productxorder")
public class ProductXorder {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="quantity")
	private int quantity;
	
	@Column(name="basePrice")
	private double basePrice;
	
	@Column(name="taxPrice")
	private double taxPrice;
	
	@Column(name="totalPrice")
	private double totalPrice;
	
	@Column(name="productProductId")
	private int productProductId;
	
	@Column(name="orderOrderId")
	private int orderOrderId;
	
	@Column(name="productName")
	private String productName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}

	public double getTaxPrice() {
		return taxPrice;
	}

	public void setTaxPrice(double taxPrice) {
		this.taxPrice = taxPrice;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getProductProductId() {
		return productProductId;
	}

	public void setProductProductId(int productProductId) {
		this.productProductId = productProductId;
	}

	public int getOrderOrderId() {
		return orderOrderId;
	}

	public void setOrderOrderId(int orderOrderId) {
		this.orderOrderId = orderOrderId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@Override
	public String toString() {
		return "ProductXorder [id=" + id + ", quantity=" + quantity + ", basePrice=" + basePrice + ", taxPrice="
				+ taxPrice + ", totalPrice=" + totalPrice + ", productProductId=" + productProductId + ", orderOrderId="
				+ orderOrderId + ", productName=" + productName + "]";
	}

	
	
	
	
}
