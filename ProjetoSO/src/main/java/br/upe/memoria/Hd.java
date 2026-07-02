package br.upe.memoria;

//swap
public class Hd {
    //estrutura pra armazenar os dados
    public int[] dadosDisco;

    //inicializar o disco: criar paginas preencher com valores
    public Hd(int tamanho){
        dadosDisco = new int[tamanho];
        //ficticio
        for(int i = 0; i< tamanho; i++){
            dadosDisco[i] = i*20;
        }
    }

    //recebe numero da pagina, busca no disco e retorna o valor
    public int lerDado(int endereco){
        return dadosDisco[endereco];
    }


    //atualiza valor da pagina no disco
    public void escreverDado(int endereco, int valor){
        dadosDisco[endereco] = valor;
    }

    /*
    public void exibir(){
        //estrutura da exibicao: pagina x -> valor que tem na pagina
        System.out.println("hd" + Arrays.toString(dadosDisco));
    }
     */
}
