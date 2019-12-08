package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Posicao;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author paulo
 */
@Stateful
public class PosicaoDAO extends DAOGenerico<Posicao> implements Serializable {
    
    public PosicaoDAO(){
        super();
        classePersistente = Posicao.class;
        // adicionar as ordenações possiveis
        listaOrdem.add(new Ordem("id", "ID", "="));
        listaOrdem.add(new Ordem("nome", "Nome", "like"));
        // definir a ordem padrão
        ordemAtual = listaOrdem.get(1);
        // inicalizar o conversor com a lista de ordens
        converterOrdem = new ConverterOrdem(listaOrdem);               
    }

}
