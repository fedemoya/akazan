package com.akazan.calculators;

import org.openxava.annotations.*;
import org.openxava.calculators.*;
import org.openxava.jpa.*;

import com.akazan.model.*;

public class PricePerUnitCalculator implements ICalculator {
	
	private Long productId;
	private Boolean payCash;
	private Boolean payCard1;
	private Boolean payCard3;

	@Override
	public Object calculate() throws Exception {
		Product product = XPersistence.getManager().find(Product.class, productId);
		Double price = product.getPrice();
		if (payCash) { 
			return price - (price * (product.getDiscount() / 100.00));
		} else if (payCard1) {
			return product.getPrice() * (1 + (product.getSurcharge1() / 100.00)); 
		} else if (payCard3) {
			return product.getPrice() * (1 + (product.getSurcharge1() / 100.00));
		} else {
			return price;
		}
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
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
