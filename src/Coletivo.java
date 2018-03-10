
/**
 * Escreva a descrição da classe Collective aqui.
 * 
 * @author Daniel
 * @version 25/02/2018
 */
import java.time.LocalDate;

public class Coletivo extends Contribuinte
{
    /**
     * COnstrutor para objetos da classe Coletivo
     */
    
    private String[] atividadesEconomicas;
    private float fatorDeducao;
    
    public Coletivo()
    {
        //Por fazer
    }
    
    public Fatura emitirFatura(int valor, Individual cliente, LocalDate data, String descricao){
        String atividadeE = "Pendente"; //fica a espera de validaçao do cliente
        if(atividadesEconomicas.length == 1) atividadeE = atividadesEconomicas[0]; //ver se pertence a atividades dedutiveis do cliente
        return Fatura(this.nif, this.nome, data, cliente.getNif(), descricao, atividadeE, valor);
    }
    
}
