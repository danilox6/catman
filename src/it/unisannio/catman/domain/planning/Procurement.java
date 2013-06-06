package it.unisannio.catman.domain.planning;

import java.util.Set;

import it.unisannio.catman.domain.equipment.Materiel;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Procurement extends Requirement {
	
	public static Procurement findProcurement(Long id) {
		return find(Procurement.class, id);
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
}
