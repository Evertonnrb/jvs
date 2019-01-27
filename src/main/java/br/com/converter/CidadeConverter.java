package br.com.converter;

import br.com.model.Cidade;
import br.com.model.Estado;
import br.com.util.JPAUtil;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.io.Serializable;

@FacesConverter(forClass = Cidade.class,value = "cidadeConverter")
public class CidadeConverter implements Converter, Serializable {

    @Inject
    private JPAUtil jpaUtil;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String codCidade) {
        EntityManager manager = jpaUtil.EntityManagergetEmf();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        Cidade cidade = (Cidade) manager.find(Cidade.class,Long.parseLong(codCidade));
        return cidade;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object cidade) {

        if(cidade == null) return null;
        if(cidade instanceof Cidade) return ((Cidade) cidade).getId().toString();
        else return cidade.toString();
    }
}
