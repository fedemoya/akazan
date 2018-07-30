package com.akazan.actions;

import org.openxava.actions.*;

public class GoAddProductsToPPUpdateAction extends GoAddElementsToCollectionAction {

	public String getNextController() {
		return "AddProductsToPPUpdate";
	}
}
