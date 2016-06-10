package rn;

import model.Sala;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * Classe que teste as regras de negocio da classe SalaRN
 */
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
    public void testAdicionar() throws Exception {
        System.out.println("adicionar");
        Sala s = new Sala(666, 1000);
        lista.adicionar(s);
        assertNotNull(lista.procurarPorNumero(s.getNumeroSala()));
    }

    /**
     * Teste do método listar, da classe SalaRN.
     */
    @Test
    public void testListar() {
        System.out.println("listar");
        int expResult = 0;
        int result = lista.listar().size();
        
        assertTrue(expResult<result);
        
    }

    /**
     * Teste do método deletar, da classe SalaRN.
     * @throws java.lang.Exception
     */
    @Test
    public void testDeletar() throws Exception {
        System.out.println("deletar");
        int tamanhoLista = lista.listar().size();
        Sala sala = new Sala(666, 1000);
        lista.deletar(sala);
        int _tamanhoLista = lista.listar().size();
        assertTrue(_tamanhoLista != tamanhoLista);
        
    }

    /**
     * Teste do método atualizar, da classe SalaRN.
     * @throws java.lang.Exception
     */
    @Test
    public void testAtualizar() throws Exception {
        System.out.println("atualizar");
        Sala sala = new Sala(666, 1000);
        lista.atualizar(sala);
        assertTrue(!lista.listar().contains(sala));
    }

    /**
     * Test of procurarPorNumero method, of class SalaRN.
     */
    @Test
    public void testProcurarPorNumero() throws Exception {
        System.out.println("procurarPorNumero");
        int tamanhoLista = lista.listar().size();
        int ultimoRegistro = lista.listar().get(tamanhoLista-1).getNumeroSala();
        
        Sala result = lista.procurarPorNumero(ultimoRegistro);
        assertNotNull(result);
    }
    
}
