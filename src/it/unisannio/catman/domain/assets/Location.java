package it.unisannio.catman.domain.assets;

import it.unisannio.catman.domain.contacts.Addressable;

public class Location implements Addressable{
	
	private String address;
	private String city;
	private String state;
	private String country;
	private String postalCode;
	
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
