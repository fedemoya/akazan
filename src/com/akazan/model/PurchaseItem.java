package com.akazan.model;

import javax.persistence.*;

import org.openxava.annotations.*;

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

	private Integer amount;

	private Double price;
	
	// Callbacks
	
	@PostCreate
	public void increaseProductStock() {
		product.setAmount(product.getAmount() + amount);
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

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

}
