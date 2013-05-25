package it.unisannio.catman.domain.equipment;

import it.unisannio.catman.common.server.AbstractEntity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.Version;

/* TODO Questa struttura porta ad avere delle colonne duplicate per gli id. Funziona, ma sarebbe da sistemare */
@Entity
@IdClass(Supply.Key.class)
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public abstract class Supply<T extends Supply<T,S>, S extends Supplier<T,S>> extends AbstractEntity<Supply.Key>{
	
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
	protected long supplierId;
	
	@Id
	private long materialId;
	
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

	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public abstract S getSupplier();
	public abstract void setSupplier(S supplier);
	

	
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
