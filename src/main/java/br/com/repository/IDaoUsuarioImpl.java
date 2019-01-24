package br.com.repository;

import br.com.model.Cidade;
import br.com.model.Estado;
import br.com.model.Usuario;
import br.com.util.JPAUtil;

import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.List;

public class IDaoUsuarioImpl implements IDaoUsuario {

    @Override
    public Usuario consultarUsuario(String login, String senha) {
        Usuario usuario = null;
        EntityManager manager = JPAUtil.EntityManagergetEmf();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();

        usuario =(Usuario) manager.createQuery(
                "select u from usuario u where u.email = '"+login+"' and u.senha = '"+senha+"'"
        ).getSingleResult();

        transaction.commit();
        manager.close();
        return usuario;
    }

    @Override
    public List<SelectItem> estados() {
        List<SelectItem> items = new ArrayList<SelectItem>();
        EntityManager manager = JPAUtil.EntityManagergetEmf();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        List<Estado> estados = manager.createQuery("from Estado").getResultList();
        for (Estado estado : estados){
            items.add(new SelectItem(estado.getId(),estado.getNome()));
        }
        return items    ;
    }

    @Override
    public List<SelectItem> buscarCidadePorCodEstado(Long codEstado) {
        List<SelectItem> selectItemsCidades = new ArrayList<>();
        EntityManager em = JPAUtil.EntityManagergetEmf();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        List<Cidade> cidades = em.createQuery("from Cidade where estado.id = "+codEstado).getResultList();
        for (Cidade c : cidades){
            selectItemsCidades.add(new SelectItem(c.getId(),c.getNome()));

        }
        return selectItemsCidades;
    }
}
