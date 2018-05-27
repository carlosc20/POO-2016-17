
/**
 * Write a description of class Fatura here.
 *
 * @author Carlos Castro, Daniel Costa, Luís Macedo
 * @version 17/05/2018
 */

import java.lang.Comparable;
import java.time.LocalDate;

public class Fatura implements Comparable<Fatura> {
    private static int totalFaturas;
    
    private int id;
    private int nifEmitente;
    private LocalDate dataEmissao;
    private int nifCliente;
    private String descricao;
    private AtivEco ativEconomica;
    private int valorTotal;
    
    /**
     * Construtor para objetos da classe Fatura
     * Atribui um id único à fatura
     */
    public Fatura() {
        this.setId();
        this.setNifEmitente(0);
        this.setDataEmissao(LocalDate.now());
        this.setNifCliente(0);
        this.setDescricao("");
        this.setAtivEconomica(ativEconomica.Pendente);
        this.setValorTotal(0);
    }
    
    /**
     * Construtor para objetos da classe Fatura recebendo a data de emissão
     * Esta classe deve ser utilizada para fazer um subSet de faturas num intervalo de tempo
     * 
     * @param  dataEmissao                Data da emissão da fatura
     */
    public Fatura(LocalDate dataEmissao){
        this.id = -1;
        this.setNifEmitente(0);
        this.setDataEmissao(dataEmissao);
        this.setNifCliente(0);
        this.setDescricao("");
        this.setAtivEconomica(ativEconomica.Pendente);
        this.setValorTotal(0);
    }
    
    /**
     * Construtor para objetos da classe Fatura recebendo valores iniciais
     * Atribui um id único à fatura
     * 
     * @param  emitente          NIF da empresa que emite a fatura
     * @param  dataEmisao        Data da emissão da fatura
     * @param  nifCliente        NIF do cliente que emite a fatura
     * @param  descricao         Descrição da fatura
     * @param  ativEco           Atividade económica da fatura
     * @param  valorTotal        Valor da fatura
     */
    public Fatura(int emitente, LocalDate dataEmissao, int nifCliente, String descricao, AtivEco ativEco, int valorTotal) {
        this.setId();
        this.setNifEmitente(emitente);
        this.setDataEmissao(dataEmissao);
        this.setNifCliente(nifCliente);
        this.setDescricao(descricao);
        this.setAtivEconomica(ativEco);
        this.setValorTotal(valorTotal);
    }

    /**
     * Construtor para objetos da classe Fatura copiando outra fatura
     * Atribui um id único à fatura, não copia o id
     */
    public Fatura(Fatura other) {
        this.setId();
        this.setNifEmitente(other.getNifEmitente());
        this.setDataEmissao(other.getDataEmissao());
        this.setNifCliente(other.getNifCliente());
        this.setDescricao(other.getDescricao());
        this.setAtivEconomica(other.getAtivEconomica());
        this.setValorTotal(other.getValorTotal());
    }

    /**
     * Construtor para objetos da classe Fatura copiando outra fatura
     * Atribui o id passado como parâmetro
     */
    private Fatura(Fatura other, int id) {
        this.setId(id);
        this.setNifEmitente(other.getNifEmitente());
        this.setDataEmissao(other.getDataEmissao());
        this.setNifCliente(other.getNifCliente());
        this.setDescricao(other.getDescricao());
        this.setAtivEconomica(other.getAtivEconomica());
        this.setValorTotal(other.getValorTotal());
    }

    /**
     * Atribui um id único
     */
    private void setId() {
        totalFaturas++;
        this.id = totalFaturas;
    }

    /**
     * Atribui um id passado como parâmetro
     * @param id        Novo id da fatura
     */
    private void setId(int id) {
        this.id = id;
    }

    /**
     * Retorna o id da fatura
     * @return Id da fatura
     */
    public int getId(){
        return this.id;
    }

    /**
     * Atribui o NIF do emitente à fatura
     * @param nif       Novo nif da fatura
     */
    public void setNifEmitente(int nif) {
        this.nifEmitente = nif;
    }

