
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rn;

import java.util.List;
import model.Venda;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Edu
 */
public class VendaRNTest {
    
    public VendaRNTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of adicionar method, of class VendaRN.
     */
    @Test
    public void testAdicionar() throws Exception {
        System.out.println("adicionar");
        Venda v = null;
        VendaRN instance = new VendaRN();
        instance.adicionar(v);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listar method, of class VendaRN.
     */
    @Test
    public void testListar() {
        System.out.println("listar");
        VendaRN instance = new VendaRN();
        List<Venda> expResult = null;
        List<Venda> result = instance.listar();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of procurarPorId method, of class VendaRN.
     */
    @Test
    public void testProcurarPorId() throws Exception {
        System.out.println("procurarPorId");
        int registroVenda = 0;
        VendaRN instance = new VendaRN();
        Venda expResult = null;
        Venda result = instance.procurarPorId(registroVenda);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
