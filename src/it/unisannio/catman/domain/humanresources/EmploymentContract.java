package it.unisannio.catman.domain.humanresources;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

@Entity
public class EmploymentContract extends Contract {
	public static EmploymentContract findEmploymentContract(Long id) {
		return find(EmploymentContract.class, id);
	}
	
	@NotNull
	@Temporal(TemporalType.DATE)
	private Date startDate = new Date();
	
	@Temporal(TemporalType.DATE)
	private Date endDate;

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

	@AssertTrue(message = "End date must be either blank (open ended contract) or be after start date")
	private boolean isEndDateValid() {
		return endDate == null || endDate.after(startDate);
	}
}
