package it.unisannio.catman.domain.planning;

import java.util.ArrayList;
import java.util.List;
import it.unisannio.catman.domain.equipment.Materiel;
import it.unisannio.catman.domain.equipment.Supply;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

@SuppressWarnings("rawtypes")
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
	
	@AssertTrue(message = "A requirement for the same materiel can't be added twice")
	private boolean isUnique() {
		List<Procurement> procurements = getPlan().getProcurements();
		for(Procurement p : procurements) {
			Materiel m = p.getMateriel();
			if( p != this && this.getMateriel().equals(m))
				return false;
		}

		return true;
	}

	public void move(int quantity, Supply supply){
		if(quantity == 0)
			return;
		if(quantity + getQuantityFilled() > getQuantity())
			throw new IllegalArgumentException("Cannot add more than required units.");
		if(quantity < 0 && Math.abs(quantity) > getQuantityFilled())
			throw new IllegalArgumentException("Cannot remove more than available units.");
		if(quantity > supply.getQuantity())
			throw new IllegalArgumentException("Cannot move more than " + quantity + " units. " + supply.getQuantity() + " available in supply");
		Source source = findOrCreateSource(supply);
		source.getSupply().setQuantity(supply.getQuantity() - quantity);
		source.setQuantity(source.getQuantity()+quantity);
		
	}

	private Source findOrCreateSource(Supply supply) {
		List<Source> sources = findByQuery("SELECT s FROM Procurement p " +
				"INNER JOIN p.sources s " +
				"WHERE s.supply = ?1 " +
				"AND p = ?2", supply,this);
		if(sources.isEmpty()) {
			Source source = new Source();
			source.setSupply(supply);
			source.persist();
			addSource(source);
			return source;
		}
		return sources.get(0);
	}
}
