/**
 * Classe para armazenar informações sobre um contribuinte.
 * A classe guarda o NIF, o nome, e-mail, morada, palavra-passe,
 * coeficiente fiscal, números fiscais do agregado familiar e códigos das atividades económicas
 * 
 * @author Daniel Costa 
 * @version 25/02/2018
 */
public class Individual extends Taxpayer
{
    private int taxCoefficient;
    private int[] householdTaxID;
    private int[] economicActivities;
    
    /**
     * Construtor para objetos da classe Individual
     */
    public Individual()
    {
        this.taxCoefficient = 0;
        this.householdTaxID = null;
        this.economicActivities = null;
    }
    
    /**
     * Construtor para objetos da classe Individual recebendo valores iniciais
     * 
     * @param  taxId              Número de Identificação Fiscal do contribuinte
     * @param  name               Nome do contribuinte
     * @param  email              E-mail de contacto do contribuinte
     * @param  adress             Morada do contribuinte
     * @param  password           Palavra-passe de acesso do contribuinte
     * @param  taxCoefficient     Coeficiente fiscal para efeitos de dedução do contribuinte
     * @param  householdTaxID     Números fiscais do agregado familiar do contribuinte
     * @param  economicActivities Códigos das atividades económicas que o contribuinte pode deduzir despesas
     */
    public Individual(int taxId, String name, String email, String adress, String password, int taxCoefficient, int[] householdTaxID, int[] economicActivities)
    {
        super(taxId, name, email, adress, password);
        this.taxCoefficient = taxCoefficient;
        this.householdTaxID = householdTaxID;
        this.economicActivities = economicActivities;
    }
    
    /**
     * Construtor para objetos da classe Individual recebendo um objeto da classe Individual
     * 
     * @param  taxId              Número de Identificação Fiscal do contribuinte
     * @param  name               Nome do contribuinte
     * @param  email              E-mail de contacto do contribuinte
     * @param  adress             Morada do contribuinte
     * @param  password           Palavra-passe de acesso do contribuinte
     * @param  taxCoefficient     Coeficiente fiscal para efeitos de dedução do contribuinte
     * @param  householdTaxID     Números fiscais do agregado familiar do contribuinte
     * @param  economicActivities Códigos das atividades económicas que o contribuinte pode deduzir despesas
     */
    public Individual(Individual other)
    {
        this.taxId = other.getTaxId();
        this.name = other.getName();
        this.email = other.getEmail();
        this.adress = other.getAdress();
        this.password = other.getPassword();
        this.taxCoefficient = other.getTaxCoefficient();
        this.householdTaxID = other.getHouseholdTaxID();
        this.economicActivities = other.getEconomicActivities();
    }
    
    /**
     * Altera o coeficiente fiscal do contribuinte
     * @param  taxCoefficient    Coeficiente fiscal do contribuinte
     */
    public void setTaxCoefficient(int taxCoefficient)
    {
        this.taxCoefficient = taxCoefficient;
    }
    
    /**
     * Obtém o NIF do contribuinte
     * 
     * @return     o NIF do contribuinte
     */
    public int getTaxCoefficient()
    {
        return this.taxCoefficient;
    }
    
    /**
     * Altera os números fiscais do agregado familiar do contribuinte
     * @param  householdTaxID    Números fiscais do agregado familiar do contribuinte
     */
    public void setHouseholdTaxID(int[] householdTaxID)
    {
        this.householdTaxID = householdTaxID;
    }
    
    /**
     * Obtém os números fiscais do agregado familiar do contribuinte
     * 
     * @return     os números fiscais do agregado familiar do contribuinte
     */
    public int[] getHouseholdTaxID()
    {
        return this.householdTaxID;
    }
    
    /**
     * Altera os códigos das atividades económicas que o contribuinte pode deduzir despesas
     * @param  economicActivities    Códigos das atividades económicas que o contribuinte pode deduzir despesas
     */
    public void setEconomicActivities(int[] economicActivities)
    {
        this.economicActivities = economicActivities;
    }
    
    /**
     * Obtém os códigos das atividades económicas que o contribuinte pode deduzir despesas
     * 
     * @return     os códigos das atividades económicas que o contribuinte pode deduzir despesas
     */
    public int[] getEconomicActivities()
    {
        return this.economicActivities;
    }
}
