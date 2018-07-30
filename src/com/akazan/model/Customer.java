package com.akazan.model;

import javax.persistence.*;

import org.hibernate.validator.constraints.*;
import org.openxava.annotations.*;

@Entity
@Table(name="customer")
@Views({
	@View(name="CustomerCompactView", members = "name, lastname")
})
public class Customer {

	private static final String SEQ_NAME = "customer_seq";

	@Id
	@SequenceGenerator(name = SEQ_NAME, catalog = "akazan", sequenceName = SEQ_NAME, allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)
	@Hidden
	private Long id;

	@Required
	private String name;
	@Required
	private String lastname;

	private String mobileNumber;

	@Email
	private String email;

	@Embedded
	private Address address;

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

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

}
