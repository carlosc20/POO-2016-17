
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
    
    private Coletivo emitente;
    private LocalDate dataEmissao;
    private int nifCliente;
    private String descricao;
    private AtivEco aEcon; 
    private int valorTotal; //em centimos
    //private int valorImposto; 
    //private int valorDedutivel; -1 se pendente?
    //private List<Alteracao> histAlter ?
    
    /**
     * Constructor for objects of class Fatura
     */
    public Fatura()
    {

    }

    public Fatura(Coletivo emitente, LocalDate dataEmissao, int nifCliente, String descricao, AtivEco aEcon, int valorTotal)
    {

    }
    
    public Coletivo getEmitente() {
        return this.emitente;
    }
    
    public void setAtivEconomica(AtivEco ativ){
        this.aEcon = ativ;
    }
    
    public void atualizaDeducao() {
        //valorDedutivel = 
    }
}
