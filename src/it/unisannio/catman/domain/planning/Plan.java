package it.unisannio.catman.domain.planning;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;

import it.unisannio.catman.domain.workflow.Event;
import it.unisannio.catman.domain.workflow.EventDocument;
import it.unisannio.catman.domain.workflow.EventStatus;

@Entity
public class Plan extends EventDocument {
	
	public static Plan findPlan(Long id) {
		return find(Plan.class, id);
	}
	
	@OneToMany(mappedBy="plan", targetEntity = Requirement.class)
	private List<Procurement> procurements = new ArrayList<Procurement>();
	
	@OneToMany(mappedBy="plan", targetEntity = Requirement.class)
	private List<Position> positions = new ArrayList<Position>();
	
	public boolean isComplete(){
		List<Procurement> procurements = getProcurements();
		List<Position> positions = getPositions();
		
		// not even started
		if(procurements == null && positions == null)
			return false;
		
		if(procurements != null) {
			for(Procurement p : procurements) {
				if(!p.isFilled())
					return false;
			}
		}
		
		if(positions != null) {
			for(Position p : positions) {
				if(!p.isFilled())
					return false;
			}
		}
		
		return true;
	}
	
	public Procurement getFake() {
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
	
	@Override
	public void setDossier(Event event) {
		if(event == null && getDossier() != null) {
			getDossier().setStatus(EventStatus.NOT_PLANNED);
		} else {
			event.setStatus(EventStatus.PLANNING);
		}
		
		super.setDossier(event);
	}
	
	
	@PrePersist
	private void updateStatus() {
		Event evt = getDossier();
		if(evt != null) {
			evt.setStatus(isComplete() ? EventStatus.PLANNED : EventStatus.PLANNING);
		}
	}

	@Override
	public String getTitle() {
		return "Plan";
	}
}
