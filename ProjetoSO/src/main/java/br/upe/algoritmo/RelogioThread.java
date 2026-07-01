package br.upe.algoritmo;

public class RelogioThread extends Thread {
    //volatile para garantir que oq for feito na thread
    //fique visivel para quem le de imediado
    public volatile int relogio;
    public volatile boolean funcionamento;

    public RelogioThread(int relogio){
        this.funcionamento = true;
        this.relogio = relogio;
    }

    //Thread
    @Override
    public void run(){
        //enquanto executar

        while(funcionamento) {
            //incrementar contador
            relogio++;
            // esperar um tempinho (sleep) pra evitar q o contador cresça mt rapido
            try{
                Thread.sleep(100);
        }catch(InterruptedException e){
            Thread.currentThread().interrupt(); // restaura o status de interrupcao
            funcionamento = false;
        }
        }
    }

    //permitir que o algoritmo leia o tempo, logo:
    public int valorAtualContador(){
        return relogio;
    }

    //metodo para desligar relogio
    public void desligaRelogio(){
        funcionamento = false;
    }


}
