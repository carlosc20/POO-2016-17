
/**
 * Write a description of class Geral here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.Map;
import java.util.List;
import java.io.Serializable;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Estado implements Serializable
{

    private Map<Integer, Contribuinte> contribs;

    private Map<Integer, List<Fatura>> faturasInd;
    private Map<Integer, List<Fatura>> faturasCol;
    
    public Estado()
    {

    }

    public Map<Integer, Contribuinte> getContribs() {
        return contribs;
    }
    

    
    public void guardaEstado() throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("estado.obj"));
        oos.writeObject(this);
        oos.close();
    }
    
    public static Estado leEstado() throws ClassNotFoundException, FileNotFoundException, IOException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("estado.obj"));
        Estado e = (Estado) ois.readObject();
        ois.close();
        return e;
    }
}
