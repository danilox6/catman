package it.unisannio.catman.domain.planning;

import it.unisannio.catman.common.server.AbstractEntity;
import it.unisannio.catman.domain.equipment.Supply;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity
public class Source extends AbstractEntity<Long> {
	
	public static Source findSource(Long id) {
		return find(Source.class, id);
	}
	
	@Id
	@GeneratedValue
	private long id;
	
	@Version
	private int version;
	
	private int quantity;
	
	private Supply<?,?> source;

	@Override
	public int getVersion() {
		return version;
	}

	@Override
	public Long getId() {
		return id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Supply<?,?> getSource() {
		return source;
	}

	public void setSource(Supply<?,?> source) {
		this.source = source;
	}
	
	

}
