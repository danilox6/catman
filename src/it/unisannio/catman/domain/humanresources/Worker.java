package it.unisannio.catman.domain.humanresources;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import it.unisannio.catman.domain.contacts.Contactable;

@Entity
public class Worker extends Contactable {
	public static Worker findWorker(Long id) {
		return find(Worker.class, id);
	}
	
	public static List<Worker> findAll() {
		return findAll(Worker.class);	
	}
	
	public static List<Worker> listWorkersSource(int start, int length){
		return listByQuery(Worker.class, start, length, "SELECT w FROM  JobBoard jb " +
														"LEFT OUTER JOIN jb.workers w " +
														"WHERE w.candidate = ?1 " +
														"AND jb IS NULL",false);
	}
	
	public static List<Worker> listCandidates(int start, int length){
		return listByQuery(Worker.class, start, length, "SELECT w FROM Worker w " +
														"WHERE w.candidate = ?1",true);
	}
	
	public static List<Worker> listByJobBoard(JobBoard jobBoard, int start, int length){
		return listByQuery(Worker.class, start, length, "SELECT w FROM Worker w " +
														"INNER JOIN w.jobBoards jb " +
														"WHERE jb = ?1", jobBoard);
	}
	
	public static int countWorkersSource(){
		return countByQuery("SELECT COUNT(w) FROM  JobBoard jb " +
				"LEFT OUTER JOIN jb.workers w " +
				"WHERE w.candidate = ?1 " +
				"AND jb IS NULL",false);
	}
	
	public static int countCandidates(){
		return countByQuery("SELECT COUNT(w) FROM Worker w WHERE w.candidate = ?1",true);
	}
	
	public  static int countByJobBoard(JobBoard jobBoard){
		return countByQuery("SELECT COUNT(w) FROM Worker w " +
							"INNER JOIN w.jobboards jb " +
							"WHERE jb = ?1", jobBoard);
	}
	
	@Id 
	@GeneratedValue
	private long id;
	
	@Version 
	private int version;
	
	private boolean candidate;
	
	private String resume;
	
	@OneToMany(mappedBy="worker", orphanRemoval=true, cascade={CascadeType.ALL})
	private Set<Contract> contracts;
	
	@ManyToMany
	private Set<Qualification> qualifications;
	
	public Set<Qualification> getQualifications() {
		return qualifications;
	}
	
	public void addQualification(Qualification q) {
		qualifications.add(q);
	}
	
	public void removeQualification(Qualification q) {
		qualifications.remove(q);
	}
	
	public boolean hasQualification(Qualification q) {
		return qualifications.contains(q);
	}
	
	public Set<Contract> getContracts() {
		return contracts;
	}

	public void addContract(Contract c) {
		this.contracts.add(c);
	}
	
	public void removeContract(Contract c) {
		this.contracts.remove(c);
	}


	@Override
	public int getVersion() {
		return version;
	}

	@Override
	public Long getId() {
		return id;
	}

	public boolean isCandidate() {
		return candidate;
	}

	public void setCandidate(boolean candidate) {
		this.candidate = candidate;
	}
	
	public boolean isHired() {
		for(Contract c : getContracts()) {
			if(c.isCurrent() && !c.isFreelance())
				return true;
		}
		
		return false;
	}
	
	public boolean isFreelance() {
		for(Contract c : getContracts()) {
			if(c.isCurrent() && c.isFreelance())
				return true;
		}
		
		return false;
	}
	
	public boolean isWorking(){
		for(Contract c : getContracts()) {
			if(c.isCurrent())
				return true;
		}
		
		return false;
	}

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}
	
	
}
