
/**
 * Classe para armazenar informações sobre um contribuinte coletivo/empresa.
 * 
 * @author Carlos Castro, Daniel Costa, Luís Macedo
 * @version 11/03/2018
 */

import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;

public class Coletivo extends Contribuinte
{

    private Set<AtivEco> ativEco; 
    private float fatorDeducao; //Ex: privilegia empresas do interior
    
    public String fancyAtivEco(Set<AtivEco> ativEco){
        StringBuilder sb = new StringBuilder("");
        for(AtivEco a : ativEco){
            sb.append(a.fancyToString()).append("; ");
        }
        return sb.toString();
    }
    
    public String fancyToString(){
        StringBuilder sb = new StringBuilder("");
        sb.append(super.toString()).append("\n");
        sb.append("Atividades Economicas: ").append(fancyAtivEco(ativEco)).append("\n");
        sb.append("Fator de Deduçao: ").append(fatorDeducao).append("\n");
        return sb.toString();
    }
    
    public String toString(){
        StringBuilder sb = new StringBuilder("Contribuinte Coletivo{\n");
        sb.append(super.toString()).append("\n");
        sb.append("Atividades Economicas: ").append(this.ativEco).append("\n");
        sb.append("Fator de Deduçao: ").append(fatorDeducao).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
    
    public Coletivo(){
        super();
        this.ativEco = new HashSet<>();
        this.fatorDeducao = 0.0f;
    }
    
    public Coletivo(int nif, String nome, String email, String morada, String password, Set<AtivEco> ativEco, float fatorDeducao){
        super(nif, nome, email, morada, password);
        this.ativEco = ativEco;
        this.fatorDeducao = fatorDeducao;
    }
    
    public Coletivo(Coletivo other){
        super(other);
    }
    
    public Fatura emitirFatura(int valor,int cliente, LocalDate data, String descricao){
        AtivEco atividadeE = AtivEco.Pendente; //fica à espera de validaçao do cliente
        
        if(ativEco.size() == 1) {
            for(AtivEco a : ativEco){//Verificar se existe uma alternativa melhor
                atividadeE = a;
            }
                //calcula deduçao
        }
        Fatura fatura = new Fatura(getNif(), data, cliente, descricao, atividadeE, valor);
        
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
