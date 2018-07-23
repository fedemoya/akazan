package com.akazan.model;

import javax.persistence.*;

import org.openxava.annotations.*;

@Entity
@Table(name = "product")
public class Product {

	private static final String SEQ_NAME = "product_seq";

	@Id
	@SequenceGenerator(name = SEQ_NAME, catalog = "akazan", sequenceName = SEQ_NAME, allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)
	@Hidden
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "category_id")
	private ProductCategory category;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "brand_id")
	private ProductBrand brand;

	private String supplierCode;

	@Required
	private String line;

	private String variant;

	private String description;

	@Required
	private Double price;
	
	@Required
	private Integer amount;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ProductCategory getCategory() {
		return category;
	}

	public void setCategory(ProductCategory category) {
		this.category = category;
	}

	public ProductBrand getBrand() {
		return brand;
	}

	public void setBrand(ProductBrand brand) {
		this.brand = brand;
	}

	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public String getVariant() {
		return variant;
	}

	public void setVariant(String variant) {
		this.variant = variant;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

}
