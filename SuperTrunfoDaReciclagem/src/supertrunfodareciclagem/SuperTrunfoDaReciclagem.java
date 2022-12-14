/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package supertrunfodareciclagem;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author julia
 */
public class SuperTrunfoDaReciclagem {
    
 
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int response = 0;
        boolean error = false;
        Scanner ler0 = new Scanner(System.in);
        
        System.out.println(" *:･ﾟ✧*:･ﾟ✧ SUPER TRUNFO DA RECICLAGEM *:･ﾟ✧*:･ﾟ✧\n\t\t      Bem vindo!\n\t\t        Menu:");
        
        do{
            error = false;
            try {
                do{
                    System.out.println("\n1. Iniciar jogo\n2. Iniciar simulação\n3. Sair\n");
                    response = ler0.nextInt();
                }while(response > 3 || response < 1);
            } catch (Exception e) {
                System.out.println("Erro, insira uma opção válida. \n");
                ler0.next();
                error = true;
            }
        } while (error);
        
        switch( response ){
            case 1: 
                novoJogo(false);
            break;
            case 2:
                novoJogo(true);
            break;    
            case 3: 
               System.out.println("Volte sempre! :D");
            break;
            default:
            break;
        }
    }
    
    public static void novoJogo(boolean simulacao){
        Jogador[] jogador = new Jogador[3];
        Baralho baralho = new Baralho();
        Cartas carta;
        Scanner ler1 = new Scanner(System.in); // precisa de dois scanner pq se nao buga
        Scanner ler = new Scanner(System.in);
        boolean error = false;
        Random random = new Random();
        int jogadorTurno = random.nextInt(1);
        int round = 0;
        int response = 0;
        int result;
        baralho.geraBaralho();
        
        List<Integer> baralhoUm = new ArrayList<Integer>(16);
        List<Integer> baralhoDois = new ArrayList<Integer>(16);
        
        
        Random rn = new Random();
        int numeroRamdom = 0;
        
        if(!simulacao){// se nao for simulacao
            System.out.println("\nInsira o nome do jogador um:");
            jogador[0] = new Jogador(ler1.nextLine());
        
            System.out.println("\nInsira o nome do jogador dois:");
            jogador[1] = new Jogador(ler1.nextLine());;
            
        }else{
            jogador[0] = new Jogador("Lucas");
            jogador[1] = new Jogador("Júlia");
            
        }
        
        jogador[2] = new Jogador("Mesa");
        

        while (baralhoUm.size() < 16){
            numeroRamdom = rn.nextInt(32) + 1;

            if (!baralhoUm.contains(numeroRamdom)){
                baralhoUm.add(numeroRamdom);
            }
        }

        while (baralhoDois.size() < 16){
            numeroRamdom = rn.nextInt(32) + 1;

            if (!baralhoDois.contains(numeroRamdom) && !baralhoUm.contains(numeroRamdom)){
                baralhoDois.add(numeroRamdom);
            }
        }
        
        for(Integer a : baralhoUm){
            carta = baralho.selecionaCarta(a - 1);
            jogador[0].incluir(carta);
        }
        
        for(Integer a : baralhoDois){
            carta = baralho.selecionaCarta(a - 1);
            jogador[1].incluir(carta);
        }
    
       
        for(int i = 0; i < jogador.length; i++){
            System.out.println("\nBaralho de "+ jogador[i].nome +":");
            System.out.print(jogador[i].cartas);
        }

        if (jogadorTurno == 1){
            System.out.println("\n\n"+jogador[0].nome+ " começa. "+jogador[0].nome+" possui "+jogador[0].numeroDeCartas()+" cartas. "+jogador[1].nome+" possui "+jogador[1].numeroDeCartas()+" cartas.\n");
        }else{
            System.out.println("\n\n"+jogador[1].nome+ " começa. "+jogador[0].nome+" possui "+jogador[0].numeroDeCartas()+" cartas. "+jogador[1].nome+" possui "+jogador[1].numeroDeCartas()+" cartas.\n");
        }
        
        do{
            round++;
            
            System.out.println("\n~~> Rodada número: " + round);
            
            if( jogadorTurno == 1){
                System.out.println("\nÉ a vez de " + jogador[0].nome + ".");
            }else{
                System.out.println("\nÉ a vez de " + jogador[1].nome + ".");
            }
            
            System.out.println("Carta de " + jogador[jogadorTurno == 1 ? 0 : 1].nome + ": " + jogador[jogadorTurno == 1 ? 0 : 1].cartas.get(0));
            
            if(!simulacao){
                do{
                    error = false;
                    try {
                        do{
                            System.out.printf("Qual atributo comparar?\n1. Decomposicao\n2. Reciclavel\n3. Ataque\n4. Cor\n");
                            response = ler.nextInt();
                        }while(response > 4 || response < 1);
                    } catch (Exception e) {
                        System.out.println("Erro, insira uma opção válida. \n");
                        ler.next();
                        error = true;
                    }
                } while (error);
            }else{
                response = random.nextInt(3) + 1;
            }
           
            result = jogador[jogadorTurno == 1 ? 0 : 1].cartas.get(0).comparar(response, jogador[jogadorTurno == 1 ? 1 : 0].cartas.get(0));
            
            if (result == 1){
                jogador[jogadorTurno == 1 ? 0 : 1].incluir(jogador[jogadorTurno == 1 ? 0 : 1].cartas.get(0));
                jogador[jogadorTurno == 1 ? 0 : 1].incluir(jogador[jogadorTurno == 1 ? 1 : 0].cartas.get(0));
                jogador[jogadorTurno == 1 ? 0 : 1].excluir();
                jogador[jogadorTurno == 1 ? 1 : 0].excluir();
                
                System.out.println("\n"+jogador[jogadorTurno == 1 ? 0 : 1].nome+" ganhou. "+jogador[jogadorTurno == 1 ? 0 : 1].nome+" possui "+jogador[jogadorTurno == 1 ? 0 : 1].numeroDeCartas()+" cartas. "+jogador[jogadorTurno == 1 ? 1 : 0].nome+" possui "+jogador[jogadorTurno == 1 ? 1 : 0].numeroDeCartas()+" cartas.\n");
            } else if (result == -1){
                jogador[jogadorTurno == 1 ? 1 : 0].incluir(jogador[jogadorTurno == 1 ? 1 : 0].cartas.get(0));
                jogador[jogadorTurno == 1 ? 1 : 0].incluir(jogador[jogadorTurno == 1 ? 0 : 1].cartas.get(0));
                jogador[jogadorTurno == 1 ? 1 : 0].excluir();
                jogador[jogadorTurno == 1 ? 0 : 1].excluir();
                
                System.out.println("\n"+jogador[jogadorTurno == 1 ? 1 : 0].nome+" ganhou. "+jogador[jogadorTurno == 1 ? 0 : 1].nome+" possui "+jogador[jogadorTurno == 1 ? 0 : 1].numeroDeCartas()+" cartas. "+jogador[jogadorTurno == 1 ? 1 : 0].nome+" possui "+jogador[jogadorTurno == 1 ? 1 : 0].numeroDeCartas()+" cartas.\n");
            } else {
                jogador[2].incluir(jogador[jogadorTurno == 1 ? 0 : 1].cartas.get(0));
                jogador[2].incluir(jogador[jogadorTurno == 1 ? 1 : 0].cartas.get(0));
                jogador[jogadorTurno == 1 ? 0 : 1].excluir();
                jogador[jogadorTurno == 1 ? 1 : 0].excluir();
                
                System.out.println("\nEmpate. As cartas vão para a mesa. A mesa está com "+jogador[2].numeroDeCartas()+" cartas. "+jogador[jogadorTurno == 1 ? 0 : 1].nome+" possui "+jogador[jogadorTurno == 1 ? 0 : 1].numeroDeCartas()+" cartas. "+jogador[jogadorTurno == 1 ? 1 : 0].nome+" possui "+jogador[jogadorTurno == 1 ? 1 : 0].numeroDeCartas()+" cartas.\n");
            }

            jogadorTurno = jogadorTurno == 1 ? 0 : 1;
            
        }while(jogador[jogadorTurno == 1 ? 0 : 1].temCartas() && jogador[jogadorTurno == 1 ? 1 : 0].temCartas());
        
        System.out.println("=^..^=    =^..^=    =^..^=    =^..^=    =^..^=    =^..^=    =^..^=    =^..^=    =^..^=    =^..^=    =^..^=");
        if(jogador[jogadorTurno == 1 ? 0 : 1].temCartas()){
            for(int i = 0; i < jogador[2].numeroDeCartas(); i++){
                jogador[jogadorTurno == 1 ? 0 : 1].incluir(jogador[2].cartas.get(0));
            }
            
            System.out.println("\n\t\tO ganhador é " + jogador[jogadorTurno == 1 ? 0 : 1].nome + ". As cartas da mesa vão pra ele. " + jogador[jogadorTurno == 1 ? 0 : 1].nome + " possui " + jogador[jogadorTurno == 1 ? 0 : 1].numeroDeCartas()+ " cartas.");
        }else if(jogador[jogadorTurno == 1 ? 1 : 0].temCartas()){
            for(int i = 0; i < jogador[2].numeroDeCartas(); i++){
                jogador[jogadorTurno == 1 ? 1 : 0].incluir(jogador[2].cartas.get(0));
            }
                        
            System.out.println("\n\t\tO ganhador é " + jogador[jogadorTurno == 1 ? 1 : 0].nome + ". As cartas da mesa vão pra ele. " + jogador[jogadorTurno == 1 ? 1 : 0].nome + " possui " + jogador[jogadorTurno == 1 ? 1 : 0].numeroDeCartas()+ " cartas.");
        }
        System.out.println("\n=^..^=    =^..^=    =^..^=    =^..^=    =^..^=    =^..^=    =^..^=    =^..^=    =^..^=    =^..^=    =^..^=");
        
        do{
            error = false;
            try {
                do{
                    System.out.println("\nQuero: \n1. Jogar novamente\n2. Simular novamente\n3. Sair\n");
                    response = ler.nextInt();
                }while(response > 3 || response < 1);
            } catch (Exception e) {
                System.out.println("Erro, insira uma opção válida. \n");
                ler.next();
                error = true;
            }
        } while (error);
        
        switch(response){
            case 1:
                novoJogo(false);
            break;
            case 2:
                novoJogo(true);
            break;
            case 3: 
               System.out.println("Volte sempre! :D");
            break;
            default:
            break;
        }
    }  
}
