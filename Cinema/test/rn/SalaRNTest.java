/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rn;

import java.util.List;
import model.Sala;
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
public class SalaRNTest {
    
    public SalaRNTest() {
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
     * Test of adicionar method, of class SalaRN.
     */
    @Test
    public void testAdicionar() throws Exception {
        System.out.println("adicionar");
        Sala s = null;
        SalaRN instance = new SalaRN();
        instance.adicionar(s);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listar method, of class SalaRN.
     */
    @Test
    public void testListar() {
        System.out.println("listar");
        SalaRN instance = new SalaRN();
        List<Sala> expResult = null;
        List<Sala> result = instance.listar();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deletar method, of class SalaRN.
     */
    @Test
    public void testDeletar() throws Exception {
        System.out.println("deletar");
        Sala sala = null;
        SalaRN instance = new SalaRN();
        instance.deletar(sala);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of atualizar method, of class SalaRN.
     */
    @Test
    public void testAtualizar() throws Exception {
        System.out.println("atualizar");
        Sala sala = null;
        SalaRN instance = new SalaRN();
        instance.atualizar(sala);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of procurarPorNumero method, of class SalaRN.
     */
    @Test
    public void testProcurarPorNumero() throws Exception {
        System.out.println("procurarPorNumero");
        int numero = 0;
        SalaRN instance = new SalaRN();
        Sala expResult = null;
        Sala result = instance.procurarPorNumero(numero);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
