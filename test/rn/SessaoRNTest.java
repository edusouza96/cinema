package rn;

import java.util.Date;
import java.util.List;
import model.Filme;
import model.Sala;
import model.Sessao;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import util.DateUtil;

/**
 *Classe que teste as regras de negocio da classe SessaoRN
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SessaoRNTest {
    private SessaoRN lista;
    private Filme filme;
    private Sala sala;
    
    /**
     * Construtor que teste as regras de negocio da classe SessaoRN
     */
    public SessaoRNTest() {
        lista = new SessaoRN();
        filme = new Filme(100, "filmeTeste", "Genero Teste", "sinopse Teste");
        sala = new Sala (666, 100);
    }
    

    

    /**
     * Testa do método adicionar, da classe SessaoRN.
     * @throws java.lang.Exception
     */
    @Test
    public void testAAdicionar() throws Exception {
        System.out.println("adicionar");
        Sessao s = new Sessao(filme, sala, DateUtil.stringToHour("15:00"));
        lista.adicionar(s);
        assertTrue(!lista.listar().contains(s));
    }

    /**
     * Teste do método listar, da classe SessaoRN.
     */
    @Test
    public void testBListar() {
        System.out.println("listar");
        int expResult = 0;
        int result = lista.listar().size();
        assertNotEquals(expResult, result);
        
    }
    
     /**
     * Teste do metodo procurarPorId, da classe  SessaoRN.
     * @throws java.lang.Exception
     */
    @Test
    public void testCProcurarPorId() throws Exception {
        System.out.println("procurarPorId");
        int tamanhoLista = lista.listar().size();
                System.out.println(tamanhoLista);

        int ultimoRegistro = lista.listar().get(tamanhoLista-1).getCodigoSessao();
        Sessao result = lista.procurarPorId(ultimoRegistro);
       assertNotNull(result);
       
      
    }

    /**
     * Teste do método procuraPorHorario, da classe SessaoRN.
     * @throws java.lang.Exception
     */
    @Test
    public void testDProcuraPorHorario() throws Exception {
        System.out.println("procuraPorHorario");
        Date horario = DateUtil.stringToHour("15:10");
        List<Sessao> expResult = null;
        List<Sessao> result = lista.procuraPorHorario(horario);
        assertNotEquals(expResult, result);
    }
    
    /**
     *Teste do metodo atualizar, da classe SessaoRN.
     * @throws java.lang.Exception
     */
    @Test
    public void testEAtualizar() throws Exception {
        System.out.println("atualizar");
        int tamanhoLista = lista.listar().size();
        int ultimoRegistro = lista.listar().get(tamanhoLista-1).getCodigoSessao();
        Sessao sessao = new Sessao(ultimoRegistro, sala, filme, DateUtil.stringToHour("15:10"));
        lista.atualizar(sessao);
        assertTrue(!lista.listar().contains(sessao));
    }

    /**
     * Teste do metodo deletar, da classe SessaoRN.
     * @throws java.lang.Exception
     */
    @Test
    public void testFDeletar() throws Exception {
        System.out.println("deletar");
        int tamanhoLista = lista.listar().size();
        int ultimoRegistro = lista.listar().get(tamanhoLista-1).getCodigoSessao();
        Sessao sessao = new Sessao(ultimoRegistro, sala, filme, DateUtil.stringToHour("15:00"));
        lista.deletar(sessao);
        int _tamanhoLista = lista.listar().size();
        assertTrue(_tamanhoLista != tamanhoLista);
    }
    
}
