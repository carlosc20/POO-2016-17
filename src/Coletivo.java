
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
    private float totalFaturado;
    
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
        sb.append("Atividades Económicas: ").append(fancyAtivEco(ativEco)).append("\n");
        return sb.toString();
    }
    
    public String toString(){
        StringBuilder sb = new StringBuilder("Contribuinte Coletivo{\n");
        sb.append(super.toString()).append("\n");
        sb.append("Atividades Economicas: ").append(this.ativEco).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
    
    /**
     * Construtor para objetos da classe Coletivo
     */
    public Coletivo(){
        super();
        this.ativEco = new HashSet<>();
        this.totalFaturado = 0.0f;
    }
    
    /**
     * Construtor para objetos da classe Coletivo recebendo valores iniciais
     * 
     * @param  nif                Número de Identificação Fiscal do contribuinte
     * @param  nome               Nome do contribuinte
     * @param  email              E-mail de contacto do contribuinte
     * @param  morada             Morada do contribuinte
     * @param  password           Palavra-passe de acesso do contribuinte
     * @param  ativEco            Set com as atividades económicas
     * @param  fatorDeducao       Fator de dedução
     */
    public Coletivo(int nif, String nome, String email, String morada, String password, Set<AtivEco> ativEco, float totalFaturado){
        super(nif, nome, email, morada, password);
        this.ativEco = new HashSet<>(ativEco);
        this.totalFaturado = totalFaturado;
    }
    
    /**
     * Construtor para objetos da classe Coletivo recebendo outro objeto
     * @param outro     Objeto a ser copiado
     */
    public Coletivo(Coletivo outro){
        super(outro);
        this.ativEco = outro.getAtivEco();
        this.totalFaturado = outro.totalFaturado();
    }
    
    /**
     * Cria uma fatura
     * @param valor         Valor total da fatura
     * @param cliente       NIF do cliente da fatura
     * @param data          Data da emissão da fatura
     * @param descricao     Descrição da nova fatura
     * @return Uma nova fatura
     */
    // Por fazer
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
    
    // Por fazer
    public int totalFaturado(LocalDate inicio, LocalDate fim) {
        //percorrer faturasEmitidas e somar valores
        return 0;
    }
    
    /**
     * Modifica as atividades económicas da empresa
     * @param ativ         Novo Set com as  atividades económicas que a empresa pertence
     */
    public void setAtivEco(Set<AtivEco> ativ){
        this.ativEco = new HashSet<>(ativ);
    }
    
    /**
     * Retorna as atividades económicas da empresa
     * @return Set com as atividades económicas da empresa
     */
    public Set getAtivEco(){
        return new HashSet<>(this.ativEco);
    }
    
    /**
     * Verifica se a empresa tem a atividade económica
     * @param ativ          Atividade económica a verificar
     * @return 'true' se a empresa tiver a atividade económica passada como argumento, 'false' caso contrário
     */
    public boolean temAtivEco(AtivEco ativ){
        return ativEco.contains(ativ);
    }
    
    /**
     * Soma o valor ao total faturado
     * @param valor         Valor a ser adicionado ao total faturado
     */
    public void addTotalFaturado(float valor){
        this.totalFaturado += valor;
    }
    
    /**
     * Retorna o total faturado pela empresa
     * @return Valor total faturado pela empresa
     */
    public float totalFaturado(){
        return this.totalFaturado;
    }
    
    public Coletivo clone(){
        return new Coletivo(this);
    }

    /**
     * @param outro         Objeto a ser verificado se é igual
     * @return 'true' o contribuinte coletivo tiver o mesmo NIF
     */
    public boolean equals(Object outro) {
        if(outro == this) {
            return true;
        }
        if(outro == null || outro.getClass() != this.getClass()) {
            return false;
        }
        Coletivo outroColetivo = (Coletivo) outro;
        if(this.getNif() != outroColetivo.getNif()){
            return false;
        }
        return true;
    }
}
