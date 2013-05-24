package it.unisannio.catman.domain.equipment;

import it.unisannio.catman.common.server.AbstractEntity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.OneToOne;
import javax.persistence.Version;

@Entity
@IdClass(Supply.Key.class)
public class Supply<T extends Supply<T,S>, S extends Supplier<T,S>> extends AbstractEntity<Supply.Key>{
	
	public static class Key implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = 2861529191874673599L;
		private long supplierId;
		private long materialId;
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + (int) (materialId ^ (materialId >>> 32));
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
			Key other = (Key) obj;
			if (materialId != other.materialId)
				return false;
			if (supplierId != other.supplierId)
				return false;
			return true;
		}
	}
	
	public static Supply<?,?> findSupply(Supply.Key key) {
		return find(Supply.class, key);
	}
	
	@Version
	private int version;
	
	@Id
	private long supplierId;
	
	@Id
	private long materialId;
	
	@OneToOne
	private S supplier;
	
	@OneToOne
	private Material material;
	
	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
		materialId = material.getId();
	}

	private int quantity;

	public Supply() {
		// TODO Auto-generated constructor stub
	}
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public S getSupplier() {
		return supplier;
	}

	public void setSupplier(S supplier) {
		this.supplier = supplier;
		supplierId = supplier.getId();
	}
	
	public int getVersion() {
		return version;
	}
	
	public Key getId() {
		Key k = new Key();
		
		k.materialId = materialId;
		k.supplierId = supplierId;
		
		return k;
	}

}
