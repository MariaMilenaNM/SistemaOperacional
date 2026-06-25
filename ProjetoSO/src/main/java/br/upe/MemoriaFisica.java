package br.upe;

import java.util.Arrays;

public class MemoriaFisica {
    public int[] memoriaFisica;

    public MemoriaFisica(int tamanho) {
        memoriaFisica = new int[tamanho];
        Arrays.fill(memoriaFisica, -1);
    }

    //procura frame livre
    public int procuraFrameLivre() {
        for (int i = 0; i < memoriaFisica.length; i++) {
            if (memoriaFisica[i] == -1) {
                //retorna posicao vazia
                return i;
            }
        }
        //retorna -1 caso nao haja posicao vazia
        return -1;
    }

    public void carregaPagina(int valorPagina, int frameLivre){
        //valor do hd para saber onde será colocada a pagina
        memoriaFisica[frameLivre] = valorPagina;


    }

    public void removePagina(int frame){
        //libera um frame
        //coloca o valor novamente como vazio
        memoriaFisica[frame] = -1;
    }

    //auxilia nos testes e mostra páginas carregadas
    public void consultaConteudo(){
        System.out.println(Arrays.toString(memoriaFisica));

    }

}