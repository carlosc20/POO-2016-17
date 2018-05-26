import java.util.Map;
import java.util.HashMap;

public class TabelaIncentivoFiscal
{
    private Map<String, Float> tabela;
    private float valorBase;
    
    public TabelaIncentivoFiscal(){
        this.tabela = new HashMap<>();
        this.valorBase = 0.0f;
    }
    
    public TabelaIncentivoFiscal(Map tabela, float valorBase){
        this.tabela = new HashMap<>(tabela);
        valorBase = 0.0f;
    }
    
    public float getIncentivoFiscal(String nomeDoConcelho){
        Float incentivoFiscal = this.tabela.get(nomeDoConcelho);
        if(incentivoFiscal == null){
            return this.valorBase;
        } else {
            return incentivoFiscal;
        }
    }
    
    public void setIncentivoFiscal(String nomeDoConcelho, float incentivo){
        this.tabela.put(nomeDoConcelho, incentivo);
    }
    
    public void removeIncentivoFiscal(String nomeDoConcelho){
        this.tabela.remove(nomeDoConcelho);
    }
}
