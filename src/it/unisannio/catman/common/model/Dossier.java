package it.unisannio.catman.common.model;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Dossier {
	public static enum Status {
		AWAITING_FOR_INFORMATION,
		QUOTE_GENERATED,
		QUOTE_ACCEPTED,
		AWAITING_GUEST_LIST,
		EVENT_READY_FOR_PLANNING,
		EVENT_PLANNED,
		EVENT_READY,
		EVENT_EXECUTING,
		EVENT_ENDED,
		BILL_GENERATED,
		BILL_PAID;
		
	}
	@Id
	private long id;
	
	private Customer customer;
	private Status status;
	
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
}
