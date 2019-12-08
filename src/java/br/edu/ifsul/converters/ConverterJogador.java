package br.edu.ifsul.converters;

import br.edu.ifsul.modelo.Jogador;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author paulo
 */
@Named(value = "converterJogador")
@RequestScoped
public class ConverterJogador implements Serializable, Converter {
    
    @PersistenceContext(unitName = "TimesFutebolViewPU")
    private EntityManager em;

    // converte da tela para o objeto
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string == null || string.equals("Selecione um registro")){
            return null;
        }
        return em.find(Jogador.class, Integer.parseInt(string));
    }

    // converte do objeto para a tela
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object t) {
        if (t == null){
            return null;
        }
        Jogador obj = (Jogador) t;
        return obj.getId().toString();
    }

}
