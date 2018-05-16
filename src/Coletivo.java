
/**
 * Classe para armazenar informações sobre um contribuinte coletivo/empresa.
 * 
 * @author Carlos Castro, Daniel Costa, Luís Macedo
 * @version 11/03/2018
 */

import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Coletivo extends Contribuinte
{    
    private String[] cae; //setor economico?
    private float fatorDeducao;
    private ArrayList<Fatura> faturasEmitidas; //ordenadas por data
    
    public Coletivo(){
        this.cae=new String[10];
        this.fatorDeducao=0;
        this.faturasEmitidas=new ArrayList<>();
    }
    
    public Coletivo(String[] cae, float fatorDeducao, ArrayList<Fatura> faturasEmitidas){
        this.cae=Arrays.copyOf(cae,cae.length);
        this.fatorDeducao=fatorDeducao;
        this.faturasEmitidas = new ArrayList<>();
        faturasEmitidas.forEach(s -> {this.faturasEmitidas.add(s);});
    }
    
    public Coletivo(Coletivo umColetivo){
        setCae(umColetivo.getCae());
        setFatorDeducao(umColetivo.getFatorDeducao());
        setFaturasEmitidas(umColetivo.getFaturasEmitidas());
    }
    
    public String[] getCae(){
        return Arrays.copyOf(this.cae, this.cae.length);
    }
    
    public void setCae(String[] cae){
        this.cae=Arrays.copyOf(cae,cae.length);
    }
    
    public float getFatorDeducao(){
        return this.fatorDeducao;
    }
    
    public void setFatorDeducao(float fator){
        this.fatorDeducao=fator;
    }
    
    public ArrayList<Fatura> getFaturasEmitidas(){
        ArrayList<Fatura> res = new ArrayList<>();
        for(Fatura le : this.faturasEmitidas) {
            res.add(le.clone());
        }
        return res;
    }
    
    public void setFaturasEmitidas(ArrayList<Fatura> faturas){
        this.faturasEmitidas = new ArrayList<>();
        faturas.forEach(s->{this.faturasEmitidas.add(s);});
    }
    
    public Coletivo clone(){
        return new Coletivo(this);
    }
    
    public Fatura emitirFatura(int valor, Individual cliente, LocalDate data, String descricao){
        String atividadeE = "Pendente"; //fica à espera de validaçao do cliente
        if(cae.length == 1) 
            atividadeE = cae[0]; 
        //algoritmo deduçao?
        Fatura fatura = new Fatura(this.nif, this.nome, data, cliente.getNif(), descricao, atividadeE, valor);
        faturasEmitidas.add(fatura);
        cliente.adicionaFatura(fatura);
        return fatura;
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
