package rn;

import java.util.Date;
import java.util.List;
import model.Assento;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author eduardo
 */
public class AssentoRNTest {
    private AssentoRN lista;
    public AssentoRNTest() {
        lista = new AssentoRN();
    }
    

    /**
     * Test of adicionar method, of class AssentoRN.
     */
    @Test
    public void testAdicionar() throws Exception {
        System.out.println("adicionar");
        Assento a = null;
        AssentoRN instance = new AssentoRN();
        instance.adicionar(a);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listar method, of class AssentoRN.
     */
    @Test
    public void testListar() {
        System.out.println("listar");
        AssentoRN instance = new AssentoRN();
        List<Assento> expResult = null;
        List<Assento> result = instance.listar();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of atualizar method, of class AssentoRN.
     */
    @Test
    public void testAtualizar() throws Exception {
        System.out.println("atualizar");
        Assento assento = null;
        AssentoRN instance = new AssentoRN();
        instance.atualizar(assento);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of procurarPorDataCodigo method, of class AssentoRN.
     */
    @Test
    public void testProcurarPorDataCodigo() throws Exception {
        System.out.println("procurarPorDataCodigo");
        Date data = null;
        int codigo = 0;
        AssentoRN instance = new AssentoRN();
        Assento expResult = null;
        Assento result = instance.procurarPorDataCodigo(data, codigo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
