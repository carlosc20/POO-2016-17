
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

public class App
{
    private static Estado estado;
    
    /*Validar acesso
     * 
     * Individual pode:
     * Consultar faturas, associar ativeEco onde não foi auto, corrigir ativEco(deixa registo para ser rastreada)
     * Ver valor acum deduções fiscais -> imposto anual total, por si e por agregado
     * 
     * Coletivo:
     * ver faturas por data ou valor
     * ver faturas por contrib num intervalo de tempo ou ordenadas por valor
     * ver total faturado em intervalo
     * emitir faturas
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
    
    private static void menuAdmin(Scanner s, Individual cont) {
        String[] descInd = new String[] {
            "Registar contribuinte", 
            "etc"
        };
        Opcao[] opsInd = new Opcao[] {
            new Opcao() { public void escolher() { System.out.println("Faturas:"); } }, //(Individual)contrib).getFaturas
            new Opcao() { public void escolher() { System.out.println("Menu 2"); } }
        };
        menu(s, opsInd, descInd, 2);
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
        menu(s, opsInd, descInd, 3);
    }
    
    
    private static void menuCol(Scanner s, Coletivo cont) {
        String[] descCol = new String[] {
            "Diz boas",
            "Diz oi"
        };
        Opcao[] opsCol = new Opcao[] {
            new Opcao() { public void escolher() { System.out.println("Boas"); } },
            new Opcao() { public void escolher() { System.out.println("Oi"); } }
        };
        menu(s, opsCol, descCol, 2);
    }
    
    
    private static void menu(Scanner s, Opcao[] opcoes, String[] desc, int nOps){
        
        int op = 0;

        do {
            System.out.println("0 -> Sair/Retroceder");
            for(int i = 0; i < nOps; i++){
                System.out.println((i+1) + " -> " + desc[i]);
            }
            try {
                op = s.nextInt();
            } catch (InputMismatchException e){
                System.out.println("Input Errado");
                op = -1;
            }
            
            if(op > 0 && op <= nOps){
                opcoes[op - 1].escolher();
            }

        } while(op != 0);
        
    }
    

    private static Contribuinte login(Scanner s){

        System.out.println("Nº de Contribuinte:");
        String nif = s.next();
       
        System.out.println("Senha de acesso:");
        String passwd = s.next();
        
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
