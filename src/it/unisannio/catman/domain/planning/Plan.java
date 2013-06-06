package it.unisannio.catman.domain.planning;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import it.unisannio.catman.domain.workflow.EventDocument;
import it.unisannio.catman.domain.workflow.EventStatus;

@Entity
public class Plan extends EventDocument {
	
	public static Plan findPlan(Long id) {
		return find(Plan.class, id);
	}
	
	@OneToMany(mappedBy="plan", targetEntity = Requirement.class)
	private List<Procurement> procurements;
	
	@OneToMany(mappedBy="plan", targetEntity = Requirement.class)
	private List<Position> positions;
	
	public boolean isComplete(){
		for(Procurement p : getProcurements()) {
			if(!p.isFilled())
				return false;
		}
		
		for(Position p : getPositions()) {
			if(!p.isFilled())
				return false;
		}
		
		return true;
	}

	@Override
	public EventStatus getStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Procurement> getProcurements() {
		return procurements;
	}

	public void addProcurement(Procurement p) {
		this.procurements.add(p);
	}
	
	public void removeProcurement(Procurement p) {
		this.procurements.remove(p);
	}

	public List<Position> getPositions() {
		return positions;
	}

	public void addPosition(Position p) {
		this.positions.add(p);
	}
	
	public void removePosition(Position p) {
		this.positions.remove(p);
	}
}
