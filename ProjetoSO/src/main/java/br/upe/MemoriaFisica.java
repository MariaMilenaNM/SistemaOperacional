package br.upe;

import java.util.Arrays;

public class MemoriaFisica {
    public int[]     memoriaFisica;
    private boolean[] ocupado; // true = frame em uso, false = frame livre

    public MemoriaFisica(int tamanho) {
        memoriaFisica = new int[tamanho];
        ocupado       = new boolean[tamanho];
        // não precisa mais de valor sentinela (-1)
        // quem controla se o frame está livre é o array ocupado
        Arrays.fill(ocupado, false);
    }

    // procura frame livre pelo array ocupado, não pelo valor armazenado
    public int procuraFrameLivre() {
        for (int i = 0; i < ocupado.length; i++) {
            if (!ocupado[i]) return i;
        }
        return -1; // memória cheia
    }

    public void carregaPagina(int valorPagina, int frameLivre) {
        memoriaFisica[frameLivre] = valorPagina;
        ocupado[frameLivre]       = true; // marca como ocupado
    }

    public void removePagina(int frame) {
        memoriaFisica[frame] = 0;
        ocupado[frame]       = false; // marca como livre
    }

    public void consultaConteudo() {
        System.out.println("MemFisica: " + Arrays.toString(memoriaFisica));
        System.out.println("Ocupado:   " + Arrays.toString(ocupado));
    }
}