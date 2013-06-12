package it.unisannio.catman.domain.equipment;

import java.util.List;

import it.unisannio.catman.common.server.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity
public class Materiel extends AbstractEntity<Long> {
	
	public static Materiel findMateriel(Long id) {
		return find(Materiel.class, id);
	}
	
	public static Integer count() {
		return count(Materiel.class);
	}
	
	public static List<Materiel> findAll() {
		return findAll(Materiel.class);
	}
	
	@Id
	@GeneratedValue
	private long id;
	
	@Version
	private int version;

	private String name;
	private String description;
	
	private boolean consumable = false;
	
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

	public boolean isConsumable() {
		return consumable;
	}

	public void setConsumable(boolean consumable) {
		this.consumable = consumable;
	}
	
	public String getDescription(){
		return description;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
}
