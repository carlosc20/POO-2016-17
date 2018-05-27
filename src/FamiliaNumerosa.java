
/**
 * Write a description of class FamiliaNumerosa here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.io.Serializable;

public class FamiliaNumerosa extends Individual implements CasoEspecial, Serializable
{
    private static int minimo = 4;
    private float bonificacao;
    
    /**
     * Constructor for objects of class FamiliaNumerosa recendo um Individual
     * 
     * @param individual    Contribuinte com mais filhos que o mínimo
     * 
     */
    public FamiliaNumerosa(Individual individual)
    {
        super(individual);
        bonificacao = 0.05f * individual.getAgregadoFamiliar().size();
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public double reducaoImposto(){

        return 1.2;
    }
    
    public static int minimoDeDependentes(){
        return minimo;
    }
}
