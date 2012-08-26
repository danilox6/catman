package it.unisannio.memento.gwt;

import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;

import it.unisannio.memento.jpa.GenericDAO;

public class GenericLocatorDAO<K, E> extends GenericDAO<K, E> implements
		LocatorDAO<K, E> {

	public GenericLocatorDAO(EntityManager em, Class<E> entityClass) {
		super(em, entityClass);
	}

	@Override
	public E create(Class<? extends E> entityClass) {
		try {
			return entityClass.newInstance();
		} catch (Exception e) {
			throw new RuntimeException("Error instantiating " + entityClass.getName(), e);
		} 
	}

	@SuppressWarnings("unchecked")
	@Override
	public K getId(E entity) {
		EntityType<?> entityType = getEntityManager().getMetamodel().entity(entity.getClass());
		Member member = null;
		try {
			member = entityType.getId(entityType.getIdType().getJavaType()).getJavaMember();
			try {
				if(member instanceof Method) {
					return (K) ((Method) member).invoke(entity);
				} else {
					return (K) ((Field) member).get(entity);
				}
			} catch (Exception e) {
				throw new RuntimeException("Exception retrieving Id field " + member + " on object " + entity, e);
			}
		} catch (Exception e) {
			throw new RuntimeException("Entity " + entity + " doesn't seem to have an id field", e);
		}
	}

	@Override
	public E find(Class<? extends E> entityClass, K id) {
		return find(id);
	}

	@Override
	public Object getVersion(E entity) {
		EntityType<?> entityType = getEntityManager().getMetamodel().entity(entity.getClass());
		Member member = null;
		try {
			member = entityType.getVersion(Object.class).getJavaMember();
			try {
				if(member instanceof Method) {
					return ((Method) member).invoke(entity);
				} else {
					return ((Field) member).get(entity);
				}
			} catch (Exception e) {
				throw new RuntimeException("Exception retrieving version field " + member + " on object " + entity, e);
			}
		} catch (Exception e) {
			throw new RuntimeException("Entity " + entity + " doesn't seem to have a version field", e);
		}
	}
	

}
