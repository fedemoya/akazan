package com.akazan.model;

import javax.persistence.*;

import org.openxava.annotations.*;

@Entity
@Table(name = "supplier")
public class Supplier {

	private static final String SEQ_NAME = "supplier_seq";

	@Id
	@SequenceGenerator(name = SEQ_NAME, catalog = "akazan", sequenceName = SEQ_NAME, allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)
	@Hidden
	private Long id;

	@Required
	private String name;

	private String phoneNumber;

	@Embedded
	private Address address;

	private String iva;

	private String cuit;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getIva() {
		return iva;
	}

	public void setIva(String iva) {
		this.iva = iva;
	}

	public String getCuit() {
		return cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

}
