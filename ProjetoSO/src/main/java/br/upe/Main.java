package br.upe;

public class Main {
    //testando hd
    //testando memoria fisica
    static Hd hd = new Hd(10);
    static MemoriaFisica mf = new MemoriaFisica(5);

    public static void main(String[] args) {

        hd.exibir();

        System.out.println("Frame livre: " + mf.procuraFrameLivre());

        mf.carregaPagina(100, 0);
        mf.carregaPagina(200, 1);

        mf.consultaConteudo();
        System.out.println("Frame livre: " + mf.procuraFrameLivre());
        mf.removePagina(0);
        mf.consultaConteudo();
        System.out.println("Frame livre: " + mf.procuraFrameLivre());

}}