package br.com.repository;

import br.com.model.Cidade;
import br.com.model.Usuario;

import javax.faces.model.SelectItem;
import java.util.List;

public interface IDaoUsuario {
    Usuario consultarUsuario(String login,String senha);
    List<SelectItem> estados();
    List<SelectItem> buscarCidadePorCodEstado(Long codEstado);
}
