package com.akazan.calculators;

import org.openxava.calculators.*;
import org.openxava.jpa.*;

import com.akazan.model.*;

@SuppressWarnings("serial")
public class PurchaseItemProfitCalculator implements ICalculator {

	private Long productId;

	@Override
	public Object calculate() throws Exception {
		Product product = XPersistence.getManager().find(Product.class, productId);
		return product.getProfitPercentage();
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

}
