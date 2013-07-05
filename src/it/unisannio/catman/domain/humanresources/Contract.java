package it.unisannio.catman.domain.humanresources;

import it.unisannio.catman.common.server.AbstractEntity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class Contract extends AbstractEntity<Long> {
	public static Contract findContract(Long id) {
		return find(Contract.class, id);
	}
	
	public static List<Contract> findByWorker(Worker worker) {
		return findByQuery("SELECT DISTINCT c FROM Contract c, Worker w, Piecework p WHERE p.worker = ?1 AND c.piecework = p", worker);
	}
	
	public static List<Contract> listByWorker(Worker worker, int start, int length) {
		return listByQuery(Contract.class, start, length,"SELECT DISTINCT c FROM Contract c, Worker w, Piecework p WHERE p.worker = ?1 AND c.piecework = p", worker);
	}
	
	public static Integer countdByWorker(Worker worker) {
		return countByQuery("SELECT DISTINCT COUNT(c) FROM Contract c, Worker w, Piecework p WHERE p.worker = ?1 AND c.piecework = p", worker);
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private long id;
	
	@Version
	private int version;
	
	@ManyToOne
	@NotNull
	private Piecework piecework;
	
	public Piecework getPiecework() {
		return piecework;
	}

	public void setPiecework(Piecework piecework) {
		this.piecework = piecework;
	}

	public abstract Date getStartDate();

	public abstract Date getEndDate();

	public boolean isCurrent() {
		Date current = new Date();
		return getStartDate().before(current) && (getEndDate() == null || getEndDate().after(current));
	}	

	public Long getId() {
		return id;
	}

	public int getVersion() {
		return version;
	}
	
//	@AssertTrue(message = "Worker has already a contract with the same qualification with overlapping duration")
//	private boolean areContractsOverlapping(){
//		return false;
//		if(this.getPiecework().isFreelance())
//			return false;
		/*
		List<Contract> contracts = findByWorker(this.getPiecework().getWorker());
		for(Contract contract : contracts){
			if(contract instanceof EmploymentContract){
				if(contract.getEndDate() == null){
					if(this.getEndDate() == null)
						return true;
					else if(this.getEndDate().after(contract.getStartDate()))
						return true;
				}else if (getStartDate().before(contract.getEndDate()) && getEndDate().after(contract.getEndDate()))
					return true;
				if(DateUtils.isSameDay(getStartDate(), contract.getStartDate()) && DateUtils.isSameDay(getEndDate(), contract.getEndDate()));
					return true;
			}
		}
		return false;*/
//	}
	

	
}
