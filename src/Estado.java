
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
    
    public Contribuinte getContribuinte(int nif) throws NaoExisteContribuinteException {
        Contribuinte resultado = this.contribuintes.get(nif);
        if(resultado == null){
            throw new NaoExisteContribuinteException(Integer.toString(nif));
        }
        return resultado.clone();
    }
    
    public void addContribuinte(Contribuinte contribuinte){
        int nif = contribuinte.getNif();
        this.contribuintes.put(nif, contribuinte);
    }
    
    public boolean existeContribuinte(Contribuinte contribuinte){
        int nif = contribuinte.getNif();
        return this.contribuintes.get(nif) != null;
    }
    
    public Set<Contribuinte> getContribuintes(){
        Set<Contribuinte> resultado = new HashSet<Contribuinte>();
        
        for(Contribuinte contribuinte : this.contribuintes.values()){
            resultado.add(contribuinte);
        }
        
        return resultado;
    }
    
    public SortedSet<Contribuinte> getContribuintes(Comparator c){
        SortedSet<Contribuinte> resultado = new TreeSet<Contribuinte>(c);
        
        for(Contribuinte contribuinte : this.contribuintes.values()){
            resultado.add(contribuinte);
        }
        
        return resultado;
    }
    
    public Set<Fatura> getFaturas(int nif){
        Set<Fatura> resultado = new HashSet<>();
        Set<Fatura> faturas = this.faturas.get(nif);
        
        for(Fatura fatura : faturas){
            resultado.add(fatura.clone());
        }
        
        return resultado;
    }
    
    public SortedSet<Fatura> getFaturas(int nif, Comparator<Fatura> c){
        SortedSet<Fatura> resultado = new TreeSet<>(c);

        Set<Fatura> faturas = this.faturas.get(nif);
        
        for(Fatura fatura : faturas){
            resultado.add(fatura.clone());
        }
        
        return resultado;
    }
    
    public Set<Fatura> getFaturas(int nif, LocalDate inicio, LocalDate fim){
        Set<Fatura> resultado = new HashSet<>();
        Set<Fatura> faturas = this.faturas.get(nif).subSet(new Fatura(inicio), new Fatura(fim));
        
        for(Fatura fatura : faturas){
            resultado.add(fatura.clone());
        }
        
        return resultado;
    }
    
    public Set<Fatura> getFaturasEmComum(int nifEmitente, int nifCliente){
        Set<Fatura> faturas = getFaturas(nifCliente);
        Set<Fatura> resultado = new HashSet<>();
        
        for(Fatura fatura : faturas){
            if(fatura.getNifEmitente() == nifEmitente){
                resultado.add(fatura);
            }
        }
        
        return resultado;
    }
    
    public SortedSet<Fatura> getFaturasEmComum(int nifEmitente, int nifCliente, Comparator c){
        Set<Fatura> faturas = getFaturas(nifCliente);
        SortedSet<Fatura> resultado = new TreeSet<>(c);
        
        for(Fatura fatura : faturas){
            if(fatura.getNifEmitente() == nifEmitente){
                resultado.add(fatura);
            }
        }
        
        return resultado;
    }
    
    public Map<Integer, Set<Fatura>> getFaturasDosContribuintes(int nif){
        Set<Fatura> faturas = getFaturas(nif);
        Map<Integer , Set<Fatura>> resultado = new HashMap<>();
        
        for(Fatura fatura : faturas){
            Set<Fatura> faturasDoContribuinte = resultado.putIfAbsent(fatura.getNifCliente(), new HashSet<>());
            faturasDoContribuinte.add(fatura);
        }
        
        return resultado;
    }
    
    
    public Map<Integer, Set<Fatura>> getFaturasDosContribuintes(int nif, LocalDate inicio, LocalDate fim){
        Set<Fatura> faturas = getFaturas(nif, inicio, fim);
        Map<Integer, Set<Fatura>> resultado = new HashMap<>();
        
        for(Fatura fatura : faturas){
            Set<Fatura> faturasDoContribuinte = resultado.putIfAbsent(fatura.getNifCliente(), new HashSet<>());
            faturasDoContribuinte.add(fatura);
        }
        
        return resultado;
    }
    
    public void addFatura(Fatura fatura){
        Fatura clone = fatura.clone();
        int nifEmitente = fatura.getNifEmitente();
        int nifCliente = fatura.getNifCliente();
        Set<Fatura> faturasEmitente = this.faturas.get(nifEmitente);
        Set<Fatura> faturasCliente = this.faturas.get(nifCliente);
        
        if(faturasEmitente == null){
            faturasEmitente = new TreeSet<Fatura>((a, b) -> a.getDataEmissao().compareTo(b.getDataEmissao()));
        }
        
        if(faturasCliente == null){
            faturasCliente = new TreeSet<Fatura>((a, b) -> a.getDataEmissao().compareTo(b.getDataEmissao()));
        }
        
        faturasEmitente.add(clone);
        faturasCliente.add(clone);
        Coletivo emitente = (Coletivo) this.contribuintes.get(nifEmitente);
        Contribuinte cliente = this.contribuintes.get(nifCliente);
        
        float valor = fatura.getValorTotal();
        
        emitente.addTotalFaturado(valor);
        cliente.addTotalGasto(valor);
    }
    
    public boolean existeFatura(Fatura fatura){
        int nif = fatura.getNifEmitente();
        Set<Fatura> resultado = new HashSet<>();
        Set<Fatura> faturas = this.faturas.get(nif);
        
        return faturas.contains(fatura);
    }
    
    private float calculaDeducaoFaturas(Set<Fatura> faturas){
        float resultado = 0;
        int valorFatura;
        float ativEcoPercen;
        float ativEcoLimite;
        
        for(Fatura fatura : faturas){
            valorFatura = fatura.getValorTotal();
            ativEcoPercen = fatura.getAtivEconomica().getPercentagem();
            ativEcoLimite = fatura.getAtivEconomica().getLimite();
            
            if(valorFatura * ativEcoPercen <= ativEcoLimite){
                resultado += valorFatura * ativEcoPercen;
            }    
        }
        
        return resultado;
    }
    
    public float calculaDeducao(Contribuinte contribuinte){
        int nif = contribuinte.getNif();
        Set<Fatura> faturas = this.faturas.get(nif);
        
        return calculaDeducaoFaturas(faturas);
    }
    
    public float calculaDeducaoAF(Individual contribuinte){
        List<Integer> nifs = new ArrayList<>();
        nifs.add(contribuinte.getNif());
        for(int nif : contribuinte.getAgregadoFamiliar()){
            nifs.add(nif);
        }
        
        Set<Fatura> faturas;
        float resultado = 0;

        for(int nif : nifs){
            faturas = this.faturas.get(nif);
            resultado += calculaDeducaoFaturas(faturas);
        }
        
        return resultado;   
    }
}
