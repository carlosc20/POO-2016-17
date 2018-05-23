
/**
 * Enumeration class AtivEco - write a description of the enum class here
 *
 * @author (your name here)
 * @version (version number or date here)
 */
public enum AtivEco
{
    Pendente(0, 0),
    Geral(0, 0),
    Saude(0, 0),
    Educacao(0, 0),
    Imoveis(0, 0),
    Lares(0, 0),
    Automoveis(0, 0),
    Motociclos(0, 0),
    AlojametoRestauracao(0, 0),
    Beleza(0, 0),
    Veterinario(0, 0),
    Transportes(0, 0);
    
    private float percentagem;
    private float limite;
    
    private AtivEco(float percentagem, float limite){
        this.percentagem = percentagem;
        this.limite = limite;
    }
    
    public float getPercentagem(){
        return this.percentagem;
    }
    
    public float getLimite(){
        return this.limite;
    }
}
