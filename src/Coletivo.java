
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
    
    public Coletivo(){
        super();
        this.ativEco = new ArrayList<>();
        this.fatorDeducao = 0.0f;
        this.faturasEmitidas = new ArrayList<>();
    }
    
    public Coletivo(int nif, String nome, String email, String morada, String password, List<AtivEco> ativEco, float fatorDeducao, ArrayList<Fatura> faturasEmitidas){
        super(nif, nome, email, morada, password);
        this.ativEco = ativEco;
        this.fatorDeducao = fatorDeducao;
        this.faturasEmitidas = faturasEmitidas;
    }
    
    public Coletivo(Coletivo other){
        super(other);
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
    
    public Coletivo clone(){
        return new Coletivo(this);
    }

    public boolean equals(Object other) {
        if(other == this) {
            return true;
        }
        if(other == null || other.getClass() != this.getClass()) {
            return false;
        }
        Coletivo otherColetivo = (Coletivo) other;
        if(this.getNif() != otherColetivo.getNif()){
            return false;
        }
        return true;
    }
}
