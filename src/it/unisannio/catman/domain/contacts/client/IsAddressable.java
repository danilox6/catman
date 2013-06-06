package it.unisannio.catman.domain.contacts.client;

public interface IsAddressable {
	public String getAddress();
	public void setAddress(String address);
	
	public String getCity() ;
	public void setCity(String city) ;
	
	public String getState();
	public void setState(String state);
	
	public String getCountry();
	public void setCountry(String country);
	
	public String getPostalCode();
	public void setPostalCode(String postalCode);
}
