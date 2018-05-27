
/**
 * Write a description of class Portal here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.SortedSet;
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
import java.time.format.DateTimeParseException;
import java.util.stream.Collectors;

public class App
{
    private static Estado estado;

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
        /*
        try {
            estado = Estado.leEstado();
        }
        catch(Exception e){
            estado = new Estado();
        }
        */
        Scanner s = new Scanner(System.in);
        menuInicial(s);
        s.close();
        /*
        try {
            estado.guardaEstado();
        } 
        catch(IOException e){
            System.out.print(e.getMessage());
        }
        */
    }
    
    public void run(){
        
        Scanner s = new Scanner(System.in);
        menuInicial(s);
        s.close();
        
    }
    
    private static void menu(Scanner s, Opcao[] opcoes, String[] desc){
        
        int op = 0;

        do {
            for(int i = 0; i < desc.length; i++){
                System.out.println((i+1) + " -> " + desc[i]);
            }
            System.out.println("0 -> Sair/Retroceder");
            
            try {
                op = Integer.parseInt(s.nextLine());
            } catch (NumberFormatException e){
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
                    try {
                        cont = login(s);
                        if (cont instanceof Administrador){
                            menuAdmin(s);
                        } else
                        if (cont instanceof Individual){
                            menuInd(s, (Individual) cont);
                        } else {
                            menuCol(s, (Coletivo) cont);
                        }
                    } catch(NaoExisteContribuinteException err){
                        System.out.println("Nº de Contribuinte inexistente.");
                        return;
                    } catch(NumberFormatException err){
                        System.out.println("Nº de Contribuinte inválido.");
                        return;
                    } catch(PasswordErradaException err){
                        System.out.println("Senha errada.");
                        return;
                    }
                } 
            }
        };
        menu(s, ops, desc);
    }
    
    private static Contribuinte login(Scanner s) throws PasswordErradaException, NaoExisteContribuinteException, NumberFormatException{
        String passwd;
        Contribuinte resultado = null;
        
        System.out.println("Nº de Contribuinte:");
        String nif = s.nextLine();
        resultado = estado.getContribuinte(Integer.parseInt(nif));
        
        System.out.println("Senha de acesso:");
        passwd = s.nextLine();
        if(!passwd.equals(resultado.getPassword())){
            throw new PasswordErradaException(passwd);
        }
        
        return resultado;
    }
    
    
    private static void menuAdmin(Scanner s) {
        String[] desc = new String[] {
            "Registar contribuinte", 
            "Ver os 10 contribuintes que gastam mais",
            "Ver as N empresas que mais faturam",
            "Ver alterações"
        };
        Opcao[] ops = new Opcao[] {
            new Opcao() { public void escolher() { menuAdminRegistar(s); } }, 
            new Opcao() { public void escolher() { System.out.println(topGastadores(10)); } },
            new Opcao() { public void escolher() {
                            int n = scanNif(s);
                            System.out.println(topFaturadores(n)); 
                          } 
                        },
            new Opcao() { public void escolher() { menuVerAlteracoes(s); } }           
        };
        menu(s, ops, desc);
    }
    
    private static void menuVerAlteracoes(Scanner s){
    
    }
    
    private static void menuAdminRegistar(Scanner s) {
        System.out.println("Registar contribuinte:");
        String[] desc = new String[] {
            "Individual",
            "Coletivo"
        };
        Opcao[] ops = new Opcao[] {
            new Opcao() { public void escolher() { registarInd(s); } },
            new Opcao() { public void escolher() { registarCol(s); } } 
        };
        menu(s, ops, desc);
    }
    
    private static void registarInd(Scanner s){
        int nif;
        String nome;
        String email;
        String morada;
        String password; 
        
        Set<Integer> nifAF; // AF = agregado familiar
        float coefFiscal; // um fator multiplicativo que é associado a cada despesa elegível
        Set<AtivEco> ativDedutiveis;
        

        System.out.println("Dados do contribuinte:");

        System.out.println("Nº de Contribuinte:");
        try {
            nif = Integer.parseInt(s.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Input Errado");
            return;
        }
        
        System.out.println("Nome:");
        nome = s.nextLine();
        System.out.println("Email:");
        email = s.nextLine();
        System.out.println("Morada:");
        morada = s.nextLine();
        System.out.println("Senha de acesso:");
        password = s.nextLine();
        
        //cenas
        
        //estado.addContribuinte(new Individual(nif, nome, email, morada, password, coefFiscal, nifAF, ativDedutiveis));
    }
    
    private static void registarCol(Scanner s){
        
    }
    
    private static Set<Contribuinte> topGastadores(int n){
        SortedSet<Contribuinte> ordenar = new TreeSet<Contribuinte>( (a, b) -> Float.compare(a.getTotalGasto(), b.getTotalGasto()) );
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
    
    private static Set<Coletivo> topFaturadores(int n){
        Set<Coletivo> ordenar = new TreeSet<Coletivo>( (a, b) -> Float.compare(a.totalFaturado(), b.totalFaturado()) );
        Set<Coletivo> resultado = new HashSet<Coletivo>();
        
        for(Contribuinte contribuinte : estado.getContribuintes()){
            if(contribuinte instanceof Coletivo){
                ordenar.add((Coletivo) contribuinte);
            }
        }
        
        Iterator<Coletivo> iter = ordenar.iterator();
        
        for(int i = 0; i < n && iter.hasNext(); i++){
            resultado.add(iter.next());
        }
        
        return resultado;
    }
    
    private static LocalDate scanData(Scanner s) throws DateTimeParseException {
        String data = s.nextLine();
        try{
            return LocalDate.parse(data);
        } catch(DateTimeParseException e){
            if(data.equals("")){
                LocalDate agora = LocalDate.now();
                System.out.println(agora);
                return agora;
            } else {
                throw e;
            }
        }   
    }
    
    private static AtivEco scanAtiv(Scanner s){
        
        AtivEco[] ativs = AtivEco.values();
        for(int i = 0; i < ativs.length; i++){
             System.out.println(i + "->" + ativs[i].fancyToString());
        }
        
        int esc;
        try {
            esc = Integer.parseInt(s.nextLine());
        } catch (NumberFormatException e){
            System.out.println("Input Errado");
            esc = -1;
        }
        
        return ativs[esc];
    }
    
    private static int scanNif(Scanner s) {
        int nif;
        try {
            nif = Integer.parseInt(s.nextLine());
        } catch (NumberFormatException e){
            System.out.println("Input Errado");
            nif = -1;
        }
        
        return nif;
    }
    
    

    
    
    
    
    private static void menuInd(Scanner s, Individual cont) {
        String[] desc = new String[] {
            "Ver informações gerais",
            "Ver/alterar faturas", 
            "Ver montante de dedução fiscal acumulado" 
        };
        Opcao[] ops = new Opcao[] {
            new Opcao() { public void escolher() { System.out.println("Informações gerais:\n" + cont.fancyToString()); } },
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
            "Por um emitente",
            "Atribuir/corrigir atividade económica"
        };
        Opcao[] ops = new Opcao[] {
            new Opcao() {
                public void escolher() {
                    try {
                        verFaturas(estado.getFaturas(cont.getNif()).stream().filter(f -> f.getAtivEconomica() == AtivEco.Pendente).collect(Collectors.toSet()) );
                    } catch (NaoExisteContribuinteException err){

                    }
                }
            },
            new Opcao() {
                public void escolher() {
                    try{
                        verFaturas(estado.getFaturas(cont.getNif(), (a,b)->( b.getDataEmissao().compareTo(a.getDataEmissao()) )));
                    } catch (NaoExisteContribuinteException e){
                        System.out.println("Contribuinte não encontrado: " + e.getMessage());
                    }
                }
            },
            new Opcao() {
                public void escolher() {
                    try{
                        verFaturas(estado.getFaturas(cont.getNif(), (a,b)-> ( Float.compare(a.getValorTotal(), b.getValorTotal()) )));
                    } catch (NaoExisteContribuinteException e){
                        System.out.println("Contribuinte não encontrado: " + e.getMessage());
                    }
                }
            },
            new Opcao() {
                public void escolher() {
                    try {
                        System.out.println("Número do contribuinte: ");
                        verFaturas(estado.getFaturasEmComum( scanNif(s), cont.getNif()));
                    } catch (NaoExisteContribuinteException e){
                        System.out.println("Contribuinte não encontrado: " + e.getMessage());
                    }
                }
            },
            new Opcao() {
                public void escolher() {
                    try {
                        System.out.println("Número da fatura:");
                        Fatura fat = estado.getFatura(scanNif(s));
                        if (estado.getFaturas(cont.getNif()).contains(fat))
                            classificaFatura(s, cont,fat);
                        else
                            System.out.println("Fatura não encontrada");
                    } catch (NaoExisteContribuinteException e){
                        System.out.println("Contribuinte não encontrado: " + e.getMessage());
                    }
                    
                }
            }
        };

        menu(s, ops, desc);
    }

    private static void classificaFatura(Scanner s, Individual cont, Fatura fatura){
        
        System.out.println("Atividade Económica:");
        AtivEco ativ = scanAtiv(s);
        
        try {
            Coletivo emitente = (Coletivo) estado.getContribuinte(fatura.getNifEmitente());
            if(emitente.temAtivEco(ativ)) {
                fatura.setAtivEconomica(ativ);//alterar buscar outra-----------------------------------------------------
                addAlteracao(new Alteracao(fatura.getId(),fatura.getAtivEconomica(), ativ, LocalDate.now()));
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
            new Opcao() { public void escolher() { System.out.println("Informações gerais:\n" + cont.fancyToString()); } },
            new Opcao() { public void escolher() { emitirFatura(s, cont); } },
            new Opcao() { public void escolher() { menuColFaturas(s, cont); } },
            new Opcao() { public void escolher() { verTotalFaturado(s, cont); } }
        };
        menu(s, ops, desc);
    }
    
    private static void emitirFatura(Scanner s, Coletivo cont){
        int nif;
        float valor;
        LocalDate data;
        String desc;

        System.out.println("Dados da Fatura");

        System.out.println("Nº de Contribuinte do Cliente:");
        try {
            nif = Integer.parseInt(s.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Input Errado");
            return;
        }
        
        System.out.println("Valor em euros:");
        try {
            valor = Float.parseFloat(s.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Input Errado");
            return;
        }
        
        System.out.println("Data de emissão:");
        try {
            data = scanData(s);
        } catch (DateTimeParseException e){
            System.out.println("A data apresentada é inválida. O formato é aaaa-mm-dd.");
            return;
        }
        
        System.out.println("Descrição:");
        desc = s.nextLine();
        
        estado.addFatura(cont.emitirFatura(valor, nif, data, desc));
    }
    
    private static void  menuColFaturas(Scanner s, Coletivo cont) {
        System.out.println("Faturas emitidas:");
        String[] desc = new String[] {
            "Ordenadas por data",
            "Ordenadas por valor",
            "Por contribuinte" 
        };
        Opcao[] ops = new Opcao[] {
            new Opcao() {
                public void escolher() {
                    try{
                        verFaturas(estado.getFaturas(cont.getNif(), (a,b)->( b.getDataEmissao().compareTo(a.getDataEmissao() )))); 
                    } catch (NaoExisteContribuinteException e){
                        System.out.println("Contribuinte não encontrado: " + e.getMessage());
                    }
                }
            },
            new Opcao() {
                public void escolher() {
                    try{
                        verFaturas(estado.getFaturas(cont.getNif(), (a,b)-> ( Float.compare(b.getValorTotal(), a.getValorTotal()) )));
                    } catch (NaoExisteContribuinteException e){
                        System.out.println("Contribuinte não encontrado: " + e.getMessage());
                    }
                }
            },
            new Opcao() {
                public void escolher() {
                    System.out.println("Número do contribuinte: ");
                    menuFaturasCont(s, cont, scanNif(s));
                }
            }
        };
        menu(s, ops, desc);
    }
    
    
    private static void  menuFaturasCont(Scanner s, Coletivo cont, int ind) {
        System.out.println("Faturas do contribuinte " + ind + ":");
        String[] desc = new String[] {
            "Num intervalo",
            "Ordenadas por valor decrescente",
        };
        Opcao[] ops = new Opcao[] {
            new Opcao() { public void escolher() { verFaturasContIntervalo(s, cont, ind); } },
            new Opcao() {
                public void escolher() {
                    try {
                        verFaturas(estado.getFaturasEmComum(cont.getNif(), ind, (a,b)-> ( Float.compare(b.getValorTotal(), a.getValorTotal())    )));
                    } catch(NaoExisteContribuinteException e){
                        System.out.println("Contribuinte não encontrado: " + e.getMessage());
                    }
                }
            }
        };
        menu(s, ops, desc);
    }
    
    private static void verFaturas(Set<Fatura> faturas) {
        for(Fatura f : faturas){
            System.out.println(f.fancyToString()); 
        }
    }
    
    private static void  verFaturasContIntervalo(Scanner s, Coletivo cont, int ind){
        try {
            System.out.println("De:");
            LocalDate inicio = scanData(s);
        
            System.out.println("Até:");
            LocalDate fim = scanData(s);
            verFaturas(estado.getFaturasEmComum(cont.getNif(), ind, inicio, fim));
            
        } catch (DateTimeParseException e){
            System.out.println("A data apresentada é inválida. O formato é aaaa-mm-dd.");
        } catch (NaoExisteContribuinteException e){
            System.out.println("Contribuinte não encontrado: " + e.getMessage());
        }
    }
    
    private static void  verTotalFaturado(Scanner s, Coletivo cont) {
        
        try {
            System.out.println("De:");
            LocalDate inicio = scanData(s);
        
            System.out.println("Até:");
            LocalDate fim = scanData(s);
            
            System.out.println("Total faturado: " + estado.totalFaturado(cont.getNif(), inicio, fim) + " €");
            
        } catch (DateTimeParseException e){
            System.out.println("A data apresentada é inválida. O formato é aaaa-mm-dd.");
        } catch (NaoExisteContribuinteException e){
            System.out.println("Contribuinte não encontrado: " + e.getMessage());
        }
    }
    

    
    
  
}
