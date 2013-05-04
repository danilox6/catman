package it.unisannio.catman.domain.equipment;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;

import it.unisannio.catman.domain.contacts.Contact;

@Entity
public class Seller extends Contact{
	@Id private long id;
	@Version private int version;
	
	public Seller findSeller(long id) {
		return find(Seller.class, id);
	}

	@Override
	public int getVersion() {
		return version;
	}

	@Override
	public long getId() {
		return id;
	}

}
