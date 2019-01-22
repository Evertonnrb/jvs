package br.com.testes;

import br.com.dao.GenericDao;
import br.com.model.Usuario;
import org.junit.Test;

public class TestePersistenciaDomain extends GenericDao<Usuario> {

    Usuario usuario = new Usuario("Mr bean","burns",
            "mr-bean@gmail.com","33",false,23,new String[]{"Spring"},
            "adm","masculino");
    @Test
    public void deveSalvarUsuario(){
        salvar(usuario);
    }

    @Test
    public void deveMergeUsuario(){
        merge(usuario);
    }

}
