
/**
 * Enumeration class AtivEco - write a description of the enum class here
 *
 * @author (your name here)
 * @version (version number or date here)
 */
public enum AtivEco
{
    Pendente(0, 0, "Pendente"),
    Geral(0, 0, "Geral"),
    Saude(0, 0, "Saude"),
    Educacao(0, 0, "Educaçao"),
    Imoveis(0, 0, "Imoveis"),
    Lares(0, 0, "Lares"),
    Automoveis(0, 0, "Automoveis"),
    Motociclos(0, 0, "Motociclos"),
    AlojametoRestauracao(0, 0, "Alojameto/Restauracao"),
    Beleza(0, 0, "Beleza"),
    Veterinario(0, 0, "Veterinario"),
    Transportes(0, 0, "Transportes");
    
    private float percentagem;
    private float limite;
    private String ativEco;
    
    public String fancyToString(){
        StringBuilder sb = new StringBuilder("");
        sb.append(ativEco);
        return sb.toString();
    }
    
    public String toString(){
        StringBuilder sb = new StringBuilder("Atividade {");
        sb.append("AtivEco: ").append(ativEco).append("; ");
        sb.append("Percentagem: ").append(percentagem).append("; ");
        sb.append("Limite: ").append(limite).append(";");
        sb.append("\n}");
        return sb.toString();
    }
    
    private AtivEco(float percentagem, float limite, String ativEco){
        this.percentagem = percentagem;
        this.limite = limite;
        this.ativEco = ativEco;
    }
    
    public float getPercentagem(){
        return this.percentagem;
    }
    
    public float getLimite(){
        return this.limite;
    }
    
    public String getAtivEco(){
        return this.ativEco;
    }
}
