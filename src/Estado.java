
/**
 * Write a description of class Geral here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.SortedSet;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.time.LocalDate;
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

    private Map<Integer, SortedSet<Fatura>> faturas;
    
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
    
    public Contribuinte getContribuinte(int nif){
        return this.contribuintes.get(nif).clone();
    }
    
    public void addContribuinte(Contribuinte contribuinte){
        int nif = contribuinte.getNif();
        this.contribuintes.put(nif, contribuinte);
    }
    
    public boolean existeContribuinte(Contribuinte contribuinte){
        int nif = contribuinte.getNif();
        return this.contribuintes.get(nif) != null;
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
    
    public SortedSet<Fatura> getFaturas(Contribuinte contribuinte, Comparator<Fatura> c){
        int nif = contribuinte.getNif();
        SortedSet<Fatura> resultado = new TreeSet<>(c);
        Set<Fatura> faturas = this.faturas.get(nif);
        
        for(Fatura fatura : faturas){
            resultado.add(fatura.clone());
        }
        
        return resultado;
    }
    
    public Set<Fatura> getFaturas(Contribuinte contribuinte, LocalDate inicio, LocalDate fim){
        int nif = contribuinte.getNif();
        Set<Fatura> resultado = new HashSet<>();
        Set<Fatura> faturas = this.faturas.get(nif).subSet(new Fatura(inicio), new Fatura(fim));
        
        for(Fatura fatura : faturas){
            resultado.add(fatura.clone());
        }
        
        return resultado;
    }
    
    public Map<Integer, Set<Fatura>> getFaturasDosContribuintes(Coletivo coletivo){
        Set<Fatura> faturas = getFaturas(coletivo);
        Map<Integer , Set<Fatura>> resultado = new HashMap<>();
        
        for(Fatura fatura : faturas){
            Set<Fatura> faturasDoContribuinte = resultado.putIfAbsent(fatura.getNifCliente(), new HashSet<>());
            faturasDoContribuinte.add(fatura);
        }
        
        return resultado;
    }
    
    
    public Map<Integer, Set<Fatura>> getFaturasDosContribuintes(Coletivo coletivo, LocalDate inicio, LocalDate fim){
        Set<Fatura> faturas = getFaturas(coletivo, inicio, fim);
        Map<Integer, Set<Fatura>> resultado = new HashMap<>();
        
        for(Fatura fatura : faturas){
            Set<Fatura> faturasDoContribuinte = resultado.putIfAbsent(fatura.getNifCliente(), new HashSet<>());
            faturasDoContribuinte.add(fatura);
        }
        
        return resultado;
    }
    
    public void addFatura(Fatura fatura){
        int nifEmitente = fatura.getEmitente().getNif();
        int nifCliente = fatura.getNifCliente();
        Fatura clone = fatura.clone();
        Set<Fatura> faturasEmitente = this.faturas.get(nifEmitente);
        Set<Fatura> faturasCliente = this.faturas.get(nifCliente);
        
        if(faturasEmitente == null){
            faturasEmitente = new TreeSet<Fatura>();
        }
        
        if(faturasEmitente == null){
            faturasEmitente = new TreeSet<Fatura>();
        }
        
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
