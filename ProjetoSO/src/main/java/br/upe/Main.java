package br.upe;
import br.upe.gerenciador.IGerenciador;
import br.upe.gerenciador.GerenciaMemoria;
import br.upe.processo.FabricaDeEntradas;
import br.upe.processo.Processos;

public class Main {

    // o número de threads
    static final int NUM_THREADS = 2;
    public static void main(String[] args) throws InterruptedException {

        Simulador simulador = new Simulador();
        simulador.inicio();

        // gerenciador compartilhado entre todas as threads
        IGerenciador gerenciador = new GerenciaMemoria();

        simulador.memoriaCarregada();

        // FabricaDeEntradas gera sequências aleatórias de operações
        // TAM_VIRTUAL precisa estar entre 10 e 40
        FabricaDeEntradas fabrica = new FabricaDeEntradas(gerenciador.TAM_VIRTUAL);

        // cria e dispara as threads
        Thread[] threads = new Thread[NUM_THREADS];
        for (int i = 0; i < NUM_THREADS; i++) {
            // cada thread recebe uma sequência diferente da fábrica
            String sequencia = fabrica.getNewEntrada();
            threads[i] = new Thread(new Processos(i + 1, sequencia, gerenciador));
        }

        //startar
        for (Thread t : threads) t.start();
        for (Thread t : threads) t.join();

        // estado final
        System.out.println("\n── Estado final ──────────────────────────");
        gerenciador.getFisica().consultaConteudo();

        gerenciador.getVirtual().exibir(); // chama explicitamente


        simulador.desliga();
    }
}