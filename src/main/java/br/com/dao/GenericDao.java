package br.com.dao;

import br.com.util.JPAUtil;
import org.hibernate.Transaction;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

@Named
public class GenericDao<T> {

    @Inject
    private EntityManager em;
    @Inject
    private JPAUtil jpaUtil;

    public void salvar(T entidade) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(entidade);
        transaction.commit();
    }

    public T merge(T entidade) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        T retorno = em.merge(entidade);
        transaction.commit();
        return retorno;
    }

    public void remover(T entidade) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.remove(entidade);
        transaction.commit();
    }

    public void deletarPorId(T entidade) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Object id = jpaUtil.getPrimaryKey(entidade);
        em.createQuery("delete from " + entidade.getClass().getCanonicalName() + " where id = " + id);
        transaction.commit();
    }

    @SuppressWarnings("unchecked")
    public List<T> getLista(Class<T> entidade) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        List<T> retorno = em.createQuery("from " + entidade.getName()).getResultList();
        transaction.commit();
        return retorno;
    }

    public EntityManager getEntityManager() {
        return em;
    }


}
