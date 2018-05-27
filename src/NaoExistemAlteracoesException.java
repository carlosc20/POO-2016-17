
/**
 * Classe para lançar uma exceção quando não existem alterações
 * 
 * @author Carlos Castro, Daniel Costa, Luís Macedo
 * @version 25/05/2018
 */

public class NaoExistemAlteracoesException extends Exception
{
	/**
     * Construtor para objetos da classe Não Existem Alterações Exceção.
     */
    public NaoExistemAlteracoesException(){
        super();
    }
    
    /**
     * Construtor para objetos da classe Não Existem Alterações Exceção recebendo uma mensagem.
     */
    public NaoExistemAlteracoesException(String message){
        super(message);
    }
}
