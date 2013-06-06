package it.unisannio.catman.domain.workflow;

import it.unisannio.catman.domain.documents.Document;
import it.unisannio.catman.domain.documents.Dossier;

public enum EventStatus implements Dossier.Status<EventStatus, Event> {
	A, B, C;
	static {
		A.setNext(B);
	}

	EventStatus() {}
	
	private EventStatus next;
	
	private void setNext(EventStatus es) {
		this.next = es;
	}
	
	@Override
	public EventStatus next() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isFinal() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Class<? extends Document<EventStatus, Event>> getDocumentType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Document<EventStatus, Event> getDocument() {
		// TODO Auto-generated method stub
		return null;
	}
}
