package com.akazan.model;

import javax.persistence.*;

import org.openxava.annotations.*;

import com.akazan.calculators.*;


@Entity
@Table(name = "purchase_item")
public class PurchaseItem {
	
	private static final String SEQ_NAME = "purchase_item_seq";

	@Id
	@SequenceGenerator(name = SEQ_NAME, catalog = "akazan", sequenceName = SEQ_NAME, allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)
	@Hidden
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "purchase_id")
	private Purchase purchase;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "product_id")
	private Product product;

	private Integer quantity;

	@Stereotype("MONEY")
	private Double pricePerUnit;

	private Double profitPercentage;
	
	@Stereotype("MONEY")
	@Depends("quantity, pricePerUnit, profitPercentage")
	public Double getTotal() {
		return getQuantity() * getPricePerUnit() * (1 +  profitPercentage / 100);
	}
	
	// Callbacks
	
	@PostCreate
	public void increaseProductStock() {
		product.setQuantity(product.getQuantity() + quantity);
		product.setPrice(pricePerUnit * (1 +  profitPercentage / 100));
	}
	
	@PreDelete
	public void decreaseProductStock() {
		product.setQuantity(product.getQuantity() - quantity);
		product.setPrice(0.0);
	}
	
	// Getters & Setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Purchase getPurchase() {
		return purchase;
	}

	public void setPurchase(Purchase purchase) {
		this.purchase = purchase;
	}

	public Double getProfitPercentage() {
		return profitPercentage;
	}

	public void setProfitPercentage(Double profitPercentage) {
		this.profitPercentage = profitPercentage;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPricePerUnit() {
		return pricePerUnit;
	}

	public void setPricePerUnit(Double pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}

}
