package com.ias.Ecommerce.object;

public class RequestProduct {
	
	private String email;
	
	private Integer productId;

	private String name;
	
	private String description;

	private Double basePrice;

	private Double taxRate;

	private ProductStatus productStatus;

	private Integer inventoryQuantity;

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
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

	public Double getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(Double basePrice) {
		this.basePrice = basePrice;
	}

	public Double getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(Double taxRate) {
		this.taxRate = taxRate;
	}

	public ProductStatus getProductStatus() {
		return productStatus;
	}

	public void setProductStatus(ProductStatus productStatus) {
		this.productStatus = productStatus;
	}

	public Integer getInventoryQuantity() {
		return inventoryQuantity;
	}

	public void setInventoryQuantity(Integer inventoryQuantity) {
		this.inventoryQuantity = inventoryQuantity;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "RequestProduct [email=" + email + ", productId=" + productId + ", name=" + name + ", description="
				+ description + ", basePrice=" + basePrice + ", taxRate=" + taxRate + ", productStatus=" + productStatus
				+ ", inventoryQuantity=" + inventoryQuantity + "]";
	}
	
	
	

}
