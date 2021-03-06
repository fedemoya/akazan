package com.akazan.model;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.calculators.*;

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
	@ReferenceView("SupplierCompactView")
	private Supplier supplier;
	
	@Required
	@DefaultValueCalculator(CurrentDateCalculator.class)
	private Date date;
	
	@OneToMany(mappedBy="purchase", cascade=CascadeType.ALL)
	private List<PurchaseItem> purchaseItems;
	
	@Stereotype("MONEY")
	public Double getTotal() {
		Double total = 0.0;
		for (PurchaseItem purchaseItem : purchaseItems) {
			total += purchaseItem.getTotal();
		}
		return total;
	}

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
