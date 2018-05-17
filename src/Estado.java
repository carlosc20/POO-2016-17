
/**
 * Write a description of class Geral here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Estado implements Serializable
{

    private Map<Integer, Contribuinte> contribuintes;

    private Map<Integer, List<Fatura>> faturas;
    
    public Estado()
    {
        this.contribuintes = new HashMap<>();
        this.faturas = new HashMap<>();
    }

    public Map<Integer, Contribuinte> getContribuintes() {
        return this.contribuintes; //Precisa de fazer clone
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
    
    public List<Fatura> getFaturas(Contribuinte contribuinte){
        int nif = contribuinte.getNif();
        List<Fatura> resultado = new ArrayList<>();
        List<Fatura> faturas = this.faturas.get(nif);
        for(Fatura fatura : faturas){
            resultado.add(fatura.clone());
        }
        return resultado;
    }
    
    public void addFatura(Contribuinte contribuinte, Fatura fatura){
        int nif = contribuinte.getNif();
        List<Fatura> faturas = this.faturas.get(nif);
        faturas.add(fatura.clone());
    }

    public void updateFatura(Contribuinte contribuinte, Fatura fatura){
        int nif = contribuinte.getNif();
        List<Fatura> faturas = this.faturas.get(nif);
        if(faturas != null){
            int i = 0;
            for(Fatura item : faturas){
                if(fatura.equals(item)){
                    faturas.set(i, fatura);
                }
                i++;
            }
        }
    }
}
