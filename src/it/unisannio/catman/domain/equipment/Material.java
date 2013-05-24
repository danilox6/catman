package it.unisannio.catman.domain.equipment;

import it.unisannio.catman.common.server.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity
public class Material extends AbstractEntity<Long> {
	
	public static Material findMaterial(Long id) {
		return find(Material.class, id);
	}
	
	@Id
	@GeneratedValue
	private long id;
	
	@Version
	private int version;

	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public int getVersion() {
		return version;
	}	
}
