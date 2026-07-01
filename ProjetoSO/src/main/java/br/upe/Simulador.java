package br.upe;

public class Simulador {

    public void inicio(){
        System.out.println("--> Ligando Sistema Operacional ^^");
        System.out.println("--> Carregando Kernel");
        System.out.println("--> Iniciando gerenciador de memoria");
    }

    public void memoriaCarregada(){
        System.out.println("--> Memoria Física inicializada \n--> Memória Virtual inicializada \n--> HD inicializado");
        System.out.println("--> Sistema Iniciado");
    }

    public void desliga(){
        System.out.println("--> Sistema finalizado");
    }
}
