package com.akazan.model;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;

@Entity
@Table(name="sale")
public class Sale {
	
	private static final String SEQ_NAME = "sale_seq";

	@Id
	@SequenceGenerator(name = SEQ_NAME, catalog = "akazan", sequenceName = SEQ_NAME, allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)
	@Hidden
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = true)
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	@Required
	private Date date;
	
	@OneToMany(mappedBy="sale", cascade=CascadeType.ALL)
	private List<SaleItem> saleItems;
	
	// Callbacks
	

	// Getters & Setters
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<SaleItem> getsaleItems() {
		return saleItems;
	}

	public void setsaleItems(List<SaleItem> saleItems) {
		this.saleItems = saleItems;
	}

}
