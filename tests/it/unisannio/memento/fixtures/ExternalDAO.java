package it.unisannio.memento.fixtures;

import it.unisannio.memento.GenericDAO;
import it.unisannio.memento.Persists;

@Persists(ObjectWithDeclaredDAO.class)
public interface ExternalDAO extends GenericDAO<Integer, ObjectWithDeclaredDAO> {

}
