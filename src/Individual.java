/**
 * Classe para armazenar informações sobre um contribuinte individual.
 * 
 * @author Carlos Castro, Daniel Costa, Luís Macedo
 * @version 11/03/2018
 */

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.time.LocalDate;
import java.io.Serializable;

public class Individual extends Contribuinte implements Serializable
{
    private Set<Integer> dependentesAF; // AF = agregado familiar
    private float coefFiscal; // um fator multiplicativo que é associado a cada despesa elegível
    private Set<AtivEco> ativDedutiveis; 
    
    /**
     * Construtor para objetos da classe Individual
     */
    public Individual()
    {
        super();
        this.dependentesAF = new HashSet<>();
        this.coefFiscal = 0;
        this.ativDedutiveis = new HashSet<>(); 
    }
    
    /**
     * Construtor para objetos da classe Individual recebendo valores iniciais
     * 
     * @param  nif                Número de Identificação Fiscal do contribuinte
     * @param  nome               Nome do contribuinte
     * @param  email              E-mail de contacto do contribuinte
     * @param  morada             Morada do contribuinte
     * @param  password           Palavra-passe de acesso do contribuinte
     * @param  coefFiscal         Coeficiente fiscal para efeitos de dedução do contribuinte
     * @param  nifAF              Números fiscais do agregado familiar do contribuinte
     * @param  ativDedutiveis     Códigos das atividades económicas que o contribuinte pode deduzir despesas
     */
    public Individual(int nif, String nome, String email, String morada, String password, float coefFiscal, Set<Integer> nifAF, Set<AtivEco> ativDedutiveis)
    {
        super(nif, nome, email, morada, password);
        this.coefFiscal = coefFiscal;
        this.dependentesAF = new HashSet<>();
        this.coefFiscal = 0;
        this.ativDedutiveis = new HashSet<>();
    }
    
    /**
     * Construtor para objetos da classe Individual recebendo um objeto da classe Individual
     * 
     * @param  nif                Número de Identificação Fiscal do contribuinte
     * @param  nome               Nome do contribuinte
     * @param  email              E-mail de contacto do contribuinte
     * @param  morada             Morada do contribuinte
     * @param  password           Palavra-passe de acesso do contribuinte
     * @param  coefFiscal         Coeficiente fiscal para efeitos de dedução do contribuinte
     * @param  nifAF              Números fiscais do agregado familiar do contribuinte
     * @param  ativDedutiveis     Códigos das atividades económicas que o contribuinte pode deduzir despesas
     */
    public Individual(Individual other)
    {
        super(other);
        this.coefFiscal = other.getCoefFiscal();
        this.ativDedutiveis = other.getAtivDedutiveis();
    }
    
    /**
     * Altera o coeficiente fiscal do contribuinte
     * @param  coefFiscal    Coeficiente fiscal do contribuinte
     */
    public void setCoefFiscal(float coefFiscal)
    {
        this.coefFiscal = coefFiscal;
    }
    
    /**
     * Obtém o NIF do contribuinte
     * 
     * @return     o NIF do contribuinte
     */
    public float getCoefFiscal()
    {
        return this.coefFiscal;
    }
     
    /**
     * Altera os códigos das atividades económicas que o contribuinte pode deduzir despesas
     * @param  ativDedutiveis    Códigos das atividades económicas que o contribuinte pode deduzir despesas
     */
    public void setAtivDedutiveis(Set ativDedutiveis)
    {
        this.ativDedutiveis = new HashSet<>(ativDedutiveis);
    }
    
    /**
     * Obtém os códigos das atividades económicas que o contribuinte pode deduzir despesas
     * 
     * @return     os códigos das atividades económicas que o contribuinte pode deduzir despesas
     */
    public Set getAtivDedutiveis()
    {
        return new HashSet<>(this.ativDedutiveis);
    }
    
    /**
     * Altera o Set do Agregado Familiar 
     * 
     * @param agregado A nova Collection a ser guardada
     */
    public void setAgregadoFamiliar(Collection<Integer> agregado){
        this.dependentesAF = new HashSet<>();
        this.dependentesAF.addAll(agregado);
    }
    
    /**
     * Adiciona um Numero de Identificaçao Fiscal ao Agregado Familiar
     * 
     * @param nif Numero de Identificaçao Fiscal a ser adicionado ao Agregado Familiar
     */
    public void addAgregadoFamiliar(int nif){
        this.dependentesAF.add(nif);
    }
    
    /**
     * Remove um Numero de Identificaçao Fiscal do Agregado Familiar
     * 
     * @param nif Numero de Identificaçao Fiscal a ser removido do Agregado Familiar
     */
    public void removeAgregadoFamiliar(int nif){
        this.dependentesAF.remove(nif);
    }
    
    /**
     * Obtem a Coleçao com os Numeros de Identificaçao Fiscais do Agregado Familiar
     * 
     * @return Um Set com os NIFs do Agregado Familiar
     */
    public Set<Integer> getAgregadoFamiliar(){     
        HashSet<Integer> novo = new HashSet<>();
        if(this.dependentesAF == null) return novo;
        
        for(int nif : this.dependentesAF){
            novo.add(nif);
        }
        
        return novo;
    }
    
    /**
     * Cria uma String com as infomaçoes, organizadas, contidas num Lista
     * 
     * @param ativEco Lista com Atividades Econimicas(AtivEco) guardadas
     * 
     * @return String com os elementos da Lista
     */
    public String fancyAtivEco(Set<AtivEco> ativEco){
        if(ativEco == null){return "Não tem";}
        StringBuilder sb = new StringBuilder("");
        for(AtivEco a : ativEco){
            sb.append(a.fancyToString()).append("; ");
        }
        return sb.toString();
    }
    
    /**
     * Cria e retorna uma String com as informaçoes, organizadas, pelo objeto, com o objetivo de apresetar ao user
     * 
     * @return String com as informaçoes do objeto
     */
    public String fancyToString(){
        StringBuilder sb = new StringBuilder("");
        sb.append(super.toString()).append("\n");
        if(this.dependentesAF != null){
            sb.append("Numero de Dependentes do Agregado Familiar: ").append(this.dependentesAF.size()).append("\n");
            sb.append("Nif de Dependentes do Agregado Familiar: ").append(this.dependentesAF).append("\n");
        }
        sb.append("Coeficiente Fiscal: ").append(this.coefFiscal).append("\n");
        sb.append("Atividades Dedutiveis: ").append(fancyAtivEco(this.ativDedutiveis)).append("\n");
        return sb.toString();
    }
    
    /**
     * Cria e retorna uma String com as informaçoes do objeto, com o objetivo de debugging
     * 
     * @return String com as informaçoes do objeto
     */
    public String toString(){
        StringBuilder sb = new StringBuilder("Contribuinte Individual{\n");
        sb.append(super.toString()).append("\n");
        sb.append("Nif Dependentes do Agregado Familiar: ").append(this.dependentesAF).append("\n");
        sb.append("Coeficiente Fiscal: ").append(this.coefFiscal).append("\n");
        sb.append("Atividades Dedutiveis").append(this.ativDedutiveis).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
    
    //--------------------------------------------------------------------------------------------------------------------------------------------------------
    // métodos
    //--------------------------------------------------------------------------------------------------------------------------------------------------------
    
    /**
     * Clona um objeto
     * 
     * @return Clone do Objeto
     */
    public Individual clone(){
        return new Individual(this);
    }
}
