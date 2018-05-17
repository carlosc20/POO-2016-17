
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
    private Coletivo emitente;
    private LocalDate dataEmissao;
    private int nifCliente;
    private String descricao;
    private boolean temAtivEco;
    private AtivEco ativEco;
    private int valorTotal;

    public Fatura() {
        this.setId();
        this.setEmitente(new Coletivo());
        this.setDataEmissao(LocalDate.now());
        this.setNifCliente(0);
        this.setDescricao("");
        this.temAtivEco = false;
        this.setValorTotal(0);
    }

    public Fatura(Coletivo emitente, LocalDate dataEmissao, int nifCliente, String descricao, AtivEco ativEco, int valorTotal) {
        this.setId();
        this.setEmitente(emitente);
        this.setDataEmissao(dataEmissao);
        this.setNifCliente(nifCliente);
        this.setDescricao(descricao);
        this.setAtivEconomica(ativEco);
        this.setValorTotal(valorTotal);
    }

    public Fatura(Fatura other) {
        this.id = other.getId();
        this.setEmitente(other.getEmitente());
        this.setDataEmissao(other.getDataEmissao());
        this.setNifCliente(other.getNifCliente());
        this.setDescricao(other.getDescricao());
        try {
            this.setAtivEconomica(other.getAtivEconomica());
        } catch (SemAtivEconomicaException e) {
            this.temAtivEco = false;
        }
        this.setValorTotal(other.getValorTotal());
    }

    private void setId() {
    	totalFaturas++;
    	this.id = totalFaturas;
    }

    public int getId(){
    	return this.id;
    }

    public void setEmitente(Coletivo emitente) {
        this.emitente = emitente.clone();
    }

    public Coletivo getEmitente() {
        return this.emitente.clone();
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
        this.temAtivEco = true;
        this.ativEco = ativEco;
    }

    public AtivEco getAtivEconomica() throws SemAtivEconomicaException{
        if(!this.temAtivEco){
            throw new SemAtivEconomicaException();
        }
        return this.ativEco;
    }
    
    public boolean temAtivEconomica() {
        return this.temAtivEco;
    }

    public void setValorTotal(int valorTotal) {
        this.valorTotal = valorTotal;
    }

    public int getValorTotal() {
        return this.valorTotal;
    }

    public Fatura clone() {
        return new Fatura(this);
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

    public String toString() {
        StringBuilder sb = new StringBuilder("Fatura {");
        sb.append("emitente: ").append(emitente.toString());
        sb.append(",").append("dataEmissao: ").append(dataEmissao.toString());
        sb.append(",").append("nifCliente: ").append(nifCliente);
        sb.append(",").append("descricao: ").append(descricao);
        sb.append(",").append("ativEco: ").append(ativEco.toString());
        sb.append(",").append("valorTotal: ").append(valorTotal);
    	return sb.toString();
    }
}