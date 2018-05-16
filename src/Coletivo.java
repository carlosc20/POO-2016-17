
/**
 * Classe para armazenar informações sobre um contribuinte coletivo/empresa.
 * 
 * @author Carlos Castro, Daniel Costa, Luís Macedo
 * @version 11/03/2018
 */

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;

public class Coletivo extends Contribuinte
{

    private List<AtivEco> ativEco; 
    private float fatorDeducao; //Ex: privilegia empresas do interior
    private ArrayList<Fatura> faturasEmitidas; //ordenadas por data
    
    public Coletivo()
    {
        //Por fazer
    }
    
    public Fatura emitirFatura(int valor, Individual cliente, LocalDate data, String descricao){
        AtivEco atividadeE = AtivEco.Pendente; //fica à espera de validaçao do cliente
        
        if(ativEco.size() == 1) {
            atividadeE = ativEco.get(0);
                //calcula deduçao
        }
        Fatura fatura = new Fatura(this, data, cliente.getNif(), descricao, atividadeE, valor);
        faturasEmitidas.add(fatura);
        cliente.adicionaFatura(fatura);
        
        return fatura;
    }
    
    public int totalFaturado(LocalDate inicio, LocalDate fim) {
        //percorrer faturasEmitidas e somar valores
        return 0;
    }
    
    public boolean temAtivEco(AtivEco ativ){
        return ativEco.contains(ativ);
    }
    
}
