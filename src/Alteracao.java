
import java.time.LocalDate;
/**
 * Classe que guarda as informações de alterações faitas a uma fatura
 * A única alteração que pode ser feita é na atividade económica
 */
public class Alteracao
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
    
    public Alteracao clone(Alteracao alteracao){
        return new Alteracao(this);
    }
}
