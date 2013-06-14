package it.unisannio.catman.domain.planning;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import it.unisannio.catman.domain.equipment.Materiel;
import it.unisannio.catman.domain.equipment.Supply;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

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

	public static void delete(List<Long> ids) {
		deleteByKeys(Procurement.class, ids);
	}

	public static void deleteByPlan(Plan p, List<Long> exclusions) {
		deleteByQuery(Procurement.class, exclusions, "SELECT p FROM Procurement p WHERE p.plan = ?1", p);
	}

	@ManyToOne
	@NotNull
	private Materiel materiel;

	@OneToMany
	private List<Source> sources = new ArrayList<Source>();

	@ManyToOne
	@NotNull
	private Plan plan;

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

	public List<Source> getSources() {
		return sources;
	}
	
	public void addSource(Source source){
		sources.add(source);
	}

	public Materiel getMateriel() {
		return materiel;
	}

	public void setMateriel(Materiel m) {
		this.materiel = m;
	}


	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}
	
	


	// FIXME Non funziona
	@AssertTrue(message = "A requirement for the same materiel can't be added twice")
	private boolean isUnique() {
		Set<Materiel> materiels = new HashSet<Materiel>();
		List<Procurement> procurements = getPlan().getProcurements();
		for(Procurement p : procurements) {
			Materiel m = p.getMateriel();
			if(materiels.contains(m) && p != this)
				return false;

			materiels.add(m);
		}

		return true;
	}

	public void move(int quantity, Supply supply){
		if(quantity == 0)
			return;
		if(quantity + getQuantityFilled() > getQuantity())
			throw new IllegalArgumentException("Cannot add more than required units.");
		if(quantity < getQuantityFilled())
			throw new IllegalArgumentException("Cannot remove more than available units.");
		if(quantity > supply.getQuantity())
			throw new IllegalArgumentException("Cannot move more than " + quantity + " units. " + supply.getQuantity() + " available in supply");
		Source source = findOrCreateSource(supply);
		source.getSupply().setQuantity(supply.getQuantity() - quantity);
		source.setQuantity(source.getQuantity()+quantity);
		addSource(source);
	}

	private Source findOrCreateSource(Supply supply) {
		List<Source> sources = findByQuery("SELECT s FROM Source s WHERE s.supply = ?1 ", supply);
		if(sources.isEmpty()) {
			Source source = new Source();
			source.setSupply(supply);
			source.persist();
			return source;
		}
		return sources.get(0);
	}
}
