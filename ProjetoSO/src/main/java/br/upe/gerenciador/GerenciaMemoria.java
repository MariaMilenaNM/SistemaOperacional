package br.upe.gerenciador;

import br.upe.algoritmo.AlgoritmoLRU;
import br.upe.memoria.Hd;
import br.upe.memoria.MemoriaFisica;
import br.upe.memoria.MemoriaVirtual;
import br.upe.memoria.Pagina;

public class GerenciaMemoria implements IGerenciador {

    // TAM_VIRTUAL precisa estar entre 10 e 40
    // porque a FabricaDeEntradas exige esse range
    public static final int TAM_FISICA  = 5;   // X
    // está na interface: public static final int TAM_VIRTUAL = 10;  // 2X

    private MemoriaFisica  fisica;
    private MemoriaVirtual virtual;
    private Hd hd;
    private AlgoritmoLRU algoritmo;

    public GerenciaMemoria() {
        this.fisica = new MemoriaFisica(TAM_FISICA);
        this.virtual = new MemoriaVirtual(TAM_VIRTUAL);
        this.hd = new Hd(TAM_VIRTUAL);
        this.algoritmo = new AlgoritmoLRU(TAM_VIRTUAL);
    }

    @Override
    public synchronized void read(int endereco) {
            //pega a tabela daquele endereco virtual
            Pagina pagina = virtual.getPagina(endereco);

            //se pagina nao ta na ram há falta de pagina e trata a falta
            if (!pagina.isPresente()) {
                System.out.println("  >> FALTA DE PAGINA (read) end=" + endereco);
                tratarFaltaPagina(endereco);
            } else {
                System.out.println(" >> NAO HOUVE FALTA DE PAGINA (read)");
            }

            //busca de novo a pagina, pega seu frame,
            // ja esta presente e foi lida
            int moldura = virtual.getPagina(endereco).getMoldura();
            System.out.println("  >> Leitura end=" + endereco + " valor=" + fisica.memoriaFisica[moldura]);

            //indica q n foi escrita, mas referenciada
            pagina.registrarAcesso(false);
            //atualiza ultimo acesso no lru
            algoritmo.registrarAcesso(endereco);
    }

    @Override
    public synchronized void write(int endereco, int valor) {
            Pagina pagina = virtual.getPagina(endereco);

            if (!pagina.isPresente()) {
                System.out.println("  >> FALTA DE PAGINA (write) end=" + endereco);
                tratarFaltaPagina(endereco);
            } else{
                System.out.println(" >> NAO HOUVE FALTA DE PÁGINA (write)");
            }

            int moldura = virtual.getPagina(endereco).getMoldura();
            fisica.memoriaFisica[moldura] = valor;
            System.out.println("  >> Escrita end=" + endereco + " valor=" + valor);

            pagina.registrarAcesso(true);
            algoritmo.registrarAcesso(endereco);
    }

    private void tratarFaltaPagina(int endereco) {
        //Procura um frame na memoria fisica
        int frameLivre = fisica.procuraFrameLivre();
        //se nao houver frame livre ou seja memoria fisica estiver cheia
        if (frameLivre == -1) {
            //chama o algoritmo para entender qual pagina deve ser removida
            int endExpulso  = algoritmo.paginaParaRemover(virtual);
            //pega expulso e moldura
            Pagina expulso  = virtual.getPagina(endExpulso);
            frameLivre     = expulso.getMoldura();

            //se vitima foi modificada
            if (expulso.isModificado()) {
                int valorAtual = fisica.memoriaFisica[frameLivre];
                //ele vai pegar essa expulso e colocar no hd
                hd.escreverDado(endExpulso, valorAtual);
                System.out.println("     SWAP OUT: pagina " + endExpulso + " salva no HD");
            } else {
                //se nao modificada nao precisa reescrever no disco (hd
                System.out.println("     pagina " + endExpulso + " descartada (nao modificada)");
            }

            //tira a pagina da tabela vitual
            expulso.expulsar();
            //remove pagina da fisica
            fisica.removePagina(frameLivre);
        }

        //le o valor do endereco requisitado do hd para o swapin
        int valorDoHd = hd.lerDado(endereco);
        //carrega o valor no frame livre da fisica
        fisica.carregaPagina(valorDoHd, frameLivre);
        //atualiza pagina virtual
        virtual.getPagina(endereco).carregar(frameLivre);

        System.out.println("SWAP IN: pagina " + endereco + " carregada do HD na moldura " + frameLivre);
    }

    // é usado no main para imprimir estados finais
    public MemoriaFisica  getFisica()  { return fisica; }
    public MemoriaVirtual getVirtual() { return virtual; }
}