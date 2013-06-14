package it.unisannio.catman.domain.planning;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import it.unisannio.catman.domain.humanresources.Contract;
import it.unisannio.catman.domain.humanresources.EmploymentContract;
import it.unisannio.catman.domain.humanresources.FreelanceContract;
import it.unisannio.catman.domain.humanresources.Piecework;
import it.unisannio.catman.domain.humanresources.Qualification;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

@Entity
public class Position extends Requirement {
	
	public static Position findPosition(Long id) {
		return find(Position.class, id);
	}

	public static List<Position> listByPlan(Plan p, int start, int len) {
		return listByQuery(Position.class, start, len, "SELECT p FROM Position p WHERE p.plan = ?1", p);
	}
	
	public static int countByPlan(Plan p) {
		return countByQuery("SELECT COUNT(p) FROM Position p WHERE p.plan = ?1", p);
	}
	
	public static void delete(List<Long> keys) {
		deleteByKeys(Position.class, keys);
	}
	
	public static void deleteByPlan(Plan p, List<Long> excludedKeys) {
		deleteByQuery(Position.class, excludedKeys, "SELECT p FROM Position p WHERE p.plan = ?1", p);
	}
	
	public static List<Position> findByPiecework(Piecework pw) {
		return findByQuery("SELECT p FROM Piecework pw, Qualification q, Position p WHERE pw.qualification = q AND p.qualification = q AND pw = ?1 AND 0 = (SELECT COUNT(fc) FROM FreelanceContract fc, Position p2 WHERE fc.position = p2 AND p2 = p)", pw);
	}
	
	@NotNull
	@ManyToOne
	Qualification qualification;
	
	@ManyToMany(mappedBy = "positions")
	private List<EmploymentContract> employedFillers = new ArrayList<EmploymentContract>();
	
	@OneToMany(mappedBy = "position", cascade = CascadeType.ALL)
	private List<FreelanceContract> freelanceFillers = new ArrayList<FreelanceContract>();

	@ManyToOne
	@NotNull
	private Plan plan;

	@Override
	public String getDescription() {
		return qualification.getName();
	}

	@Override
	public int getQuantityFilled() {
		return employedFillers.size() + freelanceFillers.size();
	}
	
	public List<Contract> getFillers() {
		ArrayList<Contract> total = new ArrayList<Contract>();
		total.addAll(employedFillers);
		total.addAll(freelanceFillers);
		return total;
	}
	
	public void setQualification(Qualification q) {
		this.qualification = q;
	}
	
	public Qualification getQualification() {
		return this.qualification;
	}
	
	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}
	
	
	// FIXME Non funziona
	@AssertTrue(message = "A requirement for the same qualification can't be added twice")
	private boolean isUnique() {
		Set<Qualification> qualifications = new HashSet<Qualification>();
		List<Position> positions = getPlan().getPositions();
		for(Position p : positions) {
			Qualification q = p.getQualification();
			if(qualifications.contains(q) && p != this)
				return false;
			
			qualifications.add(q);
		}
		
		return true;
	}
}
