package com.akazan.calculators;

import org.openxava.calculators.*;
import org.openxava.jpa.*;

import com.akazan.model.*;

public class PricePerUnitCalculator implements ICalculator {
	
	private Long productId;

	@Override
	public Object calculate() throws Exception {
		Product product = XPersistence.getManager().find(Product.class, productId);
		return product.getPrice();
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

}
