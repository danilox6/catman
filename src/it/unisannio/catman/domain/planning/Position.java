package it.unisannio.catman.domain.planning;

import java.util.List;

import it.unisannio.catman.domain.humanresources.Contract;
import it.unisannio.catman.domain.humanresources.Qualification;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

@Entity
public class Position extends Requirement {
	
	public static Position findPosition(Long id) {
		return find(Position.class, id);
	}
	
	@NotNull
	@ManyToOne
	Qualification qualification;
	
	@OneToMany
	private List<Contract> fillers;

	@Override
	public String getDescription() {
		return qualification.getName();
	}

	@Override
	public int getQuantityFilled() {
		return fillers.size();
	}
	
	public List<Contract> getFillers() {
		return fillers;
	}
	
	public void addFiller(Contract c) {
		fillers.add(c);
	}
	
	public void removeFiller(Contract c) {
		fillers.remove(c);
	}
	
	@AssertTrue(message = "Contracts must be either specific to this position or open ended")
	private boolean areFillersSpecific() {
		for(Contract filler : fillers) {
			if(filler.isFreelance() && !filler.getEvent().equals(getPlan().getDossier()))
				return false;
		}
		
		return true;
	}
	
}
