package com.akazan.model;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;

@Entity
@Table(name="product_price_update")
public class ProductPriceUpdate {
		
	private static final String SEQ_NAME = "product_price_update_seq";

	@Id
	@SequenceGenerator(name = SEQ_NAME, catalog = "akazan", sequenceName = SEQ_NAME, allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)
	@Hidden
	private Long id;
	
	@Required
	private Date date;
	
	@Required
	private Double percentage;
	
	@ManyToMany
	@JoinTable(name = "product_ppupdate",
	    joinColumns = @JoinColumn(name = "ppupdate_id"),
	    inverseJoinColumns = @JoinColumn(name = "product_id")
	)
	@AddAction("ProductPriceUpdate.addProducts")
	private Collection<Product> products;
	
	// Callbacks
	
	@PreDelete
	public void deletePPUpdatesFromJoinTable() {
		// TODO
	}
	
	// Getters & Setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Double getPercentage() {
		return percentage;
	}

	public void setPercentage(Double percentage) {
		this.percentage = percentage;
	}

	public Collection<Product> getProducts() {
		return products;
	}

	public void setProducts(Collection<Product> products) {
		this.products = products;
	}

}
