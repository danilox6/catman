package it.unisannio.catman.domain.humanresources;

import java.util.Date;

import it.unisannio.catman.domain.planning.Position;
import it.unisannio.catman.domain.workflow.Event;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

@Entity
public class FreelanceContract extends Contract {
	public static FreelanceContract findFreelanceContract(Long id) {
		return find(FreelanceContract.class, id);
	}

	@ManyToOne
	@NotNull(message = "A position must necessarily be picked")
	private Position position;
	

	public Event getEvent() {
		return position.getPlan().getDossier();
	}

	public void setPosition(Position p) {
		this.position = p;
	}
	
	public Position getPosition() {
		return position;
	}
	
	@Override
	public Date getStartDate() {
		return getEvent().getStartDate();
	}

	@Override
	public Date getEndDate() {
		return getEvent().getEndDate();
	}
	
	@AssertTrue(message = "Position is already filled")
	private boolean isPositionAvailable() {
		if(position == null)
			return true;
		
		return (position.getQuantityFilled() <= position.getQuantity());
	}
	
	@AssertFalse(message = "Worker is already assigned to given position")
	private boolean isAlreadyAssigned(){
		for(Contract c : position.getFillers())
			if(c.getPiecework().getWorker().equals(this.getPiecework().getWorker()))
				if(c.getPiecework().getQualification().equals(this.getPiecework().getQualification()))
					return true;
		return false;
	}
	
	
	@AssertTrue(message = "Cannot assign a contract to a position with different qualification")
	private boolean isSameQualification(){
		return this.getPiecework().getQualification().equals(position.getQualification());
	}
}
