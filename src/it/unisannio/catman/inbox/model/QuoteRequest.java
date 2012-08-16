package it.unisannio.catman.inbox.model;

import it.unisannio.catman.common.model.Customer;
import it.unisannio.catman.common.model.Event;
import it.unisannio.catman.common.model.Event.Type;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class QuoteRequest implements Serializable {
	private static final long serialVersionUID = 2224336065078166199L;
	
	@Id
	private int id;
	private Date creationDate;
	private Event.Type eventType;
	private Date eventDate;
	private int eventDuration;
	private int mealsPerDay;
	private int guestCount;
	private String customerName;
	private Customer.Type customerType;
	private String eventLocation;
	private String customerPhone;
	private String customerFax;
	private String customerEmail;
	private String customerAddress;
	
	public Date getCreationDate() {
		return creationDate;
	}
	
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	public Event.Type getEventType() {
		return eventType;
	}
	
	public void setEventType(Event.Type eventType) {
		this.eventType = eventType;
	}
	
	public Date getEventDate() {
		return eventDate;
	}
	
	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}
	
	public int getMealsPerDay() {
		return mealsPerDay;
	}
	
	public void setMealsPerDay(int mealsPerDay) {
		this.mealsPerDay = mealsPerDay;
	}
	
	public int getEventDuration() {
		return eventDuration;
	}
	
	public void setEventDuration(int eventDuration) {
		this.eventDuration = eventDuration;
	}
	
	public int getGuestCount() {
		return guestCount;
	}
	
	public void setGuestCount(int guestCount) {
		this.guestCount = guestCount;
	}
	
	public String getCustomerName() {
		return customerName;
	}
	
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	public Customer.Type getCustomerType() {
		return customerType;
	}
	
	public void setCustomerType(Customer.Type customerType) {
		this.customerType = customerType;
	}
	
	public String getEventLocation() {
		return eventLocation;
	}
	
	public void setEventLocation(String eventLocation) {
		this.eventLocation = eventLocation;
	}
	
	public String getCustomerPhone() {
		return customerPhone;
	}
	
	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}
	
	public String getCustomerFax() {
		return customerFax;
	}
	
	public void setCustomerFax(String customerFax) {
		this.customerFax = customerFax;
	}
	
	public String getCustomerEmail() {
		return customerEmail;
	}
	
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	
	public String getCustomerAddress() {
		return customerAddress;
	}
	
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
	
	public int getId() {
		return id;
	}
}
