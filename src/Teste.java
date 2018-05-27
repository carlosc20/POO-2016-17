

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Random;
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
        int numClientes = 200;
        String[] concelhos = {"Braga", "Guimarães", "Aveiro", "Lisboa", "Guarda", "Castelo Branco"};
        //Concelhos com incentivo fiscal
        this.estado.setIncentivoFiscal("Guarda", 1.5f);
        this.estado.setIncentivoFiscal("Castelo Branco", 2.0f);
        
        Random rand = new Random();
        this.estado.addContribuinte(new Administrador());

        for(int i = 1; i <= numEmpresas; i++){
            HashSet<AtivEco> ativEcos = new HashSet<>();
            for(AtivEco ativ : AtivEco.values()){
                if(rand.nextBoolean()){
                    ativEcos.add(ativ);
                }
            }
            Coletivo novo = new Coletivo(10000 + i, "Empresa " + i, "empresa" + i + "@email.com", "Rua R, " + i, "", concelhos[rand.nextInt(concelhos.length)], ativEcos);
            this.estado.addContribuinte(novo);
        }
        
        for(int i = 1; i <= numClientes; i++){
            HashSet<AtivEco> ativEcos = new HashSet<>();
            for(AtivEco ativ : AtivEco.values()){
                if(rand.nextBoolean()){
                    ativEcos.add(ativ);
                }
            }
            HashSet<Integer> af = new HashSet<>();
            while(rand.nextBoolean()){
                af.add(rand.nextInt(i));
            }
            Individual novo = new Individual(i, "Cliente " + i, "cliente" + i + "@email.com", "Rua R," + i, "", 1.0f, new HashSet<>(), ativEcos);
            this.estado.addContribuinte(novo);
        }

        for(int i = 1; i <= numEmpresas; i++){
            for(int j = 1; j <= numClientes; j++){
                while(rand.nextBoolean()){
                    Fatura novo = new Fatura(10000 + i, LocalDate.ofYearDay(2017, 1 + rand.nextInt(364)), j, "Comida", AtivEco.values()[rand.nextInt(AtivEco.values().length)], rand.nextInt(100000) / 100.0f);
                    try{
                        this.estado.addFatura(novo);
                    } catch (Exception e) {
                        System.out.println("Fatura não emitida: " + e.getMessage());
                    }
                }
            }
        }
        
        App teste = new App(estado);
        teste.main(null);
    }
    
    @Test
    public void testarMenuSemDados()
    {
        App teste = new App();
        teste.main(null);
    }
}

