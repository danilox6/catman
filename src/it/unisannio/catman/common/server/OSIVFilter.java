package it.unisannio.catman.common.server;

import it.unisannio.catman.Setup;

import java.io.IOException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
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

        EntityManager entityManager = factory.createEntityManager();
        
        entityManager.getTransaction().begin();
        chain.doFilter(request, response);
        try {
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new ServletException(e);
        } finally {
            entityManager.close();
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