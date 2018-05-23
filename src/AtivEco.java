
/**
 * Enumeration class AtivEco - write a description of the enum class here
 *
 * @author (your name here)
 * @version (version number or date here)
 */
public enum AtivEco
{
    Pendente(0, 0),
    Geral(0.35f, 250f),
    Saude(0.15f, 1000f),
    Educacao(0.3f, 800f),
    Imoveis(0.15f, 502f),
    Lares(0.25f, 403.75f),
    Automoveis(0.3f, 500f),
    Motociclos(0, 0), // não tem
    AlojametoRestauracao(0.15f, 250f),
    Beleza(0.15f, 250f),
    Veterinario(0, 0), // não tem
    Transportes(0, 0); // não tem
    
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
