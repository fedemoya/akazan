package com.akazan.model;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;

@Entity
@Table(name="purchase")
public class Purchase {
	
	private static final String SEQ_NAME = "purchase_seq";

	@Id
	@SequenceGenerator(name = SEQ_NAME, catalog = "akazan", sequenceName = SEQ_NAME, allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)
	@Hidden
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "supplier_id")
	private Supplier supplier;
	
	@Required
	private Date date;
	
	@OneToMany(mappedBy="purchase", cascade=CascadeType.REMOVE)
	private List<PurchaseItem> purchaseItems;
	
	// Callbacks
	

	// Getters & Setters
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<PurchaseItem> getPurchaseItems() {
		return purchaseItems;
	}

	public void setPurchaseItems(List<PurchaseItem> purchaseItems) {
		this.purchaseItems = purchaseItems;
	}

}
