
/**
 * Write a description of class Geral here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.Map;
import java.io.Serializable;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Estado implements Serializable
{
    // instance variables - replace the example below with your own
    private Map<Integer, Contribuinte> contribs;

    /**
     * Constructor for objects of class Geral
     */
    public Estado()
    {

    }

    public void guardaEstado() {
        try{
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("hoteis.obj"));
            oos.writeObject(this);
            oos.close();
        }
        catch(FileNotFoundException e){
            System.out.print(e.getMessage());
        }
        catch(IOException e){
            System.out.print(e.getMessage());
        }
    }
    
    public void guardaEstado2() throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("hoteis.obj"));
        oos.writeObject(this);
        oos.close();
    }
    
    public static Estado leEstado() throws ClassNotFoundException, FileNotFoundException, IOException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("hoteis.obj"));
        Estado e = (Estado) ois.readObject();
        ois.close();
        return e;
    }
}
