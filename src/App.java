
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
    
    public static void main(String[] args) {
        
        try {
            estado = Estado.leEstado();
        }
        catch(Exception e){
            estado = new Estado();
        }
        
        Scanner s = new Scanner(System.in);
        Contribuinte contrib = login(s); 
        int op = 0;
        
        do {
            try {
               System.out.println("Escreve um número!");
               op = s.nextInt();
            } catch (InputMismatchException e){
               System.out.println("Input Errado");
               op = -1;
            }
           
            switch(op){
                   case 1:  System.out.println("1!");
                            break;
                   
                    default: System.out.println("Isso é um número.");
            }
           
        } while(op != 0);
        
        s.close();
        
        try {
            estado.guardaEstado();
        } 
        catch(IOException e){
            System.out.print(e.getMessage());
        }
    }
    
    private static void menu(Scanner s) {
        
        int op = 0;

        do {
            try {
                System.out.println("Escreve um número!");//opçoes
                op = s.nextInt();
            } catch (InputMismatchException e){
                System.out.println("Input Errado");
                op = -1;
            }
                    
            switch(op){
                   case 1:  System.out.println("1!");
                            break;
                   
                   default: System.out.println("Isso é um número.");
            }
           
        } while(op != 0);
        

    }
    

    private static Contribuinte login(Scanner s) {

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
