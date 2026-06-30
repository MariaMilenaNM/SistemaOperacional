package br.upe;
import br.upe.gerenciador.GerenciaMemoria;
import br.upe.processo.FabricaDeEntradas;
import br.upe.processo.Processos;

public class Main {

    // o número de threads
    static final int NUM_THREADS = 2;

    public static void main(String[] args) throws InterruptedException {

        // gerenciador compartilhado entre todas as threads
        GerenciaMemoria gerenciador = new GerenciaMemoria();

        // FabricaDeEntradas gera sequências aleatórias de operações
        // TAM_VIRTUAL precisa estar entre 10 e 40
        FabricaDeEntradas fabrica = new FabricaDeEntradas(GerenciaMemoria.TAM_VIRTUAL);

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
        System.out.println(gerenciador.getVirtual());
    }
}