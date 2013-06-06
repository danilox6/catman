package it.unisannio.catman.domain.humanresources;

import it.unisannio.catman.common.server.AbstractEntity;
import it.unisannio.catman.domain.workflow.Event;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

@Entity
public class Contract extends AbstractEntity<Long>{
	public static Contract findContract(Long id) {
		return find(Contract.class, id);
	}

	@Id
	@GeneratedValue
	private long id;
	
	@Version
	private int version;
	
	@ManyToOne
	@NotNull
	private Worker worker;
	
	@ManyToOne
	private Event event;
	
	@NotNull
	private Date startDate = new Date();
	
	private Date endDate;
	
	@NotNull
	private Qualification qualification;
	
	public Qualification getQualification() {
		return qualification;
	}
	
	public void setQualification(Qualification q) {
		this.qualification = q;
	}
	
	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}
	
	public boolean isOpenEnded() {
		return endDate == null;
	}
	
	public boolean isFreelance() {
		return event != null;
	}

	private float wage;
	
	public float getWage() {
		return wage;
	}

	public void setWage(float wage) {
		this.wage = wage;
	}

	public Worker getWorker() {
		return worker;
	}

	public void setWorker(Worker worker) {
		this.worker = worker;
	}
	
	public Long getId() {
		return id;
	}
	
	public int getVersion() {
		return version;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public boolean isCurrent() {
		Date current = new Date();
		return startDate.before(current) && (endDate == null || endDate.after(current));
	}
	
	@AssertTrue(message = "End date must be either blank (open ended contract) or be after start date")
	private boolean isEndDateValid() {
		return endDate == null || endDate.after(startDate);
	}
	
	@AssertTrue(message = "Worker doesn't have proper qualification for the contract")
	private boolean isQualificationValid() {
		return worker.hasQualification(qualification);
	}
	
}
