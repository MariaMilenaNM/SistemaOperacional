package br.upe.memoria;

import java.util.Arrays;

//ram
public class MemoriaFisica {
    public int[] memoriaFisica; //valor que esta no frame
    private boolean[] ocupado; // true = frame em uso, false = frame livre

    public MemoriaFisica(int tamanho) {
        memoriaFisica = new int[tamanho]; //todo zerado padrao
        ocupado = new boolean[tamanho]; //todo false padrao
        // não precisa mais de valor sentinela (-1)
        // quem controla se o frame está livre é o array ocupado
    }

    // procura frame livre pelo array ocupado, não pelo valor armazenado
    public int procuraFrameLivre() {
        for (int i = 0; i < ocupado.length; i++) {
            if (!ocupado[i]) return i;
        }
        return -1; // memória cheia
    }

    public void carregaPagina(int valorPagina, int frameLivre) {
        memoriaFisica[frameLivre] = valorPagina; //valor que veio do hd nesse frame
        ocupado[frameLivre] = true; // marca como ocupado
    }

    public void removePagina(int frame) {
        memoriaFisica[frame] = 0;
        ocupado[frame] = false; // marca como livre
    }

    public void consultaConteudo() {
        System.out.println("MemFisica: " + Arrays.toString(memoriaFisica));
        System.out.println("Ocupado:   " + Arrays.toString(ocupado));
    }
}