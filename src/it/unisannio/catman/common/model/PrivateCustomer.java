package it.unisannio.catman.common.model;

import it.unisannio.catman.Setup;

public class PrivateCustomer extends Customer {
	
	private String firstName;
	private String lastName;
	private String nationalIdentityNumber;
	
	@Override
	public String getName() {
		return String.format(Setup.CUSTOMER_PRIVATE_NAME_FORMAT, firstName, lastName);
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getNationalIdentityNumber() {
		return nationalIdentityNumber;
	}

	public void setNationalIdentityNumber(String nationalIdentityNumber) {
		this.nationalIdentityNumber = nationalIdentityNumber;
	}

}
