package br.com.testes;

import br.com.model.Cidade;
import br.com.repository.IDaoUsuario;
import br.com.repository.IDaoUsuarioImpl;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

public class TestImplCidade {

    IDaoUsuario u = new IDaoUsuarioImpl();

    @Test
    public void buscarCidadePorCodigoEstado(){
        u.buscarCidadePorCodEstado(1L);
        assertNotNull(u);
    }
}
