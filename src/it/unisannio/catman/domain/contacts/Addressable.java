package it.unisannio.catman.domain.contacts;

import it.unisannio.catman.common.server.AbstractEntity;
import it.unisannio.catman.domain.contacts.client.IsAddressable;


public abstract class Addressable extends AbstractEntity<Long> implements IsAddressable {
	
	
	private String address;
	private String city;
	private String state;
	private String country;
	private String postalCode;
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getState() {
		return state;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getPostalCode() {
		return postalCode;
	}
	
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	
}
