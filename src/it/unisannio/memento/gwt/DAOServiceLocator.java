package it.unisannio.memento.gwt;

import it.unisannio.memento.DAOFactory;

import com.google.web.bindery.requestfactory.shared.ServiceLocator;

public class DAOServiceLocator implements ServiceLocator {
	private final DAOFactory factory;
	
	protected DAOServiceLocator(DAOFactory f) {
		this.factory = f;
	}
	
	@Override
	public Object getInstance(Class<?> clazz) {
		return factory.getDaoFor(clazz);
	}

}
