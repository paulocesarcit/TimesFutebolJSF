package br.edu.ifsul.controle;

import br.edu.ifsul.dao.PosicaoDAO;
import br.edu.ifsul.modelo.Posicao;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author paulo
 */
@Named(value = "controlePosicao")
@ViewScoped
public class ControlePosicao implements Serializable {

    @EJB
    private PosicaoDAO dao;
    private Posicao objeto;
    
    public ControlePosicao(){
        
    }
    
    public String listar(){
        return "/privado/posicao/listar?faces-redirect=true";
    }
    
    public void novo(){
        objeto = new Posicao();        
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

    public PosicaoDAO getDao() {
        return dao;
    }

    public void setDao(PosicaoDAO dao) {
        this.dao = dao;
    }

    public Posicao getObjeto() {
        return objeto;
    }

    public void setObjeto(Posicao objeto) {
        this.objeto = objeto;
    }
}
