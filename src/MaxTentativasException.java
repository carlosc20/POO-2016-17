/**
 * Classe para lançar uma exceção quando se atinge o máximo de tentativas de login
 * 
 * @author Carlos Castro, Daniel Costa, Luís Macedo
 * @version 26/05/2018
 */

public class MaxTentativasException extends Exception
{
	/**
     * Construtor para objetos da classe Não Existe Contribuinte Exceção.
     */
    public MaxTentativasException(){
        super();
    }
    
    /**
     * Construtor para objetos da classe Não Existe Contribuinte Exceção recebendo uma mensagem.
     */
    public MaxTentativasException(String message){
        super(message);
    }
}
