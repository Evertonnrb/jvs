package br.com.bean;

import br.com.dao.GenericDao;
import br.com.model.Lancamento;
import br.com.model.Usuario;

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

    public String salvar(){
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        Usuario usuario = (Usuario) externalContext.getSessionMap().get("usuarioLogado");
        lancamento.setUsuario(usuario);
        dao.salvar(lancamento);
        lancamento = new Lancamento();
        return "";
    }

    public String novo(){
        lancamento = new Lancamento();
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