package it.unisannio.catman.common.model;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public abstract class Customer implements Contact {
	public static enum Type { BUSINESS, PRIVATE; }
	@Id
	protected long id;
	
	private String email;

	private String phone;

	private String address;

	private String fax;

	@Override
	public String getEmail() {
		return email;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public String getAddress() {
		return address;
	}
	
	public String getFax() {
		return fax;
	}
	
	public long getId() {
		return id;
	}
}
