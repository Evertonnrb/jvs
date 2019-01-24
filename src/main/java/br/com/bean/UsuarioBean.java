package br.com.bean;

import br.com.dao.GenericDao;
import br.com.model.Cidade;
import br.com.model.Estado;
import br.com.model.Usuario;
import br.com.repository.IDaoUsuarioImpl;
import br.com.repository.IDaoUsuario;
import br.com.util.JPAUtil;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@ViewScoped
@ManagedBean(name = "usuarioBean")
public class UsuarioBean {

    private Usuario usuario = new Usuario();
    private GenericDao<Usuario> dao = new GenericDao<Usuario>();
    private List<Usuario> usuarios = new ArrayList<Usuario>();
    private IDaoUsuario iDaoUsuario = new IDaoUsuarioImpl();
    private List<SelectItem> listaEstados;
    private List<SelectItem> cidades;

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

        usuario = iDaoUsuario.consultarUsuario(usuario.getEmail(), usuario.getSenha());
        if (usuario != null) {
            FacesContext context = FacesContext.getCurrentInstance();
            ExternalContext externalContext = context.getExternalContext();
            externalContext.getSessionMap().put("usuarioLogado", usuario);
            return "cad/usuarios.xhtml";
        }
        return "index.xhtml";
    }

    public String logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        externalContext.getSessionMap().remove("usuarioLogado");
        HttpServletRequest httpServletRequest = (HttpServletRequest)
                context.getExternalContext().getRequest();
        httpServletRequest.getSession().invalidate();
        return "index.xhtml";
    }


    public boolean permiteAcesso(String acesso) {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        Usuario user = (Usuario) externalContext.getSessionMap().get("usuarioLogado");
        return user.getPerfil().equals(acesso);
    }

    public void carregaCidades(AjaxBehaviorEvent event) {
        String codEstado = (String) event.getComponent().getAttributes().get("submittedValue");
        if (codEstado != null) {
            Estado estado = JPAUtil.EntityManagergetEmf().find(Estado.class, Long.parseLong(codEstado));
            if(estado!=null){
                usuario.setEstado(estado);
                List<Cidade> cidades = JPAUtil.EntityManagergetEmf()
                                                .createQuery("from Cidade where estado.id = "+codEstado)
                                                .getResultList();
                List<SelectItem> selectItemsCidades = new ArrayList<SelectItem>();
                for (Cidade c : cidades){
                    selectItemsCidades.add(new SelectItem(c.getId(),c.getNome()));
                }
                /*List<SelectItem> items = new ArrayList<>();
                items = iDaoUsuario.buscarCidadePorCodEstado(estado.getId());
                setCidades(items);*/
                setCidades(selectItemsCidades);
            }
        }
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


    public IDaoUsuario getiDaoUsuario() {
        return iDaoUsuario;
    }

    public void setiDaoUsuario(IDaoUsuario iDaoUsuario) {
        this.iDaoUsuario = iDaoUsuario;
    }

    public List<SelectItem> getListaEstados() {
        listaEstados = iDaoUsuario.estados();
        return listaEstados;
    }

    public void setListaEstados(List<SelectItem> listaEstados) {
        this.listaEstados = listaEstados;
    }

    public List<SelectItem> getCidades() {
        return cidades;
    }

    public void setCidades(List<SelectItem> cidades) {
        this.cidades = cidades;
    }
}



