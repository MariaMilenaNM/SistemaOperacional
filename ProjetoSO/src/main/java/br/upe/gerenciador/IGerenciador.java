package br.upe.gerenciador;

import br.upe.memoria.MemoriaFisica;
import br.upe.memoria.MemoriaVirtual;

// Interface que define o contrato de acesso à memória.
// Processos só enxerga essa interface e a
// GerenciaMemoria implementa essa interface
public interface IGerenciador {

    public static final int TAM_VIRTUAL = 10; // 2X

    // leitura de um endereço virtual
    void read(int endereco);

    // escrita de um valor em um endereço virtual
    void write(int endereco, int valor);

    MemoriaFisica getFisica();

    MemoriaVirtual getVirtual();
}
