
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
    
    private Map<Integer, Fatura> faturasId;
    
    private Map<Integer, List<Alteracao>> alteracoes;
    
    private TabelaIncentivoFiscal tabela;
    
    /**
     * Construtor para objetos da classe Contribuinte
     */
    public Estado()
    {
        this.contribuintes = new HashMap<>();
        this.faturas = new HashMap<>();
        this.tabela = new TabelaIncentivoFiscal();
        this.faturasId = new HashMap<>();
    }
    
    /**
     * Guarda o Estado num ficheiro ("estado.obj")
     */
    public void guardaEstado() throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("estado.obj"));
        oos.writeObject(this);
        oos.close();
    }
    
    /**
     * Abre o ficheiro "estado.obj", que contem o estado guardado, e le-o
     * 
     * @return Estado guardado no ficheiro "estado.obj"
     */
    public static Estado leEstado() throws ClassNotFoundException, FileNotFoundException, IOException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("estado.obj"));
        Estado e = (Estado) ois.readObject();
        ois.close();
        return e;
    }
    
    //Fazer documentação
    
    public float getIncentivoFiscal(String nomeDoConcelho){
        return this.tabela.getIncentivoFiscal(nomeDoConcelho);
    }
    
    public void setIncentivoFiscal(String nomeDoConcelho, float incentivo){
        this.tabela.setIncentivoFiscal(nomeDoConcelho, incentivo);
    }
    
    public void removeIncentivoFiscal(String nomeDoConcelho){
        this.tabela.removeIncentivoFiscal(nomeDoConcelho);
    }
    
    /**
     * Obtem as informações do Contribuinte guardadas na Map, usando o NIF
     * 
     * @param NIF Numero de Identificação Fiscal
     * 
     * @return Clone do objeto Contribuite
     */
    public Contribuinte getContribuinte(int nif) throws NaoExisteContribuinteException {
        Contribuinte resultado = this.contribuintes.get(nif);
        if(resultado == null){
            throw new NaoExisteContribuinteException(Integer.toString(nif));
        }
        return resultado.clone();
    }
    
    /**
     * Adiciona um Contribuinte à Map dos contribuintes
     * 
     * @param contribuinte Contribuinte a ser adicionadao
     */
    public void addContribuinte(Contribuinte contribuinte){
        int nif = contribuinte.getNif();
        if(contribuinte instanceof Individual){
            Individual individual = (Individual) contribuinte;
            if(individual.getAgregadoFamiliar().size() > FamiliaNumerosa.minimoDeDependentes()){
                contribuinte = new FamiliaNumerosa(individual);
            }
        } else if(contribuinte instanceof Coletivo){
            Coletivo coletivo = (Coletivo) contribuinte;
            float incentivo = tabela.getIncentivoFiscal(coletivo.getConcelho());
            if(incentivo > 0.0f){
                contribuinte = new EmpresaDeInterior(coletivo, incentivo);
            }
        }
        this.contribuintes.put(nif, contribuinte);
        this.faturas.put(nif, new TreeSet<>());
    }
    
    public void addAlteracao(Alteracao alter){
        int id = alter.getId();
        List<Alteracao> resultado = this.alteracoes.get(id);
        if (resultado == null) {
            resultado = new ArrayList<Alteracao>();
            alteracoes.put(id,resultado);
        }
        resultado.add(alter);
    }
    
    public List<Alteracao> getAlteracoes(int id){
        List<Alteracao> resultado = this.alteracoes.get(id);
        if(resultado == null){
            throw new NaoExistemAlteracoesException(Integer.toString(id));
        }
        return resultado.clone();
    }
    
    /**
     * Testa se um Contribuinte existe na Map dos Contribuintes
     * 
     * @param Contribuinte a ser procurado
     * 
     * @return True se o Contribuite estiver presente dentro da Map dos Contribuintes, False caso contrario
     */
    public boolean existeContribuinte(Contribuinte contribuinte){
        int nif = contribuinte.getNif();
        return this.contribuintes.get(nif) != null;
    }
    
    /**
     * Cria e retorna um Set com os Contribuintes que estao presentes no Map dos Contribuintes
     * 
     * @return Set com os Contribuintes
     */
    public Set<Contribuinte> getContribuintes(){
        Set<Contribuinte> resultado = new HashSet<Contribuinte>();
        
        for(Contribuinte contribuinte : this.contribuintes.values()){
            resultado.add(contribuinte);
        }
        
        return resultado;
    }
    
    /**
     * Cria e retorna um Set com os Contribuintes ordenados atraves de um Comparator que estao presentes no Map dos Contribuintes
     * 
     * @param c Comparator para ordenar os Contribuintes no Set
     * 
     * @return Set com os Contribuintes ordenados
     */
    public SortedSet<Contribuinte> getContribuintes(Comparator c){
        SortedSet<Contribuinte> resultado = new TreeSet<Contribuinte>(c);
        
        for(Contribuinte contribuinte : this.contribuintes.values()){
            resultado.add(contribuinte);
        }
        
        return resultado;
    }
    
    /**
     * Cria e retorna um Set com as faturas de um contribuinte, atraves do NIF desse contribuinte
     * 
     * @param nif NIF do contribuinte
     * 
     * @return Set com as faturas
     */
    public Set<Fatura> getFaturas(int nif) throws NaoExisteContribuinteException {
        Set<Fatura> faturas = this.faturas.get(nif);
        
        if(faturas == null){
            throw new NaoExisteContribuinteException(Integer.toString(nif));
        }
        
        Set<Fatura> resultado = new HashSet<>();
        
        for(Fatura fatura : faturas){
            resultado.add(fatura.clone());
        }
        
        return resultado;
    }
    
    /**
     * Cria e retorna um Set com as faturas de um contribuinte, ordenado atraves de um Comparator
     * 
     * @param nif NIF do contribuinte
     * @param c Comparator para ordenar o Set
     * 
     * @return Set com as faturas
     */
    public SortedSet<Fatura> getFaturas(int nif, Comparator<Fatura> c) throws NaoExisteContribuinteException {
        Set<Fatura> faturas = this.faturas.get(nif);
        
        if(faturas == null){
            throw new NaoExisteContribuinteException(Integer.toString(nif));
        }
        
        SortedSet<Fatura> resultado = new TreeSet<>(c);
            
        for(Fatura fatura : faturas){
            resultado.add(fatura.clone());
        }
        
        return resultado;
    }
    
    /**
     * Cria e retorna um Set com as faturas de um contribuinte, entre um intervalo de tempo
     * 
     * @param nif NIF do contribuinte
     * @param inicio um LocalDate que representa o inicio do intervalo
     * @param fim um LocalDate que representa o fim do intervalo
     * 
     * @return Set com as faturas
     */
     public Set<Fatura> getFaturas(int nif, LocalDate inicio, LocalDate fim) throws NaoExisteContribuinteException {
        SortedSet<Fatura> faturas = this.faturas.get(nif);
        
        if(faturas == null){
            throw new NaoExisteContribuinteException(Integer.toString(nif));
        }
        
        faturas = faturas.subSet(new Fatura(inicio), new Fatura(fim));
        Set<Fatura> resultado = new HashSet<>();
        
        for(Fatura fatura : faturas){
            resultado.add(fatura.clone());
        }
        
        return resultado;
    }
    
    /**
     * Cria e retorna um Set com as faturas em comum entre um emitente e um cliente
     * 
     * @param nifEmitente NIF do emitente
     * @param nifCliente NIF do cliente
     * 
     * @return Set com as faturas
     */
    public Set<Fatura> getFaturasEmComum(int nifEmitente, int nifCliente) throws NaoExisteContribuinteException {
        if(this.contribuintes.get(nifEmitente) == null){
            throw new NaoExisteContribuinteException(Integer.toString(nifEmitente));
        }

        Set<Fatura> faturas = this.faturas.get(nifCliente);
        
        if(faturas == null){
            throw new NaoExisteContribuinteException(Integer.toString(nifCliente));
        }
        
        Set<Fatura> resultado = new HashSet<>();
        
        for(Fatura fatura : faturas){
            if(fatura.getNifEmitente() == nifEmitente){
                resultado.add(fatura.clone());
            }
        }
        
        return resultado;
    }
    
    /**
     * Cria e retorna um Set com as faturas em comum entre um emitente e um cliente, entre um intervalo de tempo
     * 
     * @param nifEmitente NIF do emitente
     * @param nifCliente NIF do cliente
     * @param inicio LocalDate que representa o inicio do intervalo
     * @param fim LocalDate que representa o fim do intervalo
     * 
     * @return Set com as faturas
     */
    public Set<Fatura> getFaturasEmComum(int nifEmitente, int nifCliente, LocalDate inicio, LocalDate fim) throws NaoExisteContribuinteException {
        if(this.contribuintes.get(nifEmitente) == null){
            throw new NaoExisteContribuinteException(Integer.toString(nifEmitente));
        }
        
        SortedSet<Fatura> faturas = this.faturas.get(nifCliente);
        
        if(faturas == null){
            throw new NaoExisteContribuinteException(Integer.toString(nifCliente));
        }

        
        faturas = faturas.subSet(new Fatura(inicio), new Fatura(fim));
        Set<Fatura> resultado = new HashSet<>();
        
        for(Fatura fatura : faturas){
            if(fatura.getNifEmitente() == nifEmitente){
                resultado.add(fatura.clone());
            }
        }
        
        return resultado;
    }
    
    /**
     * Cria e retorna um Set com as faturas em comum entre um emitente e um cliente, ordenado atraves de um comparator
     * 
     * @param nifEmitente NIF do emitente
     * @param nifCliente NIF do cliente
     * @param c Comparator
     * 
     * @return Set com as faturas
     */
    public SortedSet<Fatura> getFaturasEmComum(int nifEmitente, int nifCliente, Comparator<Fatura> c) throws NaoExisteContribuinteException {
        if(this.contribuintes.get(nifEmitente) == null){
            throw new NaoExisteContribuinteException(Integer.toString(nifEmitente));
        }
        
        SortedSet<Fatura> faturas = this.faturas.get(nifCliente);
        
        if(faturas == null){
            throw new NaoExisteContribuinteException(Integer.toString(nifCliente));
        }
        
        SortedSet<Fatura> resultado = new TreeSet<>(c);
        
        for(Fatura fatura : faturas){
            if(fatura.getNifEmitente() == nifEmitente){
                resultado.add(fatura);
            }
        }
        
        return resultado;
    }
    
    public Map<Integer, Set<Fatura>> getFaturasDosContribuintes(int nif) throws NaoExisteContribuinteException {
        Set<Fatura> faturas = getFaturas(nif);
        
        if(faturas == null){
            throw new NaoExisteContribuinteException(Integer.toString(nif));
        }
        
        Map<Integer , Set<Fatura>> resultado = new HashMap<>();
        
        for(Fatura fatura : faturas){
            Set<Fatura> faturasDoContribuinte = resultado.putIfAbsent(fatura.getNifCliente(), new HashSet<>());
            faturasDoContribuinte.add(fatura);
        }
        
        return resultado;
    }
    
    public Map<Integer, Set<Fatura>> getFaturasDosContribuintes(int nif, LocalDate inicio, LocalDate fim) throws NaoExisteContribuinteException {
        Set<Fatura> faturas = getFaturas(nif, inicio, fim);
        
        if(faturas == null){
            throw new NaoExisteContribuinteException(Integer.toString(nif));
        }
        
        Map<Integer, Set<Fatura>> resultado = new HashMap<>();
        
        for(Fatura fatura : faturas){
            Set<Fatura> faturasDoContribuinte = resultado.putIfAbsent(fatura.getNifCliente(), new HashSet<>());
            faturasDoContribuinte.add(fatura);
        }
        
        return resultado;
    }
    
    /**
     * Adiciona uma Fatura ao Set das Faturas do Emitente e ao Set das Faturas do Cliente e incremente ao total faturado do Emitente e do Cliente
     * 
     * @param fatura Fatura a ser adicionada
     */
    public void addFatura(Fatura fatura){
        Fatura clone = fatura.clone();
        int nifEmitente = fatura.getNifEmitente();
        int nifCliente = fatura.getNifCliente();
        int id = fatura.getId();
        Set<Fatura> faturasEmitente = this.faturas.get(nifEmitente);
        Set<Fatura> faturasCliente = this.faturas.get(nifCliente);
        
        if(faturasEmitente == null){
            faturasEmitente = new TreeSet<Fatura>();
        }
        
        if(faturasCliente == null){
            faturasCliente = new TreeSet<Fatura>();
        }
        
        faturasEmitente.add(clone);
        faturasCliente.add(clone);
        this.faturasId.put(id, clone);
        
        Coletivo emitente = (Coletivo) this.contribuintes.get(nifEmitente);
        Contribuinte cliente = this.contribuintes.get(nifCliente);
        
        float valor = fatura.getValorTotal();
        
        emitente.addTotalFaturado(valor);
        cliente.addTotalGasto(valor);
    }
    
    /**
     * Retorna uma fatura dando o seu id
     * 
     * @param id    id da fatura
     * 
     * @return Fatura com esse id, null se não encontrar
     */
    public Fatura getFatura(int id){
        Fatura fatura = this.faturasId.get(id);
        if(fatura != null){
            fatura = fatura.clone();
        }
        return fatura;
    }
    
    /**
     * Calcula o total faturado num intervalo de tempo
     * 
     * @param nif       Número de contribuinte
     * @param inicio    Data de início
     * @param fim       Data de fim
     * 
     * @return True se a Fatura existir no Set das Faturas do Emitente
     */
    public float totalFaturado(int nif, LocalDate inicio, LocalDate fim) throws NaoExisteContribuinteException {
        SortedSet<Fatura> faturas = this.faturas.get(nif);
        
        if(faturas == null){
            throw new NaoExisteContribuinteException(Integer.toString(nif));
        }
        
        faturas = faturas.subSet(new Fatura(inicio), new Fatura(fim));
        
        float resultado = 0.0f;
        for(Fatura fatura : faturas){
            resultado += fatura.getValorTotal();
        }
        
        return resultado;
    }
    
    /**
     * Calcula a deduçao das Faturas, atraves da atividade Economica
     * 
     * @param faturas Set com as faturas a ser deduzidas
     * 
     * @return Soma das deduçoes de todas as Faturas do Set
     */
    private float calculaDeducaoFaturas(Set<Fatura> faturas){
        float resultado = 0;
        float valorFatura;
        float ativEcoPercen;
        float ativEcoLimite;
        
        if(faturas == null){
            return 0;
        }
        
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
    
    /**
     * Calcula a deduçao de um Contribuinte atraves das suas Faturas
     * 
     * @param contribuinte Contribuinte
     * 
     * @return Soma das deduçoes de todas as Faturas do Set 
     */
    public float calculaDeducao(Contribuinte contribuinte){
        int nif = contribuinte.getNif();
        Set<Fatura> faturas = this.faturas.get(nif);
        
        return calculaDeducaoFaturas(faturas);
    }
    
    /**
     * Calcula a deduçao de um Contribuinte e do seu Agregado Familiar atraves das suas Faturas
     * 
     * @param contribuinte Individual
     * 
     * @return Soma das deduçoes de todas as Faturas do Agregado Familiar
     */
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
