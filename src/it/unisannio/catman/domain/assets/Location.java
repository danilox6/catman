package it.unisannio.catman.domain.assets;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;

import it.unisannio.catman.domain.contacts.Addressable;

@Entity
public class Location extends Addressable {

	@Id
	private long id;
	
	@Version
	private int version;
	
	@Override
	public int getVersion() {
		return version;
	}

	@Override
	public Long getId() {
		return id;
	}
	

}
