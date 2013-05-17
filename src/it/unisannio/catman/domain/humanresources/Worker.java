package it.unisannio.catman.domain.humanresources;

import javax.persistence.Id;
import javax.persistence.Version;

import it.unisannio.catman.domain.contacts.Contact;

public class Worker extends Contact{
	@Id private long id;
	@Version private int version;
	
	private boolean candidated;
	private String resume;
	
	public Worker findWorker(long id) {
		return find(Worker.class, id);
	}

	@Override
	public int getVersion() {
		return version;
	}

	@Override
	public long getId() {
		return id;
	}

	public boolean isCandidated() {
		return candidated;
	}

	public void setCandidated(boolean candidated) {
		this.candidated = candidated;
	}
	
	public boolean isHired(){
		//TODO
		return false;
	}
	
	
}
