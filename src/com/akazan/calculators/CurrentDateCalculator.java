package com.akazan.calculators;

import org.openxava.calculators.*;

public class CurrentDateCalculator implements ICalculator {
	
	public Object calculate() throws Exception {
		return new java.util.Date();
	}

}
