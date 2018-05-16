
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
       
       int op = 0;
       
       do {
           try {
               op = executaMenu();
            } catch (InputMismatchException e){
               System.out.println("Input Errado");
               op = -1;
           }
           switch(op){
                   case 1:  System.out.println("1!");
                            break;
                   
                   default: System.out.println("Isso é um número");
           }
       } while(op != 0);
        
       try {
           estado.guardaEstado();
       } catch (Exception e){
           System.out.println("Não foi possivel guardar");
       }
    }
    

    private static int executaMenu() throws InputMismatchException {
       Scanner s = new Scanner(System.in);
       System.out.println("Escreve um número");
       int op = s.nextInt();
       s.close();
       return op;
    }
    
}
