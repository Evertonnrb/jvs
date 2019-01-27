package br.com.util;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
@ApplicationScoped
public class JPAUtil {
    private static EntityManagerFactory emf;

    public JPAUtil() {
        emf = Persistence.createEntityManagerFactory("default");
    }

    @Produces
    @RequestScoped
    public EntityManager EntityManagergetEmf() {
        return emf.createEntityManager();
    }

    @Produces
    @RequestScoped
    public void close() {
        emf.close();
    }

    @Produces
    @RequestScoped
    public Object getPrimaryKey(Object entity) {
        return emf.getPersistenceUnitUtil().getIdentifier(entity);
    }
}
