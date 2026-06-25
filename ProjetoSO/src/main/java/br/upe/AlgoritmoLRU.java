package br.upe;

// a logica dele: cada página tem um contador de "tempo do último acesso" 
//a eliminada é a página com o menor contador entre as presentes.

public class AlgoritmoLRU {

    // contador global de acessos que vai incrementado a cada acesso
    private int relogio;

    // último tempo de acesso por página (indexado pelo endereço virtual)
    private int[] ultimoAcesso;

    public AlgoritmoLRU(int tamanhoMemVirtual) {
        this.relogio      = 0;
        this.ultimoAcesso = new int[tamanhoMemVirtual];
    }

    // Marca um acesso à página no endereço informado
    // ele deve ser chamado pelo GerenciaMemoria após cada leitura ou escrita
    public void registrarAcesso(int endereco) {
        ultimoAcesso[endereco] = ++relogio;
    }

    //Escolhe a página que vai sair para ser substituída, consulta a MemoriaVirtual para saber quais páginas estão presentes
    //depois disso retorna o endereço virtual da que foi usada há mais tempo
    public int paginaParaRemover(MemoriaVirtual memVirtual) {
        int vitima    = -1;
        int menorTempo = Integer.MAX_VALUE;

        for (int i = 0; i < memVirtual.getTamanho(); i++) {
            Pagina p = memVirtual.getPagina(i);
            if (p.isPresente() && ultimoAcesso[i] < menorTempo) {
                menorTempo = ultimoAcesso[i];
                vitima     = i;
            }
        }

        return vitima;
    }
}
