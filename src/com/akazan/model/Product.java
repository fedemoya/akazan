package com.akazan.model;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.jpa.*;

import com.akazan.calculators.*;

@Entity
@Table(name = "product")
@Views({@View(members="data {supplierCode;category;brand;line;variant;description;price;profitPercentage;quantity}"
		+ " purchases { purchases }"),
		@View(name="ProductCompactView", members="supplierCode;category;brand;line;variant;description")})
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

	private String line;

	private String variant;

	private String description;

	private Double price;
	
	@DefaultValueCalculator(value = ProductQuantityCalculator.class)
	private Integer quantity;
	
	@OneToMany(mappedBy="product")
	private Collection<PurchaseItem> purchases;
	
	public Double getProfitPercentage() {
		EntityManager em = XPersistence.getManager();
		Query q = em.createNativeQuery("SELECT profitpercentage FROM purchase_item WHERE product_id=? ORDER BY id DESC LIMIT 1");
		q.setParameter(1, id);
		@SuppressWarnings("unchecked")
		List<Object> results = q.getResultList();
		if (results != null && results.size() > 0) {
			Object profitPercentage = results.get(0);
		    return profitPercentage == null ? 0.0 : (Double) profitPercentage;
		} else {
			return 0.0;
		}
	}

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

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Collection<PurchaseItem> getPurchases() {
		return purchases;
	}

	public void setPurchases(Collection<PurchaseItem> purchases) {
		this.purchases = purchases;
	}

}
