/**
 * Classe para lançar uma exceção quando não existe contribuinte
 * 
 * @author Carlos Castro, Daniel Costa, Luís Macedo
 * @version 25/05/2018
 */

public class NaoExisteContribuinteException extends Exception
{
	/**
     * Construtor para objetos da classe Não Existe Contribuinte Exceção.
     */
    public NaoExisteContribuinteException(){
        super();
    }
    
    /**
     * Construtor para objetos da classe Não Existe Contribuinte Exceção recebendo uma mensagem.
     */
    public NaoExisteContribuinteException(String message){
        super(message);
    }
}
