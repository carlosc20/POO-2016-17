
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
    
    public Fatura(){
        this.totalFaturas = 0;
        this.validada = false;
        setEmitente(new Coletivo());
        this.dataEmissao = LocalDate.now();
        this.nifCliente = 0;
        this.descricao = "n/a";
        this.ativEconomica = "n/a";
        this.valorTotal = 0;
    }
    
    public Fatura(int nif, String nome, LocalDate dataEmissao, 
    int nifCliente, String descricao, String ativEconomica, int valorTotal){
        this.totalFaturas = 0;
        this.validada = false;
        setEmitente(new Coletivo());
        setDataEmissao(dataEmissao);
        this.nifCliente = nifCliente;
        this.descricao = descricao;
        this.ativEconomica = ativEconomica;
        this.valorTotal = valorTotal;
    }
    
    public Fatura(int totalFaturas, boolean validada, Coletivo emitente, LocalDate dataEmissao, 
    int nifCliente, String descricao, String ativEconomica, int valorTotal){
        this.totalFaturas = totalFaturas;
        this.validada = validada;
        setEmitente(emitente);
        setDataEmissao(dataEmissao);
        this.nifCliente = nifCliente;
        this.descricao = descricao;
        this.ativEconomica = ativEconomica;
        this.valorTotal = valorTotal;
    }
    
    public Fatura(Fatura umaFatura){
        this.totalFaturas = umaFatura.getTotalFaturas();
        this.validada = umaFatura.getValidada();
        setEmitente(umaFatura.getEmitente());
        setDataEmissao(umaFatura.getDataEmissao());
        this.nifCliente = umaFatura.getNifCliente();
        this.descricao = umaFatura.getDescricao();
        this.ativEconomica = umaFatura.getAtivEconomica();
        this.valorTotal = umaFatura.getValorTotal();
    }
    
    public int getTotalFaturas(){
        return this.totalFaturas;
    }
    
    public boolean getValidada(){
        return this.validada;
    }
    
    public void setValidada(boolean val){
        this.validada=val;
    }
    
    public Coletivo getEmitente(){
        return this.emitente.clone();
    }
    
    public void setEmitente(Coletivo emitente){
        this.emitente=emitente.clone();
    }
    
    public LocalDate getDataEmissao(){
        return this.dataEmissao;
    }
    
    public void setDataEmissao(LocalDate data){
        this.dataEmissao=LocalDate.of(data.getYear(), data.getMonth(), data.getDayOfMonth());
    }
    
    public int getNifCliente(){
        return this.nifCliente;
    }
    
    public void setNifCliente(int nif){
        this.nifCliente=nif;
    }
    
    public String getDescricao(){
        return this.descricao;
    }
    
    public void setDescricao(String descricao){
        this.descricao=descricao;
    }
    
    public String getAtivEconomica(){
        return this.ativEconomica;
    }
    
    public void setAtivEconomica(String ativEconomica){
        this.ativEconomica=ativEconomica;
    }
    
    public int getValorTotal(){
        return this.valorTotal;
    }
    
    public void setValorTotal(int valor){
        this.valorTotal=valor;
    }
    
    public Fatura clone(){
        return new Fatura(this);
    }
    
    public boolean equals(Object o){
        if(o==this){
            return true;
        }
        if(o==null || o.getClass() != this.getClass()){
            return false;
        }
        
        Fatura e = (Fatura) o;
        
        return this.validada==e.getValidada() && this.emitente.equals(e.getEmitente()) && 
        this.dataEmissao.equals(e.getDataEmissao()) && this.nifCliente==e.getNifCliente() && 
        this.descricao.equals(e.getDescricao()) && this.ativEconomica.equals(e.getAtivEconomica()) && 
        this.valorTotal==e.getValorTotal() && this.totalFaturas==e.getTotalFaturas();
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Numero Total de Faturas: ").append(this.totalFaturas);
        sb.append("Validada: ").append(this.validada);
        sb.append("Emitente: ").append(this.emitente.toString());
        sb.append("Data de Emissao: ").append(this.dataEmissao);
        sb.append("NIF do Cliente: ").append(this.nifCliente);
        sb.append("Descriçao: ").append(this.descricao);
        sb.append("Atividades Economicas: ").append(this.ativEconomica);
        sb.append("Valor Total: ").append(this.valorTotal);
        return sb.toString();   
    }
}
