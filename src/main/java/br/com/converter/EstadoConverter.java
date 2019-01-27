package br.com.converter;

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

@FacesConverter(forClass = Estado.class,value ="estadoConverter")
public class EstadoConverter implements Converter, Serializable {

    @Inject
    private JPAUtil jpaUtil;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String codEstado) {
        EntityManager manager = jpaUtil.EntityManagergetEmf();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        Estado estado = (Estado) manager.find(Estado.class,Long.parseLong(codEstado));
        return estado;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object estado) {
        if(estado == null) return null;
        if(estado instanceof Estado) ((Estado) estado).getId().toString();
        return estado.toString();
    }
}
