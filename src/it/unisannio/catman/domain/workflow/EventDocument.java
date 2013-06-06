package it.unisannio.catman.domain.workflow;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

import it.unisannio.catman.common.server.AbstractEntity;
import it.unisannio.catman.domain.documents.Document;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class EventDocument extends AbstractEntity<Long> implements Document<EventStatus, Event> {
	
	@Id
	@GeneratedValue
	private long id;
	
	@Version
	private int version;
	
	@ManyToOne
	private Event event;
	
	private String title;

	@Override
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public Event getDossier() {
		return event;
	}

	@Override
	public void setDossier(Event event) {
		this.event = event;		
	}
	
	public Long getId() {
		return id;
	}

	public int getVersion() {
		return version;
	}
}
