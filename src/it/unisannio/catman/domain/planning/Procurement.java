package it.unisannio.catman.domain.planning;

import java.util.List;
import java.util.Set;

import it.unisannio.catman.domain.equipment.Materiel;
import it.unisannio.catman.domain.planning.client.ProcurementProxy;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Procurement extends Requirement {
	
	public static Procurement findProcurement(Long id) {
		return find(Procurement.class, id);
	}
	
	public static List<Procurement> findAll() {
		return findAll(Procurement.class);
	}
	
	public static List<Procurement> listByPlan(Plan p, int start, int len) {
		return listByQuery(Procurement.class, start, len, "SELECT p FROM Procurement p WHERE p.plan = ?1", p);
	}
	
	public static int countByPlan(Plan p) {
		return countByQuery("SELECT COUNT(p) FROM Procurement p WHERE p.plan = ?1", p);
	}

	@ManyToOne
	private Materiel materiel;
	
	@OneToMany
	private Set<Source> sources;

	@Override
	public String getDescription() {
		return materiel.getName();
	}

	@Override
	public int getQuantityFilled() {
		int counter = 0;
		for(Source s : getSources()) {
			counter += s.getQuantity();
		}
		
		return counter;
	}
	
	public Set<Source> getSources() {
		return sources;
	}
	
	public Materiel getMateriel() {
		return materiel;
	}
	
	public void setMateriel(Materiel m) {
		this.materiel = m;
	}
}
