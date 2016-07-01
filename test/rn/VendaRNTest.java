package rn;

import java.text.ParseException;
import model.Filme;
import model.Sala;
import model.Sessao;
import model.Venda;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import util.DateUtil;

/**
 *Classe que teste as regras de negocio da classe VendaRN
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class VendaRNTest {
    private VendaRN lista;
    private Filme filme;
    private Sala sala;
    private Sessao sessao;
    
    /**
     * construtor que inicializa os objetos
     */
    
    public VendaRNTest() {
        lista = new VendaRN();
        filme = new Filme(100, "filmeTeste", "Genero Teste", "sinopse Teste");
        sala = new Sala (666, 100);
        try {
            sessao =  new Sessao(30, sala, filme, DateUtil.stringToHour("03:00"));
        } catch (ParseException ex) {
            System.out.println("");
        }
    }
    
   
    /**
     * Teste do método adicionar, da classe VendaRN.
     * @throws java.lang.Exception
     */
    @Test
    public void testAAdicionar() throws Exception {
        System.out.println("adicionar");
        Venda v = new Venda(sessao,  DateUtil.stringToDate("05/03/2016"));
        
        lista.adicionar(v);
        assertTrue(!lista.listar().contains(v));
    }

    /**
     * Teste do método listar, da classe VendaRN.
     */
    @Test
    public void testBListar() {
        System.out.println("listar");
        int expResult = 0;
        int result = lista.listar().size();
        assertNotEquals(expResult, result);
        
    }

    /**
     * Teste do método procurarPorId, da classe VendaRN.
     * @throws java.lang.Exception
     */
    @Test
    public void testCProcurarPorId() throws Exception {
        System.out.println("procurarPorId");
        int tamanhoLista = lista.listar().size();
        int ultimoRegistro = lista.listar().get(tamanhoLista-1).getRegistroVenda();
        Venda result = lista.procurarPorId(ultimoRegistro);
        assertNotNull(result);
        
    }
    
}
