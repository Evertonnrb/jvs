package br.com.repository;

import br.com.model.Usuario;
import br.com.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class IDaoImpl implements IDaoUsuario {

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
}
