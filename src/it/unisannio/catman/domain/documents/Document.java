package it.unisannio.catman.domain.documents;

import it.unisannio.catman.domain.documents.Dossier.Status;

public interface Document<S extends Enum<S> & Status<S,D>, D extends Dossier<S,D>> {
		
	public String getTitle();
	
	public boolean isComplete();
	
	public S getStatus(); 
	
	public D getDossier();
	
	public void setDossier(D dossier);
	 
}
