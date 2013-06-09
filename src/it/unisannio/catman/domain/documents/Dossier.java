package it.unisannio.catman.domain.documents;

import static it.unisannio.catman.domain.documents.Dossier.Status;

import java.util.List;
public interface Dossier<S extends Enum<S> & Status<S,D>, D extends Dossier<S,D>> {
	public interface Status<S extends Enum<S> & Status<S,D>, D extends Dossier<S,D>> {
	}
	
	public void addDocument(Document<S,D> d);
	public void removeDocument(Document<S,D> d);
	public List<? extends Document<S,D>> getDocuments();
	public S getStatus();
}
