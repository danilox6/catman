package it.unisannio.catman.domain.equipment;

import java.util.List;

import it.unisannio.catman.common.server.AbstractEntity;


import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.Version;

/* TODO Questa struttura porta ad avere delle colonne duplicate per gli id. Funziona, ma sarebbe da sistemare */
//TODO Michele, forse basta mettere materiel e supplier solamente in SupplyKey ?
@Entity
//@IdClass(SupplyKey.class)
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class Supply<T extends Supply<T,S>, S extends Supplier<T,S>> extends AbstractEntity<SupplyKey>{
	
	public static Supply<?,?> findSupply(SupplyKey supplyKey) {
		return find(Supply.class, supplyKey);
	}
	
	@Version
	private int version;
	
	@EmbeddedId
	private SupplyKey id = new SupplyKey();
	
	@OneToOne
	private Materiel materiel;
	
	public Materiel getMateriel() {
		return materiel;
	}

	public void setMateriel(Materiel materiel) {
		this.materiel = materiel;
		id.materielId = materiel.getId();
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
		return id;
	}

	@SuppressWarnings("rawtypes")
	public static List<Supply> listByMateriel(Materiel m, int start, int length){
		return listByQuery(Supply.class, start, length, "SELECT s FROM Supply s " +
				"WHERE s.materiel = ?1", m);
	}
	
	public static int countByMateriel(Materiel m){
		return countByQuery("SELECT COUNT(s) FROM Supply s " +
				"WHERE s.materiel = ?1", m);
	}
	
}
