

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class Teste.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class Teste
{
    Estado e = new Estado();
        
    Coletivo empresa1;
    Coletivo empresa2;
    Coletivo empresa3;
    
    Individual cliente1;
    Individual cliente2;
    Individual cliente3;
    Individual cliente4;
    Individual cliente5;
    /**
     * Default constructor for test class Teste
     */
    public Teste()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        Estado e = new Estado();
        
        Coletivo empresa1 = new Coletivo(1001, "Empresa1", "", "", "", new java.util.ArrayList<>(), 0);
        Coletivo empresa2 = new Coletivo(1002, "Empresa2", "", "", "", new java.util.ArrayList<>(), 0);
        Coletivo empresa3 = new Coletivo(1003, "Empresa3", "", "", "", new java.util.ArrayList<>(), 0);
        
        Individual cliente1 = new Individual(1, "Cliente1", "", "", "", 0, 0, null, null, new java.util.ArrayList<>());
        Individual cliente2 = new Individual(2, "Cliente1", "", "", "", 0, 0, null, null, new java.util.ArrayList<>());
        Individual cliente3 = new Individual(3, "Cliente1", "", "", "", 0, 0, null, null, new java.util.ArrayList<>());
        Individual cliente4 = new Individual(4, "Cliente1", "", "", "", 0, 0, null, null, new java.util.ArrayList<>());
        Individual cliente5 = new Individual(5, "Cliente1", "", "", "", 0, 0, null, null, new java.util.ArrayList<>());
        
        e.addContribuinte(empresa1);
        e.addContribuinte(empresa2);
        e.addContribuinte(empresa3);
        
        e.addContribuinte(cliente1);
        e.addContribuinte(cliente2);
        e.addContribuinte(cliente3);
        e.addContribuinte(cliente4);
        e.addContribuinte(cliente5);
        
        e.addFatura(new Fatura(empresa1.getNif(), cliente1.getNif()));
        e.addFatura(new Fatura(empresa1.getNif(), cliente1.getNif()));
        e.addFatura(new Fatura(empresa2.getNif(), cliente1.getNif()));
        e.addFatura(new Fatura(empresa1.getNif(), cliente3.getNif()));
        e.addFatura(new Fatura(empresa3.getNif(), cliente4.getNif()));
        e.addFatura(new Fatura(empresa3.getNif(), cliente5.getNif()));
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
        
    }

    @Test
    public void oi()
    {
    }
}

