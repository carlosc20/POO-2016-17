/**
 * Classe para lançar uma exceção quando se atinge o máximo de tentativas de login
 * 
 * @author Carlos Castro, Daniel Costa, Luís Macedo
 * @version 26/05/2018
 */

public class PasswordErradaException extends Exception
{
	/**
     * Construtor para objetos da classe Não Existe Contribuinte Exceção.
     */
    public PasswordErradaException(){
        super();
    }
    
    /**
     * Construtor para objetos da classe Não Existe Contribuinte Exceção recebendo uma mensagem.
     */
    public PasswordErradaException(String message){
        super(message);
    }
}
