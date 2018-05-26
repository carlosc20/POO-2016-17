

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
    Estado estado;
        
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
    public void testarMenu()
    {
        this.estado = new Estado();

        
        this.empresa1 = new Coletivo(1001, "Empresa1", "email1", "morada1", "1234", new java.util.HashSet<>(), 100);
        this.empresa2 = new Coletivo(1002, "Empresa2", "", "", "", new java.util.HashSet<>(), 0);
        this.empresa3 = new Coletivo(1003, "Empresa3", "", "", "", new java.util.HashSet<>(), 0);

        Individual cliente1 = new Individual(1, "Cliente1", "e1", "m1", "1234", 0, 0, null, null, new java.util.ArrayList<>());
        Individual cliente2 = new Individual(2, "Cliente2", "", "", "1234", 0, 0, null, null, new java.util.ArrayList<>());
        Individual cliente3 = new Individual(3, "Cliente3", "", "", "1234", 0, 0, null, null, new java.util.ArrayList<>());
        Individual cliente4 = new Individual(4, "Cliente4", "", "", "1234", 0, 0, null, null, new java.util.ArrayList<>());
        Individual cliente5 = new Individual(5, "Cliente5", "", "", "1234", 0, 0, null, null, new java.util.ArrayList<>());

        
        this.estado.addContribuinte(empresa1);
        this.estado.addContribuinte(empresa2);
        this.estado.addContribuinte(empresa3);
        
        this.estado.addContribuinte(cliente1);
        this.estado.addContribuinte(cliente2);
        this.estado.addContribuinte(cliente3);
        this.estado.addContribuinte(cliente4);
        this.estado.addContribuinte(cliente5);
        
        this.estado.addFatura(new Fatura(empresa1.getNif(), cliente1.getNif()));
        this.estado.addFatura(new Fatura(empresa1.getNif(), cliente1.getNif()));
        this.estado.addFatura(new Fatura(empresa2.getNif(), cliente1.getNif()));
        this.estado.addFatura(new Fatura(empresa1.getNif(), cliente3.getNif()));
        this.estado.addFatura(new Fatura(empresa3.getNif(), cliente4.getNif()));
        this.estado.addFatura(new Fatura(empresa3.getNif(), cliente5.getNif()));
       
        App teste = new App(estado);
        teste.run();


    }


    @Test
    public void getContribuinte()
    {
        try{
            assertEquals(empresa1, this.estado.getContribuinte(1001));
        } catch(Exception err){
            assertEquals(false, true);
        }
    }
}

