
/**
 * Write a description of class EmpresaDeInterior here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class EmpresaDeInterior extends Coletivo implements CasoEspecial
{
    private float incentivo;
    /**
     * Constructor da classe EmpresaDeInterior recebendo um Coletivo
     * 
     * @param coletivo      Empresa
     * @param incentivo     Percentual de incentivo
     * 
     */
    public EmpresaDeInterior(Coletivo coletivo, float incentivo)
    {
        super(coletivo);
        this.incentivo = incentivo;
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public double reducaoImposto(){

        return 0;
    }
    
}
