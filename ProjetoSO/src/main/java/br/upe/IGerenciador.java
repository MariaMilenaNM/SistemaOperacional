package br.upe;

// Interface que define o contrato de acesso à memória.
// Processos só enxerga essa interface e a
// GerenciaMemoria implementa essa interface
public interface IGerenciador {

    // leitura de um endereço virtual
    void read(int endereco);

    // escrita de um valor em um endereço virtual
    void write(int endereco, int valor);
}
