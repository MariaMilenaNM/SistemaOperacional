package br.upe;

//concluir aqui a lógica e afins quando visualizar a classe memoria virtual e algoritmo LRU

public class GerenciaMemoria {
    public int tamanhoMemoria = 10;
    MemoriaFisica fisica = new MemoriaFisica(tamanhoMemoria);
    MemoriaVirtual virtual = new MemoriaVirtual(2*tamanhoMemoria);
    Hd hd = new Hd(5*tamanhoMemoria);
    AlgoritmoLRU algoritmo = new AlgoritmoLRU();

    //recebe endereco da memoria virtual, operacao leitura/escrita (r/w) e o valor
    public int acessarEndereco (int enderecoVirtual, char operacao, int valor){
        //consultarPaginas
        // se ausente, ocorre falta de pagina e chama tratarFaltaPagina
        if(!virtual.(enderecoVirtual)){ //metodo para dizer se a pagina está na memoria
            System.out.println("Falta de pagina em " + enderecoVirtual);
            tratarFaltaPagina(enderecoVirtual);
        }
        //Pagina na RAM? sim: referencia = 1
        int frameDestino = virtual.//GetParaFrame(enderecoVirtual);
        virtual.//metodopParaSetarReferenciada(enderecoVirtual, 1);
        // se leitura: retorna o valor
        if (operacao == 'r'){
            System.out.println("Valor: " + fisica.memoriaFisica[frameDestino]);
        }
        // se escrita: atualiza e modificada = 1
        else if (operacao == 'w'){
            fisica.memoriaFisica[frameDestino] = valor;
            virtual.//setModificada(enderecoVirtual, 1);
            System.out.println("Escrita do valor " + valor);
        }
        return -1;
    }

    public void tratarFaltaPagina(int enderecoVirtual){
        //na física: tem moldura vazia?
        int frameLivre = fisica.procuraFrameLivre();

        //se nao tem vazia
        if(frameLivre == -1) {
            //Para isso eu ainda preciso ver como ficarão as classes de mem virtual e lru
            int paginaPRemover = algoritmo.paginaParaRemover(); //vai chamar um metodo do algoritmo aqui
            frameLivre = virtual//metodoprapegaroframe(paginaPRemover); // e aqui vai mostrar qual moldura vai ser sobrescrita

            if (virtual.metodo//aqui preciso de um metodo para modificação)
            {
                int valorModificado = fisica.memoriaFisica[frameLivre];
                hd.escreverDado(paginaPRemover, valorModificado); // SWAP OUT
            }
            virtual.//metodopParaSetarPresente(paginaPRemover, 0);
            virtual.//metodopParaSetarModificada(paginaPRemover, 0);
        }
        //ler pag no hd: hd.lerDado
        int valorDoHd = hd.lerDado(enderecoVirtual);
        //carrega para a física
        fisica.carregaPagina(frameLivre, valorDoHd);

        //atualiza tabela de paginas
        virtual.metodo;//metodoParaSetarFrame(enderecoVirtual, frameLivre);
        virtual.metodo;//metodopParaSetarPresente(enderecoVirtual, 1);
        virtual.metodo;//metodoParaSetarModificada(enderecoVirtual, 0);
    }
}

