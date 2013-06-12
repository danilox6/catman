package it.unisannio.catman.domain.humanresources;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

import it.unisannio.catman.common.server.AbstractEntity;

@Entity
public class Qualification extends AbstractEntity<Long>{
	
	public static Qualification findQualification(Long id) {
		return find(Qualification.class, id);
	}
	
	public static List<Qualification> findAll() {
		return findAll(Qualification.class);
	}
	
	public static List<Qualification> findInWorkersSource(){
		return findByQuery("SELECT DISTINCT q FROM  JobBoard jb " +
							"RIGHT OUTER JOIN jb.workers w " +
							"INNER JOIN w.pieceworks pw "+
							"INNER JOIN pw.qualification q "+
							"WHERE w.candidate = ?1 " +
							"AND jb IS NULL",false);
	}
	
	public static List<Qualification> findInCandidates(){
		return findByQuery("SELECT DISTINCT q FROM Worker w " +
						"INNER JOIN w.pieceworks pw "+
						"INNER JOIN pw.qualification q "+
						"WHERE w.candidate = ?1",true);
	}
	
	public static List<Qualification> findByJobBoard(JobBoard jobBoard){
		return findByQuery("SELECT DISTINCT q FROM JobBoard jb " +
							"INNER JOIN jb.workers w " +
							"INNER JOIN w.pieceworks pw "+
							"INNER JOIN pw.qualification q "+
							"WHERE jb = ?1 ", jobBoard);
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Version 
	private int version;
	
	private String name;

	@Override
	public int getVersion() {
		return version;
	}

	@Override
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
