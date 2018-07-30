package com.akazan.actions;

import java.rmi.*;
import java.util.*;

import javax.ejb.*;

import org.openxava.actions.*;
import org.openxava.jpa.*;
import org.openxava.model.*;
import org.openxava.util.*;
import org.openxava.validators.*;

import com.akazan.model.*;

public class AddProductsToPPUpdateAction extends AddElementsToCollectionAction {
	
		
	public void execute() throws Exception {
		super.execute();
		getView().refresh();
	}

	protected void associateEntity(Map keyValues)
			throws ValidationException, XavaException, ObjectNotFoundException, FinderException, RemoteException {
		super.associateEntity(keyValues);
		Product product = (Product) MapFacade.findEntity("Product", keyValues);
		Double percentage = (Double) getCollectionElementView().getParent().getValue("percentage");
		product.setPrice(product.getPrice() * (1 + (percentage / 100.00)));
		XPersistence.getManager().persist(product);
	}
}
