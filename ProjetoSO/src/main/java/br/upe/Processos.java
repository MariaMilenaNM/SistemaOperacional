package br.upe;

public class Processos implements Runnable {
	
	private String[] operacoes; // Todas as operações "separadamente".
	private int idThread; // id individual do processo.
	private IGerenciador gerenciador = new GerenciaMemoria();
	
	public Processos(int idThread, String entrada, GerenciaMemoria gerenciador) {
		this.idThread = idThread;
		this.operacoes = entrada.split(","); // Divide a string de entrada em operações individuais.
		this.gerenciador = gerenciador;
	}
	
	@Override
	public void run() {
		System.out.println("processo " + idThread + ", iniciado com sucesso. :)");
		try {
			//Loop para passar pelo array de operações.
			for(String operacaoDaVez: operacoes) {
				executarOperacao(operacaoDaVez);
			}
			System.out.println("processo " + idThread + ", finalizado com sucesso. :D");
		} catch (Exception e) {
			System.err.println("Erro no processo numero " + idThread + ". :(");
			e.printStackTrace();
		}
	}
		
	private void executarOperacao(String operacaoDaVez) {
		// um if separa leitura e escrita e chama a função adequada.
		String[] partes = operacaoDaVez.trim().split("-");
		int  endereco   = Integer.parseInt(partes[0]);
		char operacao   = partes[1].charAt(0); // 'R' ou 'W'

		if (operacao == 'R') {
			leitura(endereco);
		} else if (operacao == 'W') {
			int valor = Integer.parseInt(partes[2]);
			escrita(endereco, valor);
		}
	}
	
	public void escrita(int endereco, int valor) {
		System.out.println("Processo" + idThread + ": Escreveu " + valor + ", em index" + endereco + ".");
		gerenciador.write(endereco, valor);
	}
	
	public void leitura(int endereco) {
		// Estou assumindo que o gerenciador retorna o valor lido.
		System.out.println("Processo " + idThread + ": Leu do index" + endereco);
		gerenciador.read(endereco);
	}
}
