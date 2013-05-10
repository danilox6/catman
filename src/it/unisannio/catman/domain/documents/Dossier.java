package it.unisannio.catman.domain.documents;

import static it.unisannio.catman.domain.documents.Dossier.Status;

import java.util.SortedSet;

public interface Dossier<S extends Enum<S> & Status<S>> extends Iterable<Document>{
	public interface Status<S extends Enum<S> & Status<S>> {
		S next();
		boolean isFinal();
		Class<? extends Document> getDocumentType();
		Document getDocument();
	}
	
	public void add(Document d);
	public void remove(Document d);
	public SortedSet<Document> getAll();
	public S getStatus();
}
