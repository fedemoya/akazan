package com.akazan.model;

import javax.persistence.*;

import org.openxava.annotations.*;

import com.akazan.calculators.*;

@Entity
@Table(name = "sale_item")
public class SaleItem {

	private static final String SEQ_NAME = "sale_item_seq";
	
	@Id
	@SequenceGenerator(name = SEQ_NAME, catalog = "akazan", sequenceName = SEQ_NAME, allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)
	@Hidden
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "sale_id")
	private Sale sale;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "product_id")
	@ReferenceView("ProductCompactView")
	private Product product;

	@Required
	@DefaultValueCalculator(value = QuantityInSale.class)
	private Integer quantity;
	
	@DefaultValueCalculator(value = PayCashCalculator.class)
	private Boolean payCash;
	@DefaultValueCalculator(value = PayCard1Calculator.class)
	private Boolean payCard1;
	@DefaultValueCalculator(value = PayCard3Calculator.class)
	private Boolean payCard3;

	@DefaultValueCalculator(
			value = PricePerUnitCalculator.class,
			properties = {
					@PropertyValue(name = "productId", from = "product.id"),
					@PropertyValue(name = "payCash", from = "payCash"),
					@PropertyValue(name = "payCard1", from = "payCard1"),
					@PropertyValue(name = "payCard3", from = "payCard3"),
			})
	@Stereotype("MONEY")
	private Double pricePerUnit;
	
	@Stereotype("MONEY")
	@Depends("quantity, pricePerUnit")
	public Double getTotal() {
		return getQuantity() * getPricePerUnit();
	}
	
	// Callbacks
	
	@PostCreate
	public void decreaseProductStock() {
		product.setQuantity(product.getQuantity() - quantity);
	}
	
	@PreDelete
	public void increaseProductStock() {
		product.setQuantity(product.getQuantity() + quantity);
	}
	
	// Getters & Setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Sale getSale() {
		return sale;
	}

	public void setSale(Sale sale) {
		this.sale = sale;
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

	public Boolean getPayCash() {
		return payCash;
	}

	public void setPayCash(Boolean payCash) {
		this.payCash = payCash;
	}

	public Boolean getPayCard1() {
		return payCard1;
	}

	public void setPayCard1(Boolean payCard1) {
		this.payCard1 = payCard1;
	}

	public Boolean getPayCard3() {
		return payCard3;
	}

	public void setPayCard3(Boolean payCard3) {
		this.payCard3 = payCard3;
	}

}
