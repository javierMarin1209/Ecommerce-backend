package com.ias.Ecommerce.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;

import com.ias.Ecommerce.object.ProductStatus;

@Entity
@Table(name="product")
public class Product {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="productId")
	private int productId;
	
	@Column(name="name")
	@NonNull
	@NotEmpty
	private String name;
	
	@Column(name="description")
	@NonNull
	@NotEmpty
	private String description;
	
	@Column(name="basePrice")
	@NonNull
	private double basePrice;
	
	@Column(name="taxRate")
	@NonNull
	private double taxRate;
	
	@Column(name="productStatus")
	@NonNull
	private ProductStatus productStatus;
	
	@Column(name="inventoryQuantity")
	private int inventoryQuantity;
	

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}

	public double getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(double taxRate) {
		this.taxRate = taxRate;
	}

	public ProductStatus getProductStatus() {
		return productStatus;
	}

	public void setProductStatus(ProductStatus productStatus) {
		this.productStatus = productStatus;
	}

	public int getInventoryQuantity() {
		return inventoryQuantity;
	}

	public void setInventoryQuantity(int inventoryQuantity) {
		this.inventoryQuantity = inventoryQuantity;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", name=" + name + ", description=" + description + ", basePrice="
				+ basePrice + ", taxRate=" + taxRate + ", productStatus=" + productStatus + ", inventoryQuantity="
				+ inventoryQuantity + "]";
	}
	
	

}
