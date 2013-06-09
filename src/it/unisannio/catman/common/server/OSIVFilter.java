package it.unisannio.catman.common.server;

import it.unisannio.catman.Setup;

import java.io.IOException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class OSIVFilter implements Filter {

    private EntityManagerFactory factory;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        EntityManager em = factory.createEntityManager();
        ThreadLocalEntityManager.set(em);
        
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            chain.doFilter(request, response);
            tx.commit();
        } catch (Exception e) {
        	if (tx != null && tx.isActive())
        		tx.rollback();
            throw new RuntimeException(e);
        } finally {
            em.close();
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        factory = Persistence.createEntityManagerFactory(Setup.DATABASE_PERSISTENCE_UNIT);

    }

    @Override
    public void destroy() {
        factory.close();
    }

}