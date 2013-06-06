package it.unisannio.catman.domain.contacts.client;

public interface IsContactable extends IsAddressable {

	public String getName();
	public void setName(String name);
	
	public String getEmail();
	public void setEmail(String email);
	
	public String getPhone();
	public void setPhone(String phone);
}
