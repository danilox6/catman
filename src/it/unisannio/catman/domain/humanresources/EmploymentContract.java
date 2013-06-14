package it.unisannio.catman.domain.humanresources;

import it.unisannio.catman.domain.planning.Position;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

@Entity
public class EmploymentContract extends Contract {
	public static EmploymentContract findEmploymentContract(Long id) {
		return find(EmploymentContract.class, id);
	}
	
	@NotNull
	private Date startDate = new Date();
	
	private Date endDate;
	
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
	
	public void assignTo(Position p) {
		if(getPiecework().getQualification().equals(p.getQualification()))
			positions.add(p);
		else
			throw new IllegalArgumentException("Personnel must be qualified to cover a position");
	}

	@AssertTrue(message = "End date must be either blank (open ended contract) or be after start date")
	private boolean isEndDateValid() {
		return endDate == null || endDate.after(startDate);
	}
}
