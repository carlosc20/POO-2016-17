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
import java.util.Arrays;

public class Individual extends Contribuinte
{
    // situação sócioprossional, que pode ser utilizada para o cálculo das deduções fiscais: 
    private Set<Integer> dependentesAF; // AF = agregado familiar
    private float coefFiscal; // um fator multiplicativo que é associado a cada despesa elegível
    private List<AtivEco> ativDedutiveis; 
    private List<Fatura> faturas;
    private List<Alteracao> histAlter;
    
    /**
     * Construtor para objetos da classe Individual
     */
    public Individual()
    {
        super();
        this.dependentesAF = null;
        this.coefFiscal = 0;
        this.ativDedutiveis = null; 
        this.faturas = new ArrayList<Fatura>();
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
    public Individual(int nif, String nome, String email, String morada, String password, int numDependentesAF, int coefFiscal, int[] nifAF, int[] ativDedutiveis, ArrayList<Fatura> faturas)
    {
        super(nif, nome, email, morada, password);
        this.coefFiscal = coefFiscal;
        this.faturas = faturas;
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
    public void setAtivDedutiveis(List ativDedutiveis)
    {
        this.ativDedutiveis = ativDedutiveis;
    }
    
    /**
     * Obtém os códigos das atividades económicas que o contribuinte pode deduzir despesas
     * 
     * @return     os códigos das atividades económicas que o contribuinte pode deduzir despesas
     */
    public List getAtivDedutiveis()
    {
        return this.ativDedutiveis;
    }
    
    public void setAgregadoFamiliar(Collection<Integer> agregado){
        this.dependentesAF = new HashSet<>();
        this.dependentesAF.addAll(agregado);
    }
    
    public void addAgregadoFamiliar(int nif){
        this.dependentesAF.add(nif);
    }
    
    public void removeAgregadoFamiliar(int nif){
        this.dependentesAF.remove(nif);
    }
    
    public Set<Integer> getAgregadoFamiliar(){
        HashSet<Integer> novo = new HashSet<>();
        
        for(int nif : this.dependentesAF){
            novo.add(nif);
        }
        
        return novo;
    }
    
    //--------------------------------------------------------------------------------------------------------------------------------------------------------
    // métodos
    //--------------------------------------------------------------------------------------------------------------------------------------------------------
    

    //A FAZER:
    //verifica as despesas que foram emitidas em seu nome e verificar o montante de dedução fiscal acumulado, por si e pelo agregado familiar
    
     /**
     * Atribui uma fatura ao consumidor
     * @param  fatura    Fatura atribuida
     */
    public void adicionaFatura(Fatura fatura) {
        faturas.add(fatura);
    }
    
    
     /**
     * Corrige classicação de actividade económica de um documento de despesa e deixa registo desta operação
     * @param  fatura    Fatura a ser corrigida
     * @param  setorEco  Setor económico indicado para classificar a fatura    
     */
    public void corrigeFatura(Fatura fatura, AtivEco ativ){
        fatura.setAtivEconomica(ativ);
        //FAZER: deixar registo
    }
    
    public Individual clone(){
        return new Individual(this);
    }

    public String fancyAtivEco(List<AtivEco> ativEco){
        StringBuilder sb = new StringBuilder("");
        for(AtivEco a : ativEco){
            sb.append(a.fancyToString()).append("; ");
        }
        return sb.toString();
    }
    
    public String fancyToString(){
        StringBuilder sb = new StringBuilder("");
        sb.append(super.toString()).append("\n");
        sb.append("Numero de Dependentes do Agregado Familiar: ").append(this.dependentesAF.size()).append("\n");
        sb.append("Nif de Dependentes do Agregado Familiar: ").append(this.dependentesAF).append("\n");
        sb.append("Coeficiente Fiscal: ").append(this.coefFiscal).append("\n");
        sb.append("Atividades Dedutiveis: ").append(fancyAtivEco(this.ativDedutiveis)).append("\n");
        return sb.toString();
    }
    
    public String toString(){
        StringBuilder sb = new StringBuilder("Contribuinte Individual{\n");
        sb.append(super.toString()).append("\n");
        sb.append("Nif Dependentes do Agregado Familiar: ").append(this.dependentesAF).append("\n");
        sb.append("Coeficiente Fiscal: ").append(this.coefFiscal).append("\n");
        sb.append("Atividades Dedutiveis").append(this.ativDedutiveis).append("\n");
        sb.append("Faturas: ").append(this.faturas).append("\n");
        sb.append("Alteraçoes: ").append(this.histAlter).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
