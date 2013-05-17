package it.unisannio.catman.domain.contacts;

import it.unisannio.catman.common.server.AbstractEntity;

public abstract class Contact extends AbstractEntity implements Contactable {
	private String name;
	private String phone;
	private String email;
	private String address;
	private String city;
	private String state;
	private String country;
	private String postalCode;
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getPhone() {
		return phone;
	}

	@Override
	public String getEmail() {
		return email;
	}

	@Override
	public String getAddress() {
		return address;
	}

	@Override
	public String getCity() {
		return city;
	}

	@Override
	public String getState() {
		return state;
	}

	@Override
	public String getCountry() {
		return country;
	}

	@Override
	public String getPostalCode() {
		return postalCode;
	}

}
