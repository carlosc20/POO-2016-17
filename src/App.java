
/**
 * Write a description of class Portal here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.Map;
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

public class App
{
    private static Estado estado;
    
    /*Validar acesso
     * 
     * Individual pode:
     * Consultar faturas, associar ativeEco onde não foi auto, corrigir ativEco(deixa registo para ser rastreada)
     * Ver valor acum deduções fiscais -> imposto anual total, por si e por agregado
     * 
     * 
     * Admin:
     * Criar contrib
     * associar empresas a ativ eco
     * relaçao 10 contribs que mais gastam 
     * ver X empresas com mais faturas e suas deduçoes
     * 
     * extracto de facturação de uma empresa num determinado período
     * valor total de despesas de um contribuinte, valor total de despesas de um contribuinte por actividade económica
     */
    
    
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
        Contribuinte cont = login(s); 

        if (cont instanceof Individual){
            menuInd(s, (Individual) cont);
        } else {
            menuCol(s, (Coletivo) cont);
        }
        
        s.close();
        
        try {
            estado.guardaEstado();
        } 
        catch(IOException e){
            System.out.print(e.getMessage());
        }
    }
    
    private static void menu(Scanner s, Opcao[] opcoes, String[] desc){
        
        int op = 0;

        do {
            System.out.println("0 -> Sair/Retroceder");
            for(int i = 0; i < desc.length; i++){
                System.out.println((i+1) + " -> " + desc[i]);
            }
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
    
    private static LocalDate scanData(Scanner s){
        return LocalDate.now();//acabar
    }
    
    
    
    
    private static void menuAdmin(Scanner s, Individual cont) {
        String[] descInd = new String[] {
            "Registar contribuinte", 
            "etc"
        };
        Opcao[] opsInd = new Opcao[] {
            new Opcao() { public void escolher() { System.out.println("Faturas:"); } }, //(Individual)contrib).getFaturas
            new Opcao() { public void escolher() { System.out.println("Menu 2"); } }
        };
        menu(s, opsInd, descInd);
    }
    
    
    
    
    private static void menuInd(Scanner s, Individual cont) {
        String[] descInd = new String[] {
            "Ver informações gerais",
            "Ver faturas", //subcategorias? por empresa, ordenado etc ->pendentes
            "Ver montante de dedução fiscal acumulado" // por si e por agregado
        };
        Opcao[] opsInd = new Opcao[] {
            new Opcao() { public void escolher() { System.out.println("És um contribuinte individual"); } }, //cont
            new Opcao() { public void escolher() { System.out.println("Faturas:"); } }, //cont.getFaturas
            new Opcao() { public void escolher() { System.out.println("0€"); } }
        };
        menu(s, opsInd, descInd);
    }
    
    private static void classificaFatura(Scanner s, Individual cont, Fatura fatura){
        
        System.out.println("Atividade Económica:");
        AtivEco ativ = s.nextLine();
        
        Coletivo emitente = estado.getContribuinte(fatura.getNifEmitente());
        
        if(emitente.temAtivEco(ativ)) {
            fatura.setAtivEconomica(ativ);
            System.out.println("Atribuição concluída com sucesso");
        } else {
            System.out.println("Erro: A empresa emitente não tem essa atividade económica.");
        }
    }
    
    
    
    private static void menuCol(Scanner s, Coletivo cont) {
        String[] descCol = new String[] {
            "Ver informações gerais",
            "Emitir fatura",
            "Ver faturas", //por data e valor, por contrib por data ou valor
            "Ver total faturado" //num intervalo de tempo
        };
        Opcao[] opsCol = new Opcao[] {
            //cenas
            new Opcao() { public void escolher() { emitirFatura(s, cont); } },
            new Opcao() { public void escolher() { menuVerFaturas(s, cont); } },
            new Opcao() { public void escolher() { verTotalFaturado(s, cont); } }
        };
        menu(s, opsCol, descCol);
    }
    
    private static void emitirFatura(Scanner s, Coletivo cont){
        
        System.out.println("Nº de Contribuinte do Cliente:");
        int nif = s.nextInt();
        
        System.out.println("Valor:");
        int valor = s.nextInt();
        
        System.out.println("Descrição:");
        String desc = s.nextLine();
        
        estado.addFatura(cont.emitirFatura(valor, nif, LocalDate.now(), desc));
    }
    
    private static void  menuVerFaturas(Scanner s, Coletivo cont) {
        //acabar
    }
    
    private static void  verTotalFaturado(Scanner s, Coletivo cont) {
        System.out.println("De:");
        LocalDate inicio = scanData(s);
        
        System.out.println("Até:");
        LocalDate fim = scanData(s);
        
        System.out.println(cont.totalFaturado(inicio, fim));
    }
    

    

    

    

    private static Contribuinte login(Scanner s){

        System.out.println("Nº de Contribuinte:");
        String nif = s.nextLine();
       
        System.out.println("Senha de acesso:");
        String passwd = s.nextLine();
        
        return null;
        /*
        Contribuinte user = estado.getContribs().get(nif);
        if (user != null && user.getPassword() == passwd) 
            return user;
        
        System.out.println("Não existe esse Nº de Contribuinte ou senha de acesso errada.");
        return null;
        */
    }
}
