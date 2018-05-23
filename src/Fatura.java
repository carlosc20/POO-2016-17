
/**
 * Write a description of class Fatura here.
 *
 * @author Carlos Castro, Daniel Costa, Luís Macedo
 * @version 17/05/2018
 */

import java.time.LocalDate;

public class Fatura {
    private static int totalFaturas;
    
    private int id;
    private int nifEmitente;
    private LocalDate dataEmissao;
    private int nifCliente;
    private String descricao;
    private AtivEco ativEco;
    private int valorTotal;

    public Fatura() {
        this.setId();
        this.setNifEmitente(0);
        this.setDataEmissao(LocalDate.now());
        this.setNifCliente(0);
        this.setDescricao("");
        this.setAtivEconomica(ativEco.Pendente);
        this.setValorTotal(0);
    }
    
    public Fatura(LocalDate dataEmissao){
        this.id = -1;
        this.setNifEmitente(0);
        this.setDataEmissao(LocalDate.now());
        this.setNifCliente(0);
        this.setDescricao("");
        this.setAtivEconomica(ativEco.Pendente);
        this.setValorTotal(0);
    }

    public Fatura(int emitente, int nifCliente){
        this.setId();
        this.setNifEmitente(emitente);
        this.setDataEmissao(LocalDate.now());
        this.setNifCliente(nifCliente);
        this.setDescricao("");
        this.setAtivEconomica(ativEco.Pendente);
        this.setValorTotal(0);
    }
    
    public Fatura(int emitente, LocalDate dataEmissao, int nifCliente, String descricao, AtivEco ativEco, int valorTotal) {
        this.setId();
        this.setNifEmitente(emitente);
        this.setDataEmissao(dataEmissao);
        this.setNifCliente(nifCliente);
        this.setDescricao(descricao);
        this.setAtivEconomica(ativEco);
        this.setValorTotal(valorTotal);
    }

    public Fatura(Fatura other) {
        this.setId();
        this.setNifEmitente(other.getNifEmitente());
        this.setDataEmissao(other.getDataEmissao());
        this.setNifCliente(other.getNifCliente());
        this.setDescricao(other.getDescricao());
        this.setAtivEconomica(other.getAtivEconomica());
        this.setValorTotal(other.getValorTotal());
    }

    private Fatura(Fatura other, int id) {
        this.id = id;
        this.setNifEmitente(other.getNifEmitente());
        this.setDataEmissao(other.getDataEmissao());
        this.setNifCliente(other.getNifCliente());
        this.setDescricao(other.getDescricao());
        this.setAtivEconomica(other.getAtivEconomica());
        this.setValorTotal(other.getValorTotal());
    }

    private void setId() {
        totalFaturas++;
        this.id = totalFaturas;
    }

    private void setId(int id) {
        this.id = id;
    }

    public int getId(){
        return this.id;
    }

    public void setNifEmitente(int nif) {
        this.nifEmitente = nif;
    }

    public int getNifEmitente() {
        return this.nifEmitente;
    }

    public void setDataEmissao(LocalDate dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public LocalDate getDataEmissao() {
        return this.dataEmissao;
    }

    public void setNifCliente(int nifCliente) {
        this.nifCliente = nifCliente;
    }

    public int getNifCliente() {
        return this.nifCliente;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setAtivEconomica(AtivEco ativEco) {
        this.ativEco = ativEco;
    }

    public AtivEco getAtivEconomica(){
        return this.ativEco;
    }
    
    public boolean temAtivEconomica() {
        return this.ativEco != ativEco.Pendente;
    }

    public void setValorTotal(int valorTotal) {
        this.valorTotal = valorTotal;
    }

    public int getValorTotal() {
        return this.valorTotal;
    }
    
    public Fatura clone() {
        return new Fatura(this, this.getId());
    }

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

    public String toString() {
        StringBuilder sb = new StringBuilder("Fatura {");
        sb.append("id:").append(this.id);
        sb.append(",").append("emitente: ").append(this.nifEmitente);
        sb.append(",").append("dataEmissao: ").append(this.dataEmissao.toString());
        sb.append(",").append("nifCliente: ").append(this.nifCliente);
        sb.append(",").append("descricao: ").append(this.descricao);
        sb.append(",").append("ativEco: ").append(this.ativEco.toString());
        sb.append(",").append("valorTotal: ").append(this.valorTotal);
        return sb.toString();
    }
}