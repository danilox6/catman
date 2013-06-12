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
		return findByQuery("SELECT c FROM Contract c, Worker w, Piecework p WHERE p.worker = ?1 AND c.piecework = p", worker);
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

	
}
