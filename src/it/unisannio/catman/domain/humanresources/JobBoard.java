package it.unisannio.catman.domain.humanresources;

import java.util.ArrayList;
import java.util.List;

import it.unisannio.catman.common.server.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Version;

@Entity
public class JobBoard extends AbstractEntity<Long> {
	
	public static JobBoard findJobBoard(Long id) {
		return find(JobBoard.class, id);
	}
	
	public static List<JobBoard> findAll() {
		return findAll(JobBoard.class);
	}
	
	public static List<JobBoard> findByQualification(Qualification q){
		return findByQuery("SELECT jb FROM JobBoard jb " +
				"INNER JOIN jb.workers w " +
				"INNER JOIN w.pieceworks pw " +
				"WHERE pw.qualification = ?1", q);
	}
	
	
	public static int countByQualification(Qualification q){
		return countByQuery("SELECT COUNT(jb) FROM JobBoard jb " +
				"INNER JOIN jb.workers w " +
				"INNER JOIN w.pieceworks pw " +
				"WHERE pw.qualification = ?1", q);
	}
	
	@Id
	@GeneratedValue
	private long id;
	
	@Version
	private int version;

	private String name;
	
	@ManyToMany
	private List<Worker> workers = new ArrayList<Worker>();

	@Override
	public int getVersion() {
		return version;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Worker> getWorkers() {
		return workers;
	}

	public void addWorker(Worker w) {
		this.workers.add(w);
	}
	
	public void removeWorker(Worker w) {
		this.workers.remove(w);
	}
	
	public int getWorkersCount(){
		return this.workers.size();
	}

	@Override
	public Long getId() {
		return id;
	}
}
