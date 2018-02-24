
/**
 * Classe para armazenar informações sobre um contribuinte.
 * A classe guarda o NIF, o nome, e-mail, morada e palavra-passe.
 * 
 * @author Daniel Costa
 * @version 24/02/2018
 */
public class Taxpayer
{
    private int taxId;
    private String name;
    private String email;
    private String adress;
    private String password; //Isto é muito seguro
    
    /**
     * Construtor para objetos da classe Contribuinte
     */
    public Taxpayer()
    {
        this.taxId = 0;
        this.name = "";
        this.email = "";
        this.adress = "";
        this.password = "";
    }
    
    /**
     * Construtor para objetos da classe Contribuinte recebendo parametros
     * 
     * @param  taxId    Número de Identificação Fiscal do contribuinte
     * @param  name     Nome do contribuinte
     * @param  email    E-mail de contacto do contribuinte
     * @param  adress   Morada do contribuinte
     * @param  password Palavra-passe de acesso do contribuinte
     */
    public Taxpayer(int taxId, String name, String email, String adress, String password)
    {
        this.taxId = taxId;
        this.name = name;
        this.email = email;
        this.adress = adress;
        this.password = password;
    }
    
    /**
     * Construtor para objetos da classe Contribuinte recebendo outro objeto da classe Contribuinte
     * 
     * @param  contribuinte Outro contribuinte
     */
    public Taxpayer(Taxpayer contribuinte)
    {
        this.taxId = contribuinte.getTaxId();
        this.name = contribuinte.getName();
        this.email = contribuinte.getEmail();
        this.adress = contribuinte.getAdress();
        this.password = contribuinte.getPassword();
    }

    /**
     * Altera o NIF do contribuinte
     * @param  taxId    Número de Identificação Fiscal do contribuinte
     */
    public void setTaxId(int taxId)
    {
        this.taxId = taxId;
    }
    
    /**
     * Obtém o NIF do contribuinte
     * 
     * @return     o NIF do contribuinte
     */
    public int getTaxId()
    {
        return this.taxId;
    }
    
    /**
     * Altera o nome do contribuinte
     * @param  name     Nome do contribuinte
     */
    public void setName(String name)
    {
        this.name = name;
    }
    
    /**
     * Obtém o nome do contribuinte
     * 
     * @return     o nome do contribuinte
     */
    public String getName()
    {
        return this.name;
    }
    
    /**
     * Altera o e-mail do contribuinte
     * @param  email    E-mail de contacto do contribuinte
     */
    public void setEmail(String email)
    {
        this.email = email;
    }
    
    /**
     * Obtém o e-mail do contribuinte
     * 
     * @return     o e-mail do contribuinte
     */
    public String getEmail()
    {
        return this.email;
    }
    
    /**
     * Altera a morada do contribuinte
     * @param  adress   Morada do contribuinte
     */
    public void setAdress(String adress)
    {
        this.adress = adress;
    }
    
    /**
     * Obtém a morada do contribuinte
     * 
     * @return     a morada do contribuinte
     */
    public String getAdress()
    {
        return this.adress;
    }
    
    /**
     * Altera a palavra-passe do contribuinte
     * @param  password Palavra-passe de acesso do contribuinte
     */
    public void setPassword(String password)
    {
        this.password = password;
    }
    
    /**
     * Obtém a palavra-passe do contribuinte
     * 
     * @return     a palavra-passe do contribuinte
     */
    public String getPassword()
    {
        return this.password;
    }
}
