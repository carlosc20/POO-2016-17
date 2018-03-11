
/**
 * Write a description of class Fatura here.
 *
 * @author Carlos Castro, Daniel Costa, Luís Macedo
 * @version 11/03/2018
 */

import java.time.LocalDate;

public class Fatura
{
    private static int totalFaturas;
    
    private boolean validada; 
    private Coletivo emitente;
    private LocalDate dataEmissao;
    private int nifCliente;
    private String descricao;
    private String ativEconomica; //enum? codigo int?
    private int valorTotal; //em centimos
    //private int valorImposto;
    //private int valorDedutivel;

    /**
     * Constructor for objects of class Fatura
     */
    public Fatura()
    {

    }


    
}
