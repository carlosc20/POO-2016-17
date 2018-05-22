/**
 * Classe para armazenar informações sobre um contribuinte individual.
 * 
 * @author Carlos Castro, Daniel Costa, Luís Macedo
 * @version 11/03/2018
 */

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;

public class Individual extends Contribuinte
{
    // situação sócioprossional, que pode ser utilizada para o cálculo das deduções fiscais: 
    private List<Integer> dependentesAF; // AF = agregado familiar
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
}
