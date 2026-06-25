package br.upe;

//Cada posição é uma Pagina com seus bits de controle.
public class MemoriaVirtual {

    private Pagina[] paginas;
    private int      tamanho; //2x a fisica

    public MemoriaVirtual(int tamanho) {
        this.tamanho = tamanho;
        this.paginas = new Pagina[tamanho];
        for (int i = 0; i < tamanho; i++) {
            paginas[i] = new Pagina();
        }
    }

    //Retorna a página em um endereço virtual
    public Pagina getPagina(int endereco) {
        return paginas[endereco];
    }

    //Zera o bit referenciado de todas as páginas presentes
    //precisa ser chamado (relogio) pelo GerenciaMemoria
    public void reiniciaRef() {
        for (Pagina p : paginas) {
            if (p.isPresente()) p.resetReferenciado();
        }
    }

    public int getTamanho() { return tamanho; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("MemVirtual:\n");
        for (int i = 0; i < tamanho; i++) {
            sb.append("  [").append(i).append("] ").append(paginas[i]).append("\n");
        }
        return sb.toString();
    }
}

