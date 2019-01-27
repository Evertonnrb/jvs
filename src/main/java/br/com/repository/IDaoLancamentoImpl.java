package br.com.repository;

import br.com.model.Lancamento;
import br.com.util.JPAUtil;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class IDaoLancamentoImpl implements IDaoLancamento {

    @Inject
    private JPAUtil jpaUtil;



    @SuppressWarnings("unchecked")
    @Override
    public List<Lancamento> consultarNotas(Long codUser) {
        List<Lancamento> listNotas = null;
        EntityManager em = jpaUtil.EntityManagergetEmf();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        listNotas = em.createQuery("from lancamento where usuario.id = " + codUser).getResultList();
        transaction.commit();
        return listNotas;
    }
}
