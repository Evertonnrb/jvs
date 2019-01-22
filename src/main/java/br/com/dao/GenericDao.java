package br.com.dao;

import br.com.util.JPAUtil;
import org.hibernate.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;


public class GenericDao<T> {

    private EntityManager em = JPAUtil.EntityManagergetEmf();

    public void salvar(T entidade) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(entidade);
        transaction.commit();
        em.close();
    }

    public T merge(T entidade) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        T retorno = em.merge(entidade);
        transaction.commit();
        //em.close();
        return retorno;
    }

    public void remover(T entidade){
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.remove(entidade);
        transaction.commit();
        //em.close();
    }

    public void deletarPorId(T entidade){
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Object id = JPAUtil.getPrimaryKey(entidade);
        em.createQuery("delete from "+entidade.getClass().getCanonicalName()+" where id = "+id);
        transaction.commit();
       // em.close();
    }

    @SuppressWarnings("unchecked")
    public List<T> getLista(Class<T> entidade){
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        List<T> retorno =  em.createQuery("from "+entidade.getName()).getResultList();
        transaction.commit();
        return retorno;
    }

    public EntityManager getEntityManager() {
        return em;
    }


}