    /**
     * Retorna o NIF do emitente da fatura
     * @return NIF do emitente da fatura
     */
    public int getNifEmitente() {
        return this.nifEmitente;
    }

    /**
     * Atribui uma nova data de emissão à fatura
     * @param dataEmissao       Nova data de emissão
     */
    public void setDataEmissao(LocalDate dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    /**
     * Retorna a data de emissão da fatura
     * @return Data de emissão da fatura
     */
    public LocalDate getDataEmissao() {
        return this.dataEmissao;
    }

    /**
     * Atribui um novo NIF do cliente
     * @param nifCliente        Novo NIF do cliente
     */
    public void setNifCliente(int nifCliente) {
        this.nifCliente = nifCliente;
    }

    /**
     * Retorna o NIF do cliente da fatura
     * @return NIF do cliente da fatura
     */
    public int getNifCliente() {
        return this.nifCliente;
    }

    /**
     * Atribui uma nova descrição
     * @param descricao         Nova descrição
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Retorna a descrição
     * @return Descrição da fatura
     */
    public String getDescricao() {
        return this.descricao;
    }

    /**
     * Atribui uma nova atividade económica
     * @param ativEco       Nova atividade económica
     */
    public void setAtivEconomica(AtivEco ativEco) {
        this.ativEconomica = ativEco;
    }

    /**
     * Retorna a atividade económica da fatura
     * @return Atividade económica da fatura
     */
    public AtivEco getAtivEconomica(){
        return this.ativEconomica;
    }
    
    /**
     * Verifica se a fatura tem uma atividade económica
     * @return 'true' se tiver atividade económica, 'false' caso contrário
     */
    public boolean temAtivEconomica() {
        return this.ativEconomica != AtivEco.Pendente;
    }

    /**
     * Atribui um novo valor total da fatura
     * @param valorTotal        Novo valor da fatura
     */
    public void setValorTotal(int valorTotal) {
        this.valorTotal = valorTotal;
    }

    /**
     * Retorna o valor total da fatura
     * @return Valor total da fatura
     */
    public int getValorTotal() {
        return this.valorTotal;
    }
    
    /**
     * Copia a fatura
     */
    public Fatura clone() {
        return new Fatura(this, this.getId());
    }

    /**
     * Verifica se as faturas têm o mesmo id
     * @return 'true' se for a mesma fatura, 'false' caso contrário
     */
    public boolean equals(Object other) {
        if(other == this) {
            return true;
        }
        if(other == null || other.getClass() != this.getClass()) {
            return false;
        }
        Fatura otherFatura = (Fatura) other;
        if(this.getId() != otherFatura.getId()){
            return false;
        }
        return true;
    }
    
    public int hashCode(){
        return this.getId();
    }
    
    public int compareTo(Fatura o){
        return this.dataEmissao.compareTo(o.getDataEmissao());
    }

    public String fancyToString(){
        StringBuilder sb = new StringBuilder("");
        sb.append("Id: ").append(this.id).append("; ");
        sb.append("Atividade Economica: ").append(this.ativEconomica.fancyToString()).append(";\n");
        sb.append("NIF Cliente: ").append(this.nifCliente).append("; ");
        sb.append("Emitente: ").append(this.nifEmitente).append("; ");
        sb.append("Data Emissao: ").append(this.dataEmissao.toString()).append(";\n");
        sb.append("Valor Total: ").append(this.valorTotal).append("; ");
        sb.append("Descricao: ").append(this.descricao).append(";\n");
        return sb.toString();
    }
   
    public String toString(){
        StringBuilder sb = new StringBuilder("Fatura {");
        sb.append("Id:").append(this.id).append("\n");
        sb.append("Emitente: ").append(this.nifEmitente).append("\n");
        sb.append("Data Emissao: ").append(this.dataEmissao.toString()).append("\n");
        sb.append("NIF Cliente: ").append(this.nifCliente).append("\n");
        sb.append("Descricao: ").append(this.descricao).append("\n");
        sb.append("AtivEco: ").append(this.ativEconomica.toString()).append("\n");
        sb.append("ValorTotal: ").append(this.valorTotal).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}