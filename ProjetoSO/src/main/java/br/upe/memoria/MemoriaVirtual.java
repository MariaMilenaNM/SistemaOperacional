package br.upe.memoria;

//Cada posição é uma pagina com seus bits de controle.
public class MemoriaVirtual {

    private Pagina[] paginas;
    private int      tamanho; //2x a fisica como jorge pediu

    public MemoriaVirtual(int tamanho) {
        this.tamanho = tamanho;
        this.paginas = new Pagina[tamanho];
        for (int i = 0; i < tamanho; i++) {
            paginas[i] = new Pagina();
        }
    }

    //Retorna a página em um endereço virtual
    //Por exemplo, se a thread acessa o endereço 7 getPagina(7) retorna a ficha da página 7 e o GerenciaMemoria checa pagina.isPresente().
    public Pagina getPagina(int endereco) {
        return paginas[endereco];
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

