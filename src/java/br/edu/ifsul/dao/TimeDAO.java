package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Time;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author paulo
 */
@Stateful
public class TimeDAO extends DAOGenerico<Time> implements Serializable {
    
    public TimeDAO(){
        super();
        classePersistente = Time.class;
        // adicionar as ordenações possiveis
        listaOrdem.add(new Ordem("id", "ID", "="));
        listaOrdem.add(new Ordem("nome", "Nome", "like"));
        listaOrdem.add(new Ordem("fundacao", "Fundacao", "like"));
        listaOrdem.add(new Ordem("cidade.nome", "Cidade", "like"));
        // definir a ordem padrão
        ordemAtual = listaOrdem.get(1);
        // inicalizar o conversor com a lista de ordens
        converterOrdem = new ConverterOrdem(listaOrdem);               
    }
    
    @Override
    public Time getObjectById(Object id){
        Time obj = em.find(Time.class, id);
        obj.getJogadores().size();
        return obj;
    }

}
