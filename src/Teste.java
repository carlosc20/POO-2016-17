

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.time.LocalDate;

/**
 * The test class Teste.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class Teste
{
    Estado estado;

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
        int numEmpresas = 10;
        int numClientes = 25;
        for(int i = 0; i < numEmpresas; i++){
            Coletivo novo = new Coletivo(1000 + i, "Empresa" + i, "empresa" + i + "@email.com", "Rua R, " + i, "", "Braga", new java.util.HashSet<>());
            this.estado.addContribuinte(novo);
        }
        
        for(int i = 0; i < numClientes; i++){
            Individual novo = new Individual(i, "Cliente" + i, "cliente" + i + "@email.com", "Rua R," + i, "", 0.0f, new HashSet<>(), new HashSet<>());
            this.estado.addContribuinte(novo);
        }

        for(int i = 0; i < numEmpresas; i++){
            for(int j = i; j < numClientes; j++){
                for(int k = 0; k < j; k++){
                    Fatura novo = new Fatura(1000 + i, LocalDate.ofYearDay(2018, i * j + k + 1), j, "Comida", AtivEco.Pendente, i * j + k);
                    this.estado.addFatura(novo);
                }
            }
        }
       
        App teste = new App(estado);
        teste.main(null);
    }
}

