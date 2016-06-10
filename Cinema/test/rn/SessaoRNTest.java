/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rn;

import java.util.Date;
import java.util.List;
import model.Sessao;
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
public class SessaoRNTest {
    
    public SessaoRNTest() {
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
     * Test of verificaTempo method, of class SessaoRN.
     */
    @Test
    public void testVerificaTempo() throws Exception {
        System.out.println("verificaTempo");
        Sessao sessaoParametro = null;
        SessaoRN instance = new SessaoRN();
        instance.verificaTempo(sessaoParametro);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of adicionar method, of class SessaoRN.
     */
    @Test
    public void testAdicionar() throws Exception {
        System.out.println("adicionar");
        Sessao s = null;
        SessaoRN instance = new SessaoRN();
        instance.adicionar(s);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listar method, of class SessaoRN.
     */
    @Test
    public void testListar() {
        System.out.println("listar");
        SessaoRN instance = new SessaoRN();
        List<Sessao> expResult = null;
        List<Sessao> result = instance.listar();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deletar method, of class SessaoRN.
     */
    @Test
    public void testDeletar() throws Exception {
        System.out.println("deletar");
        Sessao sessao = null;
        SessaoRN instance = new SessaoRN();
        instance.deletar(sessao);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of atualizar method, of class SessaoRN.
     */
    @Test
    public void testAtualizar() throws Exception {
        System.out.println("atualizar");
        Sessao sessao = null;
        SessaoRN instance = new SessaoRN();
        instance.atualizar(sessao);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of procurarPorId method, of class SessaoRN.
     */
    @Test
    public void testProcurarPorId() throws Exception {
        System.out.println("procurarPorId");
        int codigoSessao = 0;
        SessaoRN instance = new SessaoRN();
        Sessao expResult = null;
        Sessao result = instance.procurarPorId(codigoSessao);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of procuraPorHorario method, of class SessaoRN.
     */
    @Test
    public void testProcuraPorHorario() throws Exception {
        System.out.println("procuraPorHorario");
        Date horario = null;
        SessaoRN instance = new SessaoRN();
        List<Sessao> expResult = null;
        List<Sessao> result = instance.procuraPorHorario(horario);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
