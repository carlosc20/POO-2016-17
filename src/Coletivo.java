
/**
 * Classe para armazenar informações sobre um contribuinte coletivo/empresa.
 * 
 * @author Carlos Castro, Daniel Costa, Luís Macedo
 * @version 11/03/2018
 */

import java.util.ArrayList;
import java.time.LocalDate;

public class Coletivo extends Contribuinte
{
    /**
     * Construtor para objetos da classe Coletivo
     */
    
    private String[] cae; //setor economico?
    private float fatorDeducao;
    private ArrayList<Fatura> faturasEmitidas; //ordenadas por data
    
    public Coletivo()
    {
        //Por fazer
    }
    
    public Fatura emitirFatura(int valor, Individual cliente, LocalDate data, String descricao){
        String atividadeE = "Pendente"; //fica à espera de validaçao do cliente
        if(cae.length == 1) 
            atividadeE = cae[0]; 
        //algoritmo deduçao?
        Fatura fatura = new Fatura(this.nif, this.nome, data, cliente.getNif(), descricao, atividadeE, valor);
        faturasEmitidas.add(fatura);
        cliente.adicionaFatura(fatura);
    }
    
    public int totalFaturado(LocalDate inicio, LocalDate fim) {
        //percorrer faturasEmitidas e somar valores
        return 0;
    }
    
    public boolean temSetor(String setorEco){
        //ve se tem alguma cae pertencete ao setor
        return true;
    }
    
}
