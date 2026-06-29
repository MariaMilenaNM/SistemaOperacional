package br.upe;

public class Processos implements Runnable {
	
	private String[] operacoes; // Todas as operações "separadamente".
	private static int contadorId = 0;
	private int idThread; // id individual do processo.
	private GerenciaMemoria gerenciador = new GerenciaMemoria();
	
	public Processos(GerenciaMemoria gerenciador, String entrada) {
		this.gerenciador = gerenciador;
		this.idThread = contadorId++;
		this.operacoes = entrada.split(","); // Divide a string de entrada em operações individuais.
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
		if (operacaoDaVez.contains("-W-")) {
			String[] argumentos = operacaoDaVez.split("-W-");
			int index = Integer.parseInt(argumentos[0]);
			int valor = Integer.parseInt(argumentos[1]);
			escrita(index, valor);
		} else if (operacaoDaVez.contains("-R")) {
			String argumento = operacaoDaVez.replace("-R", "");
			int index = Integer.parseInt(argumento);
			leitura(index);
		} else {
			System.out.println("Operação indefinida: " + operacaoDaVez + ", no processo: " + idThread + ". :O");
		}
	}
	
	public void escrita(int index, int valor) {
		gerenciador.acessarEndereco(index, 'w', valor);
		System.out.println("Processo" + idThread + ": Escreveu " + valor + ", em index" + index + ".");
	}
	
	public void leitura(int index) {
		// Estou assumindo que o gerenciador retorna o valor lido.
		int valor = gerenciador.acessarEndereco(index, 'r', 0);
		System.out.println("Processo" + idThread + ": Leu " + valor + ", do index" + index + ".");
	}
}
