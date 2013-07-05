package it.unisannio.catman.domain.humanresources;

import it.unisannio.catman.domain.planning.Position;
import it.unisannio.catman.domain.workflow.Event;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

@Entity

public class EmploymentContract extends Contract {
	public static EmploymentContract findEmploymentContract(Long id) {
		return find(EmploymentContract.class, id);
	}
	
	@NotNull(message = "Start date cannot be null")
	@Temporal(TemporalType.DATE)
	private Date startDate;
	
	@Temporal(TemporalType.DATE)
	private Date endDate;
	
	@Valid
	@ManyToMany
	private List<Position> positions = new ArrayList<Position>();

	public boolean isOpenEnded() {
		return endDate == null;
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
	
	public void assignTo(@Valid Position p) {
		if(isAlreadyAssigned(p))
			throw new IllegalArgumentException("Worker is already assigned to given position");
		if(!isEventValid(p.getPlan().getDossier()))
			throw new IllegalArgumentException("Event period must be included in contract duration");
		if(!getPiecework().getQualification().equals(p.getQualification()))
			throw new IllegalArgumentException("Cannot assign a contract to a position with different qualification");
		positions.add(p);
			
		
//		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
//		Set<ConstraintViolation<EmploymentContract>> violations1 = validator.validate(this);
//		if(!violations1.isEmpty())
//			throw new ConstraintViolationException(new HashSet<ConstraintViolation<?>>(violations1));
//		Set<ConstraintViolation<Position>> violations2 = validator.validate(p);
//		if(!violations2.isEmpty())
//			throw new ConstraintViolationException(new HashSet<ConstraintViolation<?>>(violations2));
	}

	@AssertTrue(message = "End date must be either blank (open ended contract) or be after start date")
	private boolean isEndDateValid() {
		return endDate == null || endDate.after(startDate);
	}
	
	
//	@AssertFalse(message = "Worker is already assigned to given position")
//	private boolean isAlreadyAssigned(){
//		for(Position p : positions)
//			if(isAlreadyAssigned(p))
//				return true;
//		return false;
//	}
	
	private boolean isAlreadyAssigned(Position position){
		for(Contract c : position.getFillers())
			if(c.getPiecework().getWorker().equals(this.getPiecework().getWorker()))
				if(c.getPiecework().getQualification().equals(this.getPiecework().getQualification()))
					return true;
		return false;
	}
	
	
	@AssertTrue(message = "Cannot assign a contract to a position with different qualification")
	private boolean isSameQualification(){
		for(Position p: positions)
			if(!this.getPiecework().getQualification().equals(p.getQualification()))
				return false;
		return true;
	}
	
	@AssertFalse(message = "Worker has already a contract with the same qualification in an overlapping period")
	private boolean isContractOverlapping(){
		List<Contract> contracts = new ArrayList<Contract>();
		for(Piecework p : this.getPiecework().getWorker().getPieceworks())
			contracts.addAll(p.getContracts());
		
		for(Contract contract : contracts){
			if(contract instanceof EmploymentContract && !contract.equals(this)){
				if(contract.getEndDate() == null){
					if(this.getEndDate() == null)
						return true;
					else if(this.getEndDate().after(contract.getStartDate()))
						return true;
				}else if(getEndDate() == null){
					if(contract.getEndDate().after(getStartDate()))
						return true;
				}else {
					if (getStartDate().before(contract.getEndDate()) && getEndDate().after(contract.getStartDate()))
						return true;
					if(isSameDay(getStartDate(), contract.getStartDate()) || isSameDay(getEndDate(), contract.getEndDate()) || 
							isSameDay(getStartDate(), contract.getEndDate()) || isSameDay(getEndDate(), contract.getStartDate()))
						return true;
				}
			}
		}
		return false;
	}
	
	@AssertTrue(message = "Event period must be included in contract duration")
	private boolean isEventValid(){
		for(Position p : positions)
			if(!isEventValid(p.getPlan().getDossier()))
				return false;
		return true;
	}
	
	private boolean isEventValid(Event event){
		if(getEndDate() == null)
			return getStartDate().before(event.getEndDate());
		if (getStartDate().before(event.getStartDate()) &&  getEndDate().after(event.getEndDate()))
			return true;
		return isSameDay(getStartDate(), event.getStartDate()) && isSameDay(getEndDate(), event.getEndDate());
	}
	
	private boolean isSameDay(Date day1, Date day2){
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		return formatter.format(day1).equals(formatter.format(day2));
//		return DateFormat.getInstance().format(day1).equals(DateFormat.getInstance().format(day2));
	}
	
//	private static class DateFormat extends DateTimeFormat{
//		
//		private static DateFormat instance;
//
//		private DateFormat() {
//			super("dd/MM/yyyy");
//		}
//		
//		public static DateFormat getInstance() {
//			if(instance == null)
//				instance = new DateFormat();
//			return instance;
//		}
//		
//	}
}
