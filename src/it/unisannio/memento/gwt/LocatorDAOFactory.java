package it.unisannio.memento.gwt;

import javax.persistence.EntityManager;

import it.unisannio.memento.AbstractDAO;
import it.unisannio.memento.jpa.DAOFactoryImpl;

public class LocatorDAOFactory extends DAOFactoryImpl {

	public LocatorDAOFactory(EntityManager em) {
		super(em);
	}

	@Override
	protected <K, E> AbstractDAO<K, E> createGenericImpl(EntityManager em, Class<E> entityClass) {
		return new GenericLocatorDAO<K, E>(em, entityClass);
	}
}
