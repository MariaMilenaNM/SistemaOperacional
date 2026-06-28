package br.upe;

public class Processos implements Runnable {
	
	private String[] operacoes; // Todas as operações separadamente.
	private static int contadorId = 0;
	private int idThread; // id individual do processo.
	private static GerenciaMemoria gerenciador;
	
	public Processos(String entrada) {
		this.idThread = contadorId++;
		this.operacoes = entrada.split(","); 
		// Primeira divisão: operações juntas para individuais.
	}
	
	@Override
	public void run() {
		System.out.println("processo" + idThread + "iniciado com sucesso. :)");
		
		try {
			//Loop para passar pelo array de operações.
			for(String cadaOperacao: operacoes) {
				executarA(cadaOperacao);
			}
		} catch (Exception e) {
			System.err.println("Erro no processo numero " + idThread);
		}
	}
		
	private void executarA(String operacaoDaVez) {
		// um if separa leitura e escrita e chama a função adequada.
		if (operacaoDaVez.contains("-W-")) {
			String[] argumentos = operacaoDaVez.split("-W-");
			int index = Integer.parseInt(argumentos[0]);
			int valor = Integer.parseInt(argumentos[1]);
			Escrita(index, valor);
		} else if (operacaoDaVez.contains("-R")) {
			int index = Integer.parseInt(operacaoDaVez);
			Leitura(index);
		}
	}
	
	public void Escrita(int index, int valor) {
		gerenciador.acessarEndereco(index, 'w', valor);
		System.out.println("Processo" + idThread + ": Escreveu " + valor + ": em index" + index + ".'");
	}
	
	public void Leitura(int index) {
		// Estou assumindo que o gerenciador retorna o valor lido.
		int valor = gerenciador.acessarEndereco(index, 'r', -1);
		System.out.println("Processo" + idThread + ": Leu " + valor + ": do index" + index + ".'");
	}
}
