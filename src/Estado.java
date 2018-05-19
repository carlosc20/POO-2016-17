
/**
 * Write a description of class Geral here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
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

    private Map<Integer, Set<Fatura>> faturas;
    
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
    
    public Set<Fatura> getFaturas(Contribuinte contribuinte){
        int nif = contribuinte.getNif();
        Set<Fatura> resultado = new HashSet<>();
        Set<Fatura> faturas = this.faturas.get(nif);
        for(Fatura fatura : faturas){
            resultado.add(fatura.clone());
        }
        return resultado;
    }
    
    public void addFatura(Fatura fatura){
        int nifEmitente = fatura.getEmitente().getNif();
        int nifCliente = fatura.getNifCliente();
        Fatura clone = fatura.clone();
        Set<Fatura> faturasEmitente = this.faturas.get(nifEmitente);
        Set<Fatura> faturasCliente = this.faturas.get(nifCliente);
        faturasEmitente.add(clone);
        faturasCliente.add(clone);
    }
    
    public boolean existeFatura(Fatura fatura){
        int nif = fatura.getEmitente().getNif();
        Set<Fatura> resultado = new HashSet<>();
        Set<Fatura> faturas = this.faturas.get(nif);
        return faturas.contains(fatura);
    }
}
