package it.unisannio.catman.domain.humanresources;

import java.util.Date;

import it.unisannio.catman.domain.planning.Position;
import it.unisannio.catman.domain.workflow.Event;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
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
		
		return position.getQuantityFilled() <= position.getQuantity();
	}
}
