package it.unisannio.catman.domain.humanresources;

import java.util.Date;

import it.unisannio.catman.domain.workflow.Event;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class FreelanceContract extends Contract {
	public static FreelanceContract findFreelanceContract(Long id) {
		return find(FreelanceContract.class, id);
	}

	@ManyToOne
	@NotNull
	private Event event;
	
	public void setEvent(Event event) {
		this.event = event;
	}


	public Event getEvent() {
		return event;
	}


	@Override
	public Date getStartDate() {
		return event.getStartDate();
	}

	@Override
	public Date getEndDate() {
		return event.getEndDate();
	}
	
	

}
