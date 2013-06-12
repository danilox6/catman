package it.unisannio.catman.domain.humanresources;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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

	public static List<Worker> listAll(int start, int length){
		return list(Worker.class, start,length);
	}

	public static int count(){
		return count(Worker.class);
	}

	// FIXME Tutte query da cambiare
	public static List<Worker> listByQualificationInWorkersSource(Qualification qualification, int start, int length){
		return listByQuery(Worker.class, start, length,"SELECT w FROM JobBoard jb " +
				"RIGHT OUTER JOIN jb.workers w " +
				"INNER JOIN w.qualifications q "+
				"WHERE w.candidate = ?1 " +
				"AND jb IS NULL " +
				"AND q = ?2",false,qualification);
	}

	public static int countByQualificationInWorkersSource(Qualification qualification){
		return countByQuery("SELECT COUNT(w) FROM JobBoard jb " +
				"RIGHT OUTER JOIN jb.workers w " +
				"INNER JOIN w.qualifications q "+
				"WHERE w.candidate = ?1 " +
				"AND jb IS NULL " +
				"AND q = ?2", false, qualification);
	}

	public static List<Worker> listByQualificationInCandidates(Qualification qualification, int start, int length){
		return listByQuery(Worker.class, start, length,"SELECT w FROM Worker w " +
				"INNER JOIN w.qualifications q "+
				"WHERE w.candidate = ?1 " +
				"AND q = ?2", true, qualification);
	}

	public static int countByQualificationInCandidates(Qualification qualification){
		return countByQuery("SELECT COUNT(w) FROM Worker w " +
				"INNER JOIN w.qualifications q "+
				"WHERE w.candidate = ?1 " +
				"AND q = ?2", true, qualification);
	}

	public static List<Worker> listByQualificationInJobBoard(Qualification qualification, JobBoard jobBoard, int start, int length){
		return listByQuery(Worker.class, start, length,"SELECT w FROM JobBoard jb " +
				"INNER JOIN jb.workers w " +
				"INNER JOIN w.qualifications q "+
				"WHERE jb = ?1 " +
				"AND q = ?2", jobBoard, qualification);
	}

	public static int countByQualificationInJobBoard(Qualification qualification, JobBoard jobBoard){
		return countByQuery("SELECT COUNT(w) FROM JobBoard jb " +
				"INNER JOIN jb.workers w " +
				"INNER JOIN w.qualifications q "+
				"WHERE jb = ?1 " +
				"AND q = ?2", jobBoard, qualification);
	}

	//FIXME forse non è necessaria, serve solo il relativo count
	public static List<Worker> listInWorkersSource(int start, int length){
		return listByQuery(Worker.class, start, length, "SELECT w FROM  JobBoard jb " +
				"RIGHT OUTER JOIN jb.workers w " +
				"WHERE w.candidate = ?1 " +
				"AND jb IS NULL",false);
	}

	//FIXME forse non è necessaria, serve solo il relativo count
	public static List<Worker> listInCandidates(int start, int length){
		return listByQuery(Worker.class, start, length, "SELECT w FROM Worker w " +
				"WHERE w.candidate = ?1",true);
	}

	//FIXME forse non è necessaria, serve solo il relativo count
	public static List<Worker> listByJobBoard(JobBoard jobBoard, int start, int length){
		return listByQuery(Worker.class, start, length, "SELECT w FROM JobBoard jb " +
				"INNER JOIN jb.workers w " +
				"WHERE jb = ?1", jobBoard);
	}

	public static int countInWorkersSource(){
		return countByQuery("SELECT COUNT(w) FROM  JobBoard jb " +
				"RIGHT OUTER JOIN jb.workers w " +
				"WHERE w.candidate = ?1 " +
				"AND jb IS NULL",false);
	}

	public static int countInCandidates(){
		return countByQuery("SELECT COUNT(w) FROM Worker w WHERE w.candidate = ?1",true);
	}

	public  static int countByJobBoard(JobBoard jobBoard){
		return countByQuery("SELECT COUNT(w) FROM JobBoard jb " +
				"INNER JOIN jb.workers w " +
				"WHERE jb = ?1", jobBoard);
	}

	@Id 
	@GeneratedValue
	private long id;

	@Version 
	private int version;

	private boolean candidate;

	private String resume;

	@OneToMany(mappedBy="worker", cascade = {CascadeType.ALL})
	private Set<Piecework> pieceworks = new HashSet<Piecework>();

	public Set<Qualification> getQualifications() {
		Set<Qualification> uniqueQualifications = new HashSet<Qualification>();
		for(Piecework p : pieceworks) {
			uniqueQualifications.add(p.getQualification());
		}
		
		return uniqueQualifications;
	}

	public void addPiecework(Piecework q) {
		pieceworks.add(q);
	}

	public void removePiecework(Piecework q) {
		pieceworks.remove(q);
	}

	public boolean hasQualification(Qualification q) {
		return getQualifications().contains(q);
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
	
	public Collection<Contract> getContracts() {
		return Contract.findByWorker(this);
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

	private String name;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}
}
