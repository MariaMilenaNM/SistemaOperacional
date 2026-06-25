package br.upe;

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
        this.modificado   = false;
        this.moldura      = moldura;
    }

    //Expulsa a página da memória física
    // modificado nao reseta, GerenciaMemoria precisa checar esse bit antes de chamar expulsar()
    public void expulsar() {
        this.presente = false;
        this.moldura  = -1;
    }

    // Zera bit referenciado (relogio do algoritmo
    public void resetReferenciado() {
        this.referenciado = false;
    }

    // getters 

    public boolean isPresente()     {
        return presente; }
    public boolean isReferenciado() {
        return referenciado; }
    public boolean isModificado()   {
        return modificado; }
    public int     getMoldura()     {
        return moldura; }

    //com o formato que mostra no slide para os bits
    @Override
    public String toString() {
        return String.format("[P=%b R=%b M=%b moldura=%d]",
                presente, referenciado, modificado, moldura);
    }
}
