package it.unisannio.catman.domain.workflow;

import it.unisannio.catman.domain.documents.Dossier;

public enum EventStatus implements Dossier.Status<EventStatus, Event> {
	NOT_PLANNED, PLANNED;
}
