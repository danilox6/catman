package it.unisannio.memento.fixtures;

import it.unisannio.memento.PersistedBy;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@PersistedBy(ExternalDAO.class)
public class ObjectWithDeclaredDAO {
	/**
	 * @uml.property  name="id"
	 */
	@SuppressWarnings("unused")
	@Id
	private int id;
}
