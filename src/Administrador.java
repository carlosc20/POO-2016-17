
/**
 * Write a description of class Administrador here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.io.Serializable;

public class Administrador extends Contribuinte implements Serializable
{

    /**
     * Constructor for objects of class Administrador
     */
    public Administrador()
    {
        super();
    }

    public Administrador(Administrador admin)
    {
        super(admin);
    }
    
    public Administrador clone(){
        return new Administrador(this);
    }
}
