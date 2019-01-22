package br.com.repository;

import br.com.model.Usuario;

public interface IDaoUsuario {
    Usuario consultarUsuario(String login,String senha);
}
