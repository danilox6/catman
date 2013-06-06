package it.unisannio.catman.domain.documents;

import static it.unisannio.catman.domain.documents.Dossier.Status;

import java.util.SortedSet;

public interface Dossier<S extends Enum<S> & Status<S,D>, D extends Dossier<S,D>> extends Iterable<Document<S,D>>{
	public interface Status<S extends Enum<S> & Status<S,D>, D extends Dossier<S,D>> {
		S next();
		boolean isFinal();
		Class<? extends Document<S,D>> getDocumentType();
		Document<S,D> getDocument();
	}
	
	public void add(Document<S,D> d);
	public void remove(Document<S,D> d);
	public SortedSet<Document<S,D>> getAll();
	public S getStatus();
}
