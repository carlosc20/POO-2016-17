
import java.util.Map;
import java.util.HashMap;

public class AtividadeEconomica
{
    private static Map<String, AtividadeEconomica> map;
    
    private String nomeAtividadeEconomica;
    private float percentagem;
    private float limite;
    
    public AtividadeEconomica(String nomeAtividadeEconomica, float percentagem, float limite){
        this.nomeAtividadeEconomica = nomeAtividadeEconomica;
        this.percentagem = percentagem;
        this.limite = limite;
        map.put(nomeAtividadeEconomica, this);
    }
    
    public AtividadeEconomica(AtividadeEconomica atividade){
        this.nomeAtividadeEconomica = atividade.getNomeAtividadeEconomica();
        this.percentagem = atividade.getPercentagem();
        this.limite = atividade.getLimite();
    }
    
    public static AtividadeEconomica getAtividade(String nomeAtividadeEconomica){
        return map.get(nomeAtividadeEconomica).clone();
    }
    
    public String getNomeAtividadeEconomica(){
        return this.nomeAtividadeEconomica;
    }
    
    public float getPercentagem(){
        return this.percentagem;
    }
    
    public float getLimite(){
        return this.limite;
    }
    
    public AtividadeEconomica clone(){
        return new AtividadeEconomica(this);
    }
    
    
}