package br.upe.memoria;

public class Pagina {

    private boolean presente;
    private boolean referenciado;
    private boolean modificado;
    private int     moldura;      // índice em MemoriaFisica, -1 se ausente

    public Pagina() {
        this.presente     = false;
        this.referenciado = false;
        this.modificado   = false;
        this.moldura      = -1;
    }

    // Registra acesso de leitura ou escrita.
    public void registrarAcesso(boolean ehEscrita) {
        this.referenciado = true;
        if (ehEscrita) this.modificado = true;
    }

    // Carrega a página em uma moldura física vinda do HD
    public void carregar(int moldura) {
        this.presente     = true;
        this.referenciado = true;
        this.modificado   = false; //porque a cópia na RAM acabou de vir do disco (as duas cópias são idênticas nesse momento)
        this.moldura      = moldura;
    }

    // modificado não é zerado aqui pq o GerenciaMemoria precisa ler esse bit antes de chamar
    //referenciado também não é zerado vai ser escrito no próximo carregar()
    public void expulsar() {
        this.presente = false;
        this.moldura  = -1;
    }

    // getters
    public boolean isPresente()     {
        return presente; }
    public boolean isModificado()   {
        return modificado; }
    public int     getMoldura()     {
        return moldura; }

    //com o formato que mostra no slide para os bits
    public void exibir() {
        System.out.println("[P=" + presente + " R=" + referenciado +
                " M=" + modificado + " moldura=" + moldura + "]");
    }
}
