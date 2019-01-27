package br.com.bean;

import br.com.dao.GenericDao;
import br.com.model.Lancamento;
import br.com.model.Usuario;
import br.com.repository.IDaoLancamento;
import br.com.repository.IDaoLancamentoImpl;
import com.google.gson.Gson;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

@ViewScoped
@Named(value = "lancamentoBean")
public class LancamentoBean {

    private Lancamento lancamento = new Lancamento();

    private GenericDao<Lancamento> dao = new GenericDao<Lancamento>();
    private List<Lancamento> lancamentos = new ArrayList<>();
    @Inject
    private IDaoLancamento iDaoLancamento;

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

    public void consultarCep(AjaxBehaviorEvent event){
        try {
            URL url = new URL("https://viacep.com.br/ws/"+lancamento.getCep()+"/json");
            URLConnection connection = url.openConnection();
            InputStream inputStream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
            String cep = null;
            StringBuilder jsonCep = new StringBuilder();
            while ((cep = reader.readLine())!=null){
                jsonCep.append(cep);
            }
            Lancamento lancamentoGson = new Gson().fromJson(jsonCep.toString(),Lancamento.class);
            lancamento.setCep(lancamentoGson.getCep());
            lancamento.setLagradouro(lancamentoGson.getLagradouro());
            lancamento.setBairro(lancamentoGson.getBairro());
            lancamento.setComplemento(lancamentoGson.getComplemento());
            lancamento.setLocalidade(lancamentoGson.getLocalidade());
            lancamento.setUnidade(lancamentoGson.getUnidade());
            lancamento.setUf(lancamentoGson.getUf());
            lancamento.setGia(lancamentoGson.getGia());
            lancamento.setIbge(lancamentoGson.getIbge());
        }catch (Exception e){
            mMsg("Erro ao consultar cep "+e);
            e.printStackTrace();
        }
        System.out.printf("metodo executado "+lancamento.getCep());
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

