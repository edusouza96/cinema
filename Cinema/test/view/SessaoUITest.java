package view;

import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Filme;
import model.Sala;
import model.Sessao;
import org.junit.Test;
import static org.junit.Assert.*;
import repositorio.RepositorioSessao;
import util.DateUtil;

/**
 *@see Testa a Classe SessaoUI 
 */
public class SessaoUITest {
    private Filme filme;
    private Sala sala;
    private Date hora;
    private Sessao sessao;
    private RepositorioSessao lista;
    
    public SessaoUITest() {
        try {
            filme = new Filme("The Avengers","Ficção/Ação","Heróis da marvel unidos para lutar contra loki");
            sala = new Sala(100, 300);
            this.hora = DateUtil.stringToHour("18:30");
            sessao = new Sessao(filme, sala, hora);
            lista = new RepositorioSessao();
        } catch (ParseException ex) {
            System.err.println("Erro na hora, formato invalido");
        }
    }
    
   
    /**
     * testa o cadastro de sessões
     */
    @Test
    public void testCadastrarSessao() {
        lista.adicionar(sessao);
        assertTrue(lista.getListaSessao().contains(sessao));
    }
    
    /**
     * Testa a edição de sessao
     */
    @Test
    public void testAlterarSessao(){
        lista.adicionar(sessao);
        Filme filme = sessao.getFilme();
        Sala sala = sessao.getSala();
        Date hora = sessao.getHorario();
        sessao = lista.consultarPorCodigo(1);
        
        try {
            Date hora1 = DateUtil.stringToHour("20:30");
            sessao.setHorario(hora1);
            sessao.setFilme(new Filme("Gente Grande", "Comedia", "Adultos que gostam de reviver a infancia"));
            sessao.setSala(new Sala(500, 1000));
            
            assertFalse(sessao.getFilme().equals(filme)); 
            assertFalse(sessao.getSala().equals(sala)); 
            assertFalse(sessao.getHorario().equals(hora)); 
            
        } catch (ParseException ex) {
            System.err.println("Erro na hora, formato invalido");
        }
    }
    
    /**
     * Testa a exclusão de sessao
     */
   @Test
    public void testDeletarSesssao() {
        lista.adicionar(sessao);
        System.out.println(sessao.getCodigoSessao());
        sessao = lista.consultarPorCodigo(3);
        lista.remover(sessao);
        assertEquals(0, lista.getListaSessao().size());
        
    }
}
