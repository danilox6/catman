package it.unisannio.catman.domain.equipment;

import it.unisannio.catman.common.server.AbstractEntity;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.Version;

/* TODO Questa struttura porta ad avere delle colonne duplicate per gli id. Funziona, ma sarebbe da sistemare */
@Entity
@IdClass(SupplyKey.class)
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public abstract class Supply<T extends Supply<T,S>, S extends Supplier<T,S>> extends AbstractEntity<SupplyKey>{
	
	public static Supply<?,?> findSupply(SupplyKey supplyKey) {
		return find(Supply.class, supplyKey);
	}
	
	@Version
	private int version;
	
	@Id
	protected long supplierId;
	
	@Id
	private long materialId;
	
	@OneToOne
	private Materiel materiel;
	
	public Materiel getMateriel() {
		return materiel;
	}

	public void setMateriel(Materiel materiel) {
		this.materiel = materiel;
		materialId = materiel.getId();
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
	
	public SupplyKey getId() {
		SupplyKey k = new SupplyKey();
		
		k.materialId = materialId;
		k.supplierId = supplierId;
		
		return k;
	}

}
