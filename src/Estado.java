
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
    
    public List<Fatura> getFaturas(Contribuinte contribuinte, Comparator<Fatura> c){
        int nif = contribuinte.getNif();
        List<Fatura> resultado = new ArrayList<>();
        Set<Fatura> faturas = this.faturas.get(nif);
        
        for(Fatura fatura : faturas){
            resultado.add(fatura.clone());
        }
        
        resultado.sort(c);
        return resultado;
    }
    
    public Set<Fatura> getFaturas(Contribuinte contribuinte, LocalDate inicio, LocalDate fim){
        int nif = contribuinte.getNif();
        Set<Fatura> resultado = new HashSet<>();
        Set<Fatura> faturas = this.faturas.get(nif);
        
        for(Fatura fatura : faturas){
            if(fatura.getDataEmissao().compareTo(inicio) >= 0 && fatura.getDataEmissao().compareTo(fim) <= 0){
                resultado.add(fatura.clone());
            }
        }
        
        return resultado;
    }
    
    public Map<Contribuinte, Set<Fatura>> getContribuintes(Coletivo coletivo){
        int nif = coletivo.getNif();
        Map<Contribuinte, Set<Fatura>> resultado = new HashMap<>();
        Set<Fatura> faturas = this.faturas.get(nif);
        
        for(Fatura fatura : faturas){
            Contribuinte clone = this.contribuintes.get(fatura.getNifCliente()).clone();
            Set<Fatura> faturasDoContribuinte = resultado.putIfAbsent(this.contribuintes.get(clone), new HashSet<>());
            if(faturasDoContribuinte != null){
                faturasDoContribuinte.add(fatura);
            }
        }
        
        return resultado;
    }
    
    public Map<Contribuinte, List<Fatura>> getContribuintes(Coletivo coletivo, Comparator<Fatura> c){
        int nif = coletivo.getNif();
        Map<Contribuinte, List<Fatura>> resultado = new HashMap<>();
        Set<Fatura> faturas = this.faturas.get(nif);
        
        for(Fatura fatura : faturas){
            Contribuinte clone = this.contribuintes.get(fatura.getNifCliente()).clone();
            List<Fatura> faturasDoContribuinte = resultado.putIfAbsent(this.contribuintes.get(clone), new ArrayList<>());
            if(faturasDoContribuinte != null){
                faturasDoContribuinte.add(fatura);
            }
        }
        
        for(List list : resultado.values()){
            list.sort(c);
        }
        
        return resultado;
    }
    
    public Map<Contribuinte, Set<Fatura>> getContribuintes(Coletivo coletivo, LocalDate inicio, LocalDate fim){
        int nif = coletivo.getNif();
        Map<Contribuinte, Set<Fatura>> resultado = new HashMap<>();
        Set<Fatura> faturas = this.faturas.get(nif);
        
        for(Fatura fatura : faturas){
            if(fatura.getDataEmissao().compareTo(inicio) >= 0 && fatura.getDataEmissao().compareTo(fim) <= 0){
                Contribuinte clone = this.contribuintes.get(fatura.getNifCliente()).clone();
                Set<Fatura> faturasDoContribuinte = resultado.putIfAbsent(this.contribuintes.get(clone), new HashSet<>());
                if(faturasDoContribuinte != null){
                    faturasDoContribuinte.add(fatura);
                }
            }
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
