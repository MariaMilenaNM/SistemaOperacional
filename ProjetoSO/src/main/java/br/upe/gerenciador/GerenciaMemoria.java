package br.upe.gerenciador;

import java.util.concurrent.locks.ReentrantLock;

import br.upe.algoritmo.AlgoritmoLRU;
import br.upe.memoria.Hd;
import br.upe.memoria.MemoriaFisica;
import br.upe.memoria.MemoriaVirtual;
import br.upe.memoria.Pagina;

public class GerenciaMemoria implements IGerenciador {

    // TAM_VIRTUAL precisa estar entre 10 e 40
    // porque a FabricaDeEntradas exige esse range
    public static final int TAM_FISICA  = 5;   // X
    public static final int TAM_VIRTUAL = 10;  // 2X

    private MemoriaFisica  fisica;
    private MemoriaVirtual virtual;
    private Hd             hd;
    private AlgoritmoLRU   algoritmo;

    private final ReentrantLock lock = new ReentrantLock();

    public GerenciaMemoria() {
        this.fisica    = new MemoriaFisica(TAM_FISICA);
        this.virtual   = new MemoriaVirtual(TAM_VIRTUAL);
        this.hd        = new Hd(TAM_VIRTUAL);
        this.algoritmo = new AlgoritmoLRU(TAM_VIRTUAL);
    }

    @Override
    public void read(int endereco) {
        lock.lock();
        try {
            Pagina pagina = virtual.getPagina(endereco);

            if (!pagina.isPresente()) {
                System.out.println("  >> FALTA DE PAGINA (read) end=" + endereco);
                tratarFaltaPagina(endereco);
            }

            int moldura = virtual.getPagina(endereco).getMoldura();
            System.out.println("  >> Leitura end=" + endereco
                    + " valor=" + fisica.memoriaFisica[moldura]);

            pagina.registrarAcesso(false);
            algoritmo.registrarAcesso(endereco);

        } finally {
            lock.unlock();
        }
    }

    @Override
    public void write(int endereco, int valor) {
        lock.lock();
        try {
            Pagina pagina = virtual.getPagina(endereco);

            if (!pagina.isPresente()) {
                System.out.println("  >> FALTA DE PAGINA (write) end=" + endereco);
                tratarFaltaPagina(endereco);
            }

            int moldura = virtual.getPagina(endereco).getMoldura();
            fisica.memoriaFisica[moldura] = valor;
            System.out.println("  >> Escrita end=" + endereco + " valor=" + valor);

            pagina.registrarAcesso(true);
            algoritmo.registrarAcesso(endereco);

        } finally {
            lock.unlock();
        }
    }

    private void tratarFaltaPagina(int endereco) {
        int frameLivre = fisica.procuraFrameLivre();

        if (frameLivre == -1) {
            int endVitima  = algoritmo.paginaParaRemover(virtual);
            Pagina vitima  = virtual.getPagina(endVitima);
            frameLivre     = vitima.getMoldura();

            if (vitima.isModificado()) {
                int valorAtual = fisica.memoriaFisica[frameLivre];
                hd.escreverDado(endVitima, valorAtual);
                System.out.println("     SWAP OUT: pagina " + endVitima + " salva no HD");
            } else {
                System.out.println("     pagina " + endVitima + " descartada (nao modificada)");
            }

            vitima.expulsar();
            fisica.removePagina(frameLivre);
        }

        int valorDoHd = hd.lerDado(endereco);
        fisica.carregaPagina(valorDoHd, frameLivre);
        virtual.getPagina(endereco).carregar(frameLivre);

        System.out.println("     SWAP IN: pagina " + endereco
                + " carregada do HD na moldura " + frameLivre);
    }

    public MemoriaFisica  getFisica()  { return fisica; }
    public MemoriaVirtual getVirtual() { return virtual; }
}