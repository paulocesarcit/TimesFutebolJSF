package br.edu.ifsul.controle;

import br.edu.ifsul.dao.CidadeDAO;
import br.edu.ifsul.dao.JogadorDAO;
import br.edu.ifsul.dao.TimeDAO;
import br.edu.ifsul.modelo.Jogador;
import br.edu.ifsul.modelo.Time;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author paulo
 */
@Named(value = "controleTime")
@ViewScoped
public class ControleTime implements Serializable {
    
    @EJB
    private TimeDAO dao;
    private Time objeto;
    
    @EJB
    private CidadeDAO daoCidade;
    
    @EJB
    private JogadorDAO daoJogador;
    private Jogador jogador;
    private Boolean novoJogador;
    
    public ControleTime(){
        
    }
    
    public String listar(){
        return "/privado/time/listar?faces-redirect=true";
    }
    
    public void novo(){
        objeto = new Time();        
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
    
    public void novoJogador(){
        jogador = new Jogador();
        novoJogador = true;
    }
    
    public void alterarJogador(int index){
        jogador = objeto.getJogadores().get(index);
        novoJogador = false;
    }
    
    public void salvarJogador(){
        if (novoJogador){
            objeto.adicionarJogador(jogador);
        }
        Util.mensagemInformacao("Jogador adicionado com sucesso!");
    }
    
    public void removerJogador(int index){
        objeto.removerJogador(index);
        Util.mensagemInformacao("Jogador removido com sucesso!");
    }

    public TimeDAO getDao() {
        return dao;
    }

    public void setDao(TimeDAO dao) {
        this.dao = dao;
    }

    public Time getObjeto() {
        return objeto;
    }

    public void setObjeto(Time objeto) {
        this.objeto = objeto;
    }

    public CidadeDAO getDaoCidade() {
        return daoCidade;
    }

    public void setDaoCidade(CidadeDAO daoCidade) {
        this.daoCidade = daoCidade;
    }
    
    public JogadorDAO getDaoJogador() {
        return daoJogador;
    }

    public void setDaoJogador(JogadorDAO daoJogador) {
        this.daoJogador = daoJogador;
    }

    public Jogador getJogador() {
        return jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    public Boolean getNovoJogador() {
        return novoJogador;
    }

    public void setNovoJogador(Boolean novoJogador) {
        this.novoJogador = novoJogador;
    }
    
    public void carregaNomeTime() {
        if(objeto.getNome() != null)
            objeto.setNome(objeto.getNome());
    }
}
