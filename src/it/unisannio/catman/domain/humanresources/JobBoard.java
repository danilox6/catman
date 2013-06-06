package it.unisannio.catman.domain.humanresources;

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
	
	@Id
	@GeneratedValue
	private long id;
	
	@Version
	private int version;

	private String name;
	
	@ManyToMany
	private List<Worker> workers;

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

	@Override
	public Long getId() {
		return id;
	}
}
