package br.com.bean;

import br.com.dao.GenericDao;
import br.com.model.Lancamento;
import br.com.model.Usuario;
import br.com.repository.IDaoLancamento;
import br.com.repository.IDaoLancamentoImpl;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;

@ViewScoped
@ManagedBean(name = "lancamentoBean")
public class LancamentoBean {

    private Lancamento lancamento = new Lancamento();
    private GenericDao<Lancamento> dao = new GenericDao<Lancamento>();
    private List<Lancamento> lancamentos = new ArrayList<>();
    private IDaoLancamento iDaoLancamento = new IDaoLancamentoImpl();

    public String salvar(){
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        Usuario usuario = (Usuario) externalContext.getSessionMap().get("usuarioLogado");
        lancamento.setUsuario(usuario);
        dao.salvar(lancamento);
        lancamento = new Lancamento();
        carregarLancamentos();
        mMsg("Cadastro realizado");
        return "";
    }

    public String novo(){
        lancamento = new Lancamento();
        carregarLancamentos();
        return "";
    }

    public void mMsg(String msg){
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage message = new FacesMessage(msg);
        context.addMessage(null,message);
    }

    @PostConstruct
    public void carregarLancamentos(){
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        Usuario user = (Usuario) externalContext.getSessionMap().get("usuarioLogado");
        lancamentos = iDaoLancamento.consultarNotas(user.getId());
    }

    public String remover(){
        dao.remover(lancamento);
        lancamento = new Lancamento();
        carregarLancamentos();
        return "";
    }


    public Lancamento getLancamento() {
        return lancamento;
    }

    public void setLancamento(Lancamento lancamento) {
        this.lancamento = lancamento;
    }

    public GenericDao<Lancamento> getDao() {
        return dao;
    }

    public void setDao(GenericDao<Lancamento> dao) {
        this.dao = dao;
    }

    public List<Lancamento> getLancamentos() {
        return lancamentos;
    }

    public void setLancamentos(List<Lancamento> lancamentos) {
        this.lancamentos = lancamentos;
    }
}
