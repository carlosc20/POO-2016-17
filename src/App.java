
/**
 * Write a description of class Portal here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.HashSet;
import java.util.Comparator;
import java.util.Iterator;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.ClassNotFoundException;
import java.io.File;
import java.util.HashMap;
import java.time.LocalDate;
import java.util.Set;
import java.util.SortedSet;
import java.util.stream.Collectors;

public class App
{
    private static Estado estado;
     
    /*Calcualr dedução
     * varia com ativ eco(algumas nao permitem)
     * varia com a empresa
     * varia com o agregado familiar (pdoe ter varios fatores)
     
    */

    
    public App() {
        try {
            estado = Estado.leEstado();
        }
        catch(Exception e){
            estado = new Estado();
        }
    }
    
    public App(Estado e) {
        estado = e;
    }
    
    interface Opcao {
        void escolher();
    }
    
    
    public static void main(String[] args) {
        
        try {
            estado = Estado.leEstado();
        }
        catch(Exception e){
            estado = new Estado();
        }
        
        Scanner s = new Scanner(System.in);
        menuInicial(s);
        s.close();
        
        try {
            estado.guardaEstado();
        } 
        catch(IOException e){
            System.out.print(e.getMessage());
        }
    }
    
    public void run(){
        
        Scanner s = new Scanner(System.in);
        menuInicial(s);
        s.close();
        
    }
    
    /*
    private static Contribuinte login(Scanner s){

        System.out.println("Nº de Contribuinte:");
        int nif = scanNif(s);
       
        System.out.println("Senha de acesso:");
        String passwd = s.nextLine();
        
        
        try{
            Contribuinte user = estado.getContribuinte(nif);
            if (user != null && user.getPassword() == passwd) 
                return user;
            else
                System.out.println("Senha de acesso errada");
        }
        catch(NaoExisteContribuinteException e ){
            System.out.println("Não existe esse Nº de Contribuinte");
        }
        
        return null;
        
    }
    */
    
    private static void menu(Scanner s, Opcao[] opcoes, String[] desc){
        
        int op = 0;

        do {
            for(int i = 0; i < desc.length; i++){
                System.out.println((i+1) + " -> " + desc[i]);
            }
            System.out.println("0 -> Sair/Retroceder");
            
            try {
                op = s.nextInt();
            } catch (InputMismatchException e){
                System.out.println("Input Errado");
                op = -1;
            }
            
            if(op > 0 && op <= opcoes.length){
                opcoes[op - 1].escolher();
            }

        } while(op != 0);
        
    }
    
    private static void menuInicial(Scanner s) {
        String[] desc = new String[] {
            "Login"
        };
        Opcao[] ops = new Opcao[] {
            new Opcao() { public void escolher() { 
                            Contribuinte cont;
                            try{
                                cont = login(); 
                            } catch(NaoExisteContribuinteException err){
                                System.out.println("Nº de Contribuinte inexistente.")
                            } catch(NumberFormatException err){
                                System.out.println("Nº de Contribuinte inválido.")
                            } catch(PasswordErradaException err){
                                System.out.println("Senha errada.");
                                return;
                            }

                            if (cont instanceof Individual){
                                menuInd(s, (Individual) cont);
                            } else {
                                menuCol(s, (Coletivo) cont);
                            } 
                           } 
                        }
        };
        menu(s, ops, desc);
    }
    
    private static int maisGastador(Contribuinte a, Contribuinte b){
        Set<Fatura> faturasA = estado.getFaturas(a.getNif());
        Set<Fatura> faturasB = estado.getFaturas(a.getNif());
        float totalA = a.getTotalGasto();
        float totalB = b.getTotalGasto();
        
        if(totalA > totalB){
            return -1;
        } else if(totalA > totalB){
            return 1;
        }
        
        return 0;
    }
    
    private static int maisFaturador(Coletivo a, Coletivo b){
        Set<Fatura> faturasA = estado.getFaturas(a.getNif());
        Set<Fatura> faturasB = estado.getFaturas(a.getNif());
        float totalA = a.totalFaturado();
        float totalB = b.totalFaturado();
        
        if(totalA > totalB){
            return -1;
        } else if(totalA > totalB){
            return 1;
        }
        
        return 0;
    }
    
    private static Set<Contribuinte> topGastadores(int n){
        Set<Contribuinte> ordenar = new TreeSet<Contribuinte>(new Comparator<Contribuinte>() {
            public int compare(Contribuinte a, Contribuinte b) {
                return maisGastador(a, b);
            }
        });
        Set<Contribuinte> resultado = new HashSet<Contribuinte>();
        
        for(Contribuinte contribuinte : estado.getContribuintes()){
            ordenar.add(contribuinte);
        }
        
        Iterator<Contribuinte> iter = ordenar.iterator();
        
        for(int i = 0; i < n && iter.hasNext(); i++){
            resultado.add(iter.next());
        }
        
        return resultado;
    }
    
    private static Set<Contribuinte> topFaturadores(int n){
        Set<Contribuinte> ordenar = new TreeSet<Contribuinte>(new Comparator<Contribuinte>() {
            public int compare(Contribuinte a, Contribuinte b) {
                return maisFaturador((Coletivo) a, (Coletivo) b);
            }
        });
        Set<Contribuinte> resultado = new HashSet<Contribuinte>();
        
        for(Contribuinte contribuinte : estado.getContribuintes()){
            ordenar.add(contribuinte);
        }
        
        Iterator<Contribuinte> iter = ordenar.iterator();
        
        for(int i = 0; i < n && iter.hasNext(); i++){
            resultado.add(iter.next());
        }
        
        return resultado;
    }
    
    private static LocalDate scanData(Scanner s){
        return LocalDate.now();//acabar
    }
    
    private static AtivEco scanAtiv(Scanner s){
        
        AtivEco[] ativs = AtivEco.values();
        for(int i = 0; i < ativs.length; i++){
             System.out.println(i + "->" + ativs[i]);
        }
        
        int esc;
        try {
            esc = s.nextInt();
        } catch (InputMismatchException e){
            System.out.println("Input Errado");
            esc = -1;
        }
        
        return ativs[esc];
    }
    
    private static int scanNif(Scanner s) {
        int nif;
        try {
            nif = s.nextInt();
        } catch (InputMismatchException e){
            System.out.println("Input Errado");
            nif = -1;
        }
        
        return nif;
    }
    
    
    private static void menuAdmin(Scanner s, Individual cont) {
        String[] desc = new String[] {
            //"Registar contribuinte", // individual e empresa
            "Ver os 10 contribuintes que gastam mais",
            "Ver as 10 empresas que mais faturam"
        };
        Opcao[] ops = new Opcao[] {
            //new Opcao() { public void escolher() { System.out.println("Faturas:"); } }, //(Individual)contrib).getFaturas
            new Opcao() { public void escolher() { System.out.println(topGastadores(10)); } },
            new Opcao() { public void escolher() { System.out.println(topFaturadores(10)); } } //Deve ser n
        };
        menu(s, ops, desc);
    }
    
    
    
    
    private static void menuInd(Scanner s, Individual cont) {
        String[] desc = new String[] {
            "Ver informações gerais",
            "Ver/alterar faturas", 
            "Ver montante de dedução fiscal acumulado" 
        };
        Opcao[] ops = new Opcao[] {
            new Opcao() { public void escolher() { System.out.println(cont); } },
            new Opcao() { public void escolher() { menuIndFaturas(s, cont); } },
            new Opcao() { public void escolher() { deducaoFiscal(s, cont); } }
        };
        menu(s, ops, desc);
    }
    
    
    private static void deducaoFiscal(Scanner s, Individual cont) {
        System.out.println("Dedução fiscal:");
        System.out.println("Individual: " + estado.calculaDeducao(cont) + "€");
        System.out.println("Agregado familiar: " + estado.calculaDeducaoAF(cont) + "€");
    }
    
    
    private static void  menuIndFaturas(Scanner s, Individual cont) {
        System.out.println("Ver faturas:");
        String[] desc = new String[] {
            "Com atribuição pendente",
            "Ordenadas por data",
            "Ordenadas por valor",
            "Por um emitente" 
        };
        Opcao[] ops = new Opcao[] {
            new Opcao() { public void escolher() { menuFaturas(s, estado.getFaturas(cont.getNif()).stream().filter(f -> f.getAtivEconomica() == AtivEco.Pendente).collect(Collectors.toSet()) );} },
            new Opcao() { public void escolher() { menuFaturas(s, estado.getFaturas(cont.getNif(), (a,b)->( b.getDataEmissao().compareTo(a.getDataEmissao()) )));} },
            new Opcao() { public void escolher() { menuFaturas(s, estado.getFaturas(cont.getNif(), (a,b)-> ( b.getValorTotal() - b.getValorTotal() )));} },
            new Opcao() { public void escolher() { menuFaturas(s, estado.getFaturasEmComum( scanNif(s), cont.getNif()));} }
        };

        menu(s, ops, desc);
    }
    

    
    private static void menuFaturas(Scanner s, Set<Fatura> faturas) {
        System.out.println("Escolha uma fatura para editar a atividade económica (esta operação ficará registada)");
        
        
        
        
        
        //FAZERRRRRRRRRRRRRRRRRRRRRRRRRR
    }

    
    private static void classificaFatura(Scanner s, Individual cont, Fatura fatura){
        
        System.out.println("Atividade Económica:");
        AtivEco ativ = scanAtiv(s);
        
        try {
            Coletivo emitente = (Coletivo) estado.getContribuinte(fatura.getNifEmitente());
            if(emitente.temAtivEco(ativ)) {
                fatura.setAtivEconomica(ativ);
                System.out.println("Atribuição concluída com sucesso");
            } else {
                System.out.println("Erro: A empresa emitente não tem essa atividade económica.");
            }
        }
        catch (NaoExisteContribuinteException e){
            System.out.println("Erro: Emitente não existe.");
        }
        
    }
    
    
   

        

    
    
    private static void menuCol(Scanner s, Coletivo cont) {
        String[] desc = new String[] {
            "Ver informações gerais",
            "Emitir fatura",
            "Ver faturas emitidas", 
            "Ver total faturado num período" 
        };
        Opcao[] ops = new Opcao[] {
            new Opcao() { public void escolher() { System.out.println(cont); } },
            new Opcao() { public void escolher() { emitirFatura(s, cont); } },
            new Opcao() { public void escolher() { menuColFaturas(s, cont); } },
            new Opcao() { public void escolher() { verTotalFaturado(s, cont); } }
        };
        menu(s, ops, desc);
    }
    
    private static void emitirFatura(Scanner s, Coletivo cont){
        System.out.println("Dados da Fatura");
        System.out.println("Nº de Contribuinte do Cliente:");
        int nif = s.nextInt(); //adicionar exceçoes
        
        System.out.println("Valor em euros:");
        int valor = s.nextInt();
        
        System.out.println("Descrição:");
        String desc = s.nextLine();
        
        estado.addFatura(cont.emitirFatura(valor, nif, LocalDate.now(), desc));
    }
    
    private static void  menuColFaturas(Scanner s, Coletivo cont) {
        System.out.println("Faturas emitidas:");
        String[] desc = new String[] {
            "Ordenadas por data",
            "Ordenadas por valor",
            "Por contribuinte" 
        };
        Opcao[] ops = new Opcao[] {
            new Opcao() { public void escolher() { verFaturas(estado.getFaturas(cont.getNif(), (a,b)->( b.getDataEmissao().compareTo(a.getDataEmissao()) ))); } },
            new Opcao() { public void escolher() { verFaturas(estado.getFaturas(cont.getNif(), (a,b)-> ( b.getValorTotal() - a.getValorTotal() ))); } },
            new Opcao() { public void escolher() { menuFaturasCont(s, cont, scanNif(s)); } }
        };
        menu(s, ops, desc);
    }
    
    
    private static void  menuFaturasCont(Scanner s, Coletivo cont, int ind) {
        System.out.println("Faturas do contribuinte " + ind + " :");
        String[] desc = new String[] {
            "Num intervalo",
            "Ordenadas por valor decrescente",
        };
        Opcao[] ops = new Opcao[] {
            new Opcao() { public void escolher() { verFaturasContIntervalo(s, cont, ind); } },
            new Opcao() { public void escolher() { verFaturas(estado.getFaturasEmComum(cont.getNif(), ind, (a,b)-> ( b.getValorTotal() - a.getValorTotal() ))); } }
        };
        menu(s, ops, desc);
    }
    
    private static void verFaturas(Set<Fatura> faturas) {
        for(Fatura f : faturas){
            System.out.println(f); 
            System.out.println("\n"); 
        }
    }
    
    private static void  verFaturasContIntervalo(Scanner s, Coletivo cont, int ind){
        
        System.out.println("De:");
        LocalDate inicio = scanData(s);
        
        System.out.println("Até:");
        LocalDate fim = scanData(s);
        
        verFaturas(estado.getFaturasEmComum(cont.getNif(), ind, inicio, fim));
    }
    
    private static void  verTotalFaturado(Scanner s, Coletivo cont) {
        
        System.out.println("De:");
        LocalDate inicio = scanData(s);
        
        System.out.println("Até:");
        LocalDate fim = scanData(s);
        
        System.out.println(cont.totalFaturado(inicio, fim));
    }
    

    
    
    
    
    private static Contribuinte login() throws NaoExisteContribuinteException, NumberFormatException{
        String passwd;
        Contribuinte resultado = null;
        Scanner scanner = new Scanner(System.in);
        int tentativas = 3;
        
        System.out.println("Nº de Contribuinte:");
        String nif = scanner.nextLine();
        resultado = estado.getContribuinte(Integer.parseInt(nif));
        
        System.out.println("Senha de acesso:");
        passwd = scanner.nextLine();
        if(!passwd.equals(resultado.getPassword())){
            throws new PasswordErradaException(passwd);
        }
        
        scanner.close();
        
        return resultado;
    }
}
