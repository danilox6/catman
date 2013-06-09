package it.unisannio.catman.domain.equipment;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class SupplyKey implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2861529191874673599L;
	@Column
	long supplierId;
	@Column
	long materielId;
	
	public long getSupplierId() {
		return supplierId;
	}
	
	public void setSupplierId(long id) {
		this.supplierId = id;
	}
	
	public long getMaterielId() {
		return materielId;
	}
	
	public void setMaterielId(long id) {
		this.materielId = id;
	}
	 
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (materielId ^ (materielId >>> 32));
		result = prime * result + (int) (supplierId ^ (supplierId >>> 32));
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SupplyKey other = (SupplyKey) obj;
		if (materielId != other.materielId)
			return false;
		if (supplierId != other.supplierId)
			return false;
		return true;
	}
}