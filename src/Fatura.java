
/**
 * Write a description of class Fatura here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.time.LocalDate;

public class Fatura
{
    // instance variables - replace the example below with your own
    private int nifEmitente;
    private String designacaoEmitente;
    private LocalDate data;
    private int nifCliente;
    private String descricao;
    private String atividadeEconomica; //enum? codigo int?
    private int valor; //em centimos

    /**
     * Constructor for objects of class Fatura
     */
    public Fatura()
    {
        // initialise instance variables
        x = 0;
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public int calcularDeducao(int y)
    {
        // put your code here
        return x + y;
    }
}
