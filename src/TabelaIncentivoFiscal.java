import java.util.Map;
import java.util.HashMap;

public class TabelaIncentivoFiscal
{
    private Map<String, Float> tabela;
    
    public TabelaIncentivoFiscal(){
        this.tabela = new HashMap<>();
    }
    
    public TabelaIncentivoFiscal(Map tabela, float valorBase){
        this.tabela = new HashMap<>(tabela);
    }
    
    public float getIncentivoFiscal(String nomeDoConcelho){
        Float incentivoFiscal = this.tabela.get(nomeDoConcelho);
        if(incentivoFiscal == null){
            return 0.0f;
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
