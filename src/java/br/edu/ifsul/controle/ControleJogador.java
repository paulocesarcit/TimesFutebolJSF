package br.edu.ifsul.controle;

import br.edu.ifsul.dao.JogadorDAO;
import br.edu.ifsul.dao.PosicaoDAO;
import br.edu.ifsul.dao.TimeDAO;
import br.edu.ifsul.modelo.Jogador;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author paulo
 */
@Named(value = "controleJogador")
@ViewScoped
public class ControleJogador implements Serializable {

    @EJB
    private JogadorDAO dao;
    private Jogador objeto;
    
    @EJB
    private PosicaoDAO daoPosicao;
    
    @EJB
    private TimeDAO daoTime;
    
    public ControleJogador(){
        
    }
    
    public String listar(){
        return "/privado/jogador/listar?faces-redirect=true";
    }
    
    public void novo(){
        objeto = new Jogador();        
    }
    
    public void alterar(Object id){
        try {
            objeto = dao.getObjectById(id);
        } catch (Exception e){
            Util.mensagemErro("Erro ao recuperar objeto: " + Util.getMensagemErro(e));
        }
    }
    
    public void remover(Object id){
        try {
            objeto = dao.getObjectById(id);
            dao.remove(objeto);
            Util.mensagemInformacao("Objeto removido com sucesso!");
        } catch (Exception e){
            Util.mensagemErro("Erro ao remover objeto: " + Util.getMensagemErro(e));
        }
    }
    
    public void salvar(){
        try {
            if (objeto.getId() == null){
                dao.persist(objeto);
            } else {
                dao.merge(objeto);
            }
            Util.mensagemInformacao("Objeto persistido com sucesso!");
        } catch (Exception e){
            Util.mensagemErro("Erro ao persistir objeto: " + Util.getMensagemErro(e));
        }
    }

    public JogadorDAO getDao() {
        return dao;
    }

    public void setDao(JogadorDAO dao) {
        this.dao = dao;
    }

    public Jogador getObjeto() {
        return objeto;
    }

    public void setObjeto(Jogador objeto) {
        this.objeto = objeto;
    }

    public PosicaoDAO getDaoPosicao() {
        return daoPosicao;
    }

    public void setDaoPosicao(PosicaoDAO daoPosicao) {
        this.daoPosicao = daoPosicao;
    }

    public TimeDAO getDaoTime() {
        return daoTime;
    }

    public void setDaoTime(TimeDAO daoTime) {
        this.daoTime = daoTime;
    }
    
}
