package rn;

import model.Sala;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

/**
 *
 * Classe que teste as regras de negocio da classe SalaRN
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SalaRNTest {
    private SalaRN lista;
    public SalaRNTest() {
        lista = new SalaRN();
    }
    
   
    /**
     * Teste do método adicionar, da classe SalaRN.
     * @throws java.lang.Exception
     */
    @Test
    public void testAAdicionar() throws Exception {
        System.out.println("adicionar");
        Sala s = new Sala(10, 1000);
        lista.adicionar(s);
        assertNotNull(lista.procurarPorNumero(s.getNumeroSala()));
    }

    /**
     * Teste do método listar, da classe SalaRN.
     */
    @Test
    public void testBListar() {
        System.out.println("listar");
        int expResult = 0;
        int result = lista.listar().size();
        
        assertTrue(expResult<result);
        
    }

    /**
     * Test of procurarPorNumero method, of class SalaRN.
     * @throws java.lang.Exception
     */
    @Test
    public void testCProcurarPorNumero() throws Exception {
        System.out.println("procurarPorNumero");
        int tamanhoLista = lista.listar().size();
        int ultimoRegistro = lista.listar().get(tamanhoLista-1).getNumeroSala();
        
        Sala result = lista.procurarPorNumero(ultimoRegistro);
        assertNotNull(result);
    }

    /**
     * Teste do método atualizar, da classe SalaRN.
     * @throws java.lang.Exception
     */
    @Test
    public void testDAtualizar() throws Exception {
        System.out.println("atualizar");
        Sala sala = new Sala(666, 1000);
        lista.atualizar(sala);
        assertTrue(!lista.listar().contains(sala));
    }

    
    /**
     * Teste do método deletar, da classe SalaRN.
     * @throws java.lang.Exception
     */
    @Test
    public void testEDeletar() throws Exception {
        System.out.println("deletar");
        int tamanhoLista = lista.listar().size();
        Sala sala = new Sala(6661, 1000);
        lista.deletar(sala);
        int _tamanhoLista = lista.listar().size();
        assertTrue(_tamanhoLista != tamanhoLista);
        
    }
}
