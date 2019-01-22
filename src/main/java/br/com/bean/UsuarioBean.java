package br.com.bean;

import br.com.dao.GenericDao;
import br.com.model.Usuario;
import br.com.repository.IDaoImpl;
import br.com.repository.IDaoUsuario;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;

@ViewScoped
@ManagedBean(name = "usuarioBean")
public class UsuarioBean {

    private Usuario usuario = new Usuario();
    private GenericDao<Usuario> dao = new GenericDao<Usuario>();
    private List<Usuario> usuarios = new ArrayList<Usuario>();
    private IDaoUsuario iDaoUsuario = new IDaoImpl();

    public String novoUsuario() {
        dao.salvar(usuario);
        usuario = new Usuario();
        carregarUsuarios();
        return "";
    }

    public String novo() {
        usuario = new Usuario();
        return "";
    }

    public String merge() {
        usuario = dao.merge(usuario);
        carregarUsuarios();
        return "";
    }

    public String removeBean() {
        dao.remover(usuario);
        usuario = new Usuario();
        carregarUsuarios();
        return "";
    }

    public String removerBeanPorId() {
        dao.deletarPorId(usuario);
        usuario = new Usuario();
        carregarUsuarios();
        return "";
    }

    ///home/everton/Development/Java/DevDoj/posjava/web/cad/usuarios.xhtml
    public String logar() {

        usuario= iDaoUsuario.consultarUsuario(usuario.getEmail(), usuario.getSenha());
        if (usuario != null) {
            FacesContext context = FacesContext.getCurrentInstance();
            ExternalContext externalContext = context.getExternalContext();
            externalContext.getSessionMap().put("usuarioLogado", usuario);
            return "cad/usuarios.xhtml";
        }
        return "index.xhtml";
    }

    public boolean permiteAcesso(String acesso){
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        Usuario user = (Usuario) externalContext.getSessionMap().get("usuarioLogado");
        return user.getPerfil().equals(acesso);
    }

    @PostConstruct
    public void carregarUsuarios() {
        usuarios = dao.getLista(Usuario.class);
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public GenericDao<Usuario> getDao() {
        return dao;
    }

    public void setDao(GenericDao<Usuario> dao) {
        this.dao = dao;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}
