package it.unisannio.catman.domain.humanresources;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

import it.unisannio.catman.common.server.AbstractEntity;

@Entity
public class Qualification extends AbstractEntity<Long>{
	
	public static Qualification findQualification(Long id) {
		return find(Qualification.class, id);
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Version 
	private int version;
	
	private String name;

	@Override
	public int getVersion() {
		return version;
	}

	@Override
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
