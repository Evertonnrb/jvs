package br.com.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
    private static EntityManagerFactory emf;

    static {
        emf = Persistence.createEntityManagerFactory("default");
    }

    public static EntityManager EntityManagergetEmf() {
        return emf.createEntityManager();
    }

    public static void close() {
        emf.close();
    }

    public static Object getPrimaryKey(Object entity) {
        return emf.getPersistenceUnitUtil().getIdentifier(entity);
    }
}
