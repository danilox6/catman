package it.unisannio.catman.domain.humanresources;

import java.util.List;

import it.unisannio.catman.common.server.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

@Entity
public class Piecework extends AbstractEntity<Long> {
	public static Piecework findPiecework(Long id) {
		return find(Piecework.class, id);
	}

	@Id
	@GeneratedValue
	private long id;
	
	@Version
	private int version;
	
	@ManyToOne
	@NotNull
	private Worker worker;
	
	@NotNull
	@ManyToOne
	private Qualification qualification;
	
	@OneToMany(mappedBy = "piecework")
	private List<Contract> contracts;
	
	private float pay;
	
	private boolean freelance;

	public Worker getWorker() {
		return worker;
	}

	public void setWorker(Worker worker) {
		this.worker = worker;
		this.worker.addPiecework(this);
	}

	public Qualification getQualification() {
		return qualification;
	}

	public void setQualification(Qualification qualification) {
		this.qualification = qualification;
	}
	
	public List<Contract> getContracts() {
		return contracts;
	}

	/**
	 * In case of freelance work, the pay is per day. Otherwise, it's per month.
	 * @return
	 */
	public float getPay() {
		return pay;
	}

	public void setPay(float wage) {
		this.pay = wage;
	}

	public Long getId() {
		return id;
	}

	public int getVersion() {
		return version;
	}

	public boolean isFreelance() {
		return freelance;
	}

	public void setFreelance(boolean freelance) {
		this.freelance = freelance;
	}
	
}
