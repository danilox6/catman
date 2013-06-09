package it.unisannio.catman.domain.workflow;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
	
	public static EventDocument findEventDocument(Long id) {
		return find(EventDocument.class, id);
	}
	
	public static List<EventDocument> listByEvent(Event evt, int start, int length) {
		return listByQuery(EventDocument.class, start, length, "SELECT ed FROM EventDocument ed WHERE ed.dossier = ?1", evt);
	}
	
	public static int countByEvent(Event evt) {
		return countByQuery("SELECT COUNT(ed) FROM EventDocument ed WHERE ed.dossier = ?1", evt);
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private long id;
	
	@Version
	private int version;
	
	@ManyToOne
	private Event dossier;
	
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
		return dossier;
	}

	@Override
	public void setDossier(Event event) {
		this.dossier = event;		
	}
	
	public Long getId() {
		return id;
	}

	public int getVersion() {
		return version;
	}
}
