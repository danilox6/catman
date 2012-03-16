package it.unisannio.memento.jpa;

import javax.persistence.EntityManager;

import it.unisannio.memento.GenericDAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class GenericDAOImpl<K, T> implements GenericDAO<K, T> {
		private final Class<T> objectClass;
		private final EntityManager entityManager;
		
		public GenericDAOImpl(EntityManager em, Class<T> objClass) {
			this.objectClass = objClass;
			this.entityManager = em;
		}

		

		@Override
		public void save(T... objects) {
			 
		}



		@Override
		public T find(K key) {
			return entityManager.find(objectClass, key);
		}

		public T findByJPQL(String query, Object... args) {
			return null;
		}


		@Override
		public T[] findAll(K... keys) {
			// TODO Auto-generated method stub
			return null;
		}

		public T[] findAllByJPQL(String query, Object... args) {
			return null;
		}


		@Override
		public int count() {
			// TODO Auto-generated method stub
			return 0;
		}



		@Override
		public void delete(K... keys) {
			// TODO Auto-generated method stub
			
		}

		public T[] listByJPQL(int offset, int size, String hql, Object... args) {
			return null;
			
		}

		@Override
		public T[] list(int offset, int size) {
			// TODO Auto-generated method stub
			return null;
		}
		
		public void execJPQL(String hql, Object... args) {
			
		}
	}