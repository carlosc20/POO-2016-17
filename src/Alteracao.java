
import java.time.LocalDate;
import java.io.Serializable;
/**
 * Classe que guarda as informações de alterações faitas a uma fatura
 * A única alteração que pode ser feita é na atividade económica
 */
public class Alteracao implements Serializable
{
    private int fatura;
    private AtivEco nova;
    private AtivEco antiga;
    private LocalDate data;
    
    public Alteracao(Alteracao outro) {
        fatura = outro.getFatura(); 
        nova = outro.getNova();
        antiga = outro.getAntiga();
        data = outro.getData();
    }
    
    public Alteracao(int id, AtivEco nova, AtivEco antiga, LocalDate data) {
        fatura = id;
        nova = nova;
        antiga = antiga;
        data = data;
    }
    
    public Alteracao clone(){
        return new Alteracao(this);
    }
    
    /**
     * Retorna a data da alteração
     * 
     * @return data da alteraçao
     */
    public LocalDate getData(){
        return this.data;
    }
    
    /**
     * Retorna o id da Fatura que foi alterada
     * 
     * @return Id da Fatura
     */
    public int getFatura(){
        return this.fatura;
    }
    
        /**
     * Retorna a Atividade economica nova
     * 
     * @return Atividade Economica nova
     */
    public AtivEco getNova(){
        return this.nova;
    }
    
    /**
     * Retorna a Atividade economica antiga
     * 
     * @return Atividade Economica antiga
     */
    public AtivEco getAntiga(){
        return this.antiga;
    }
    
    /**
     * Retorna uma String com a informação das Alteraçoes feitas num fatura, nomeadamente na atividade economica
     * 
     * @return String com as informações a serem apresentadas a um user
     */
    public String fancyToString(){
        StringBuilder sb = new StringBuilder("");
        sb.append("Id da Fatura: ").append(fatura).append(";");
        sb.append("Atividade Economica antiga: ").append(antiga.fancyToString()).append(";");
        sb.append("Atividade Economica nova: ").append(nova.fancyToString()).append(";");
        sb.append("Data: ").append(data.toString()).append(";");
        return sb.toString();
    }
    
    public String toString(){
        StringBuilder sb = new StringBuilder("Alteraçao{");
        sb.append("Id da Fatura: ").append(fatura).append(";\n");
        sb.append("Atividade Economica antiga: ").append(antiga.toString()).append(";\n");
        sb.append("Atividade Economica nova: ").append(nova.toString()).append(";\n");
        sb.append("Data: ").append(data.toString()).append(";\n");
        sb.append("}\n");
        return sb.toString();
    }
}
