
/**
 * Enumeration class AtivEco - write a description of the enum class here
 *
 * @author (your name here)
 * @version (version number or date here)
 */
public enum AtivEco
{
    Pendente(0, 0, "Pendente"),
    Geral(0.35f, 250f, "Geral"),
    Saude(0.15f, 1000f, "Saude"),
    Educacao(0.3f, 800f, "Educaçao"),
    Imoveis(0.15f, 502f, "Imoveis"),
    Lares(0.25f, 403.75f, "Lares"),
    Automoveis(0.3f, 500f, "Automoveis"),
    Motociclos(0, 0, "Motociclos"), // não tem
    AlojametoRestauracao(0.15f, 250f, "Alojameto/Restauracao"),
    Beleza(0.15f, 250f, "Beleza"),
    Veterinario(0, 0, "Veterinario"), // não tem
    Transportes(0, 0, "Transportes"); // não tem
    
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
