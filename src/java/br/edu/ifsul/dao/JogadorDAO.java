package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Jogador;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author paulo
 */
@Stateful
public class JogadorDAO extends DAOGenerico<Jogador> implements Serializable {
    
    public JogadorDAO(){
        super();
        classePersistente = Jogador.class;
        // adicionar as ordenações possiveis
        listaOrdem.add(new Ordem("id", "ID", "="));
        listaOrdem.add(new Ordem("nome", "Nome", "like"));
        listaOrdem.add(new Ordem("posicao.nome", "Posicao", "like"));
        listaOrdem.add(new Ordem("time.nome", "Time", "like"));
        // definir a ordem padrão
        ordemAtual = listaOrdem.get(1);
        // inicalizar o conversor com a lista de ordens
        converterOrdem = new ConverterOrdem(listaOrdem);               
    }

}
