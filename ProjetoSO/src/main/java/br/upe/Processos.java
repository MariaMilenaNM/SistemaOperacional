package br.upe;

public class Processos implements Runnable {

	private String entrada;
	private static int idThread = 0;
	private String[] operacoes; 
	// pelo que entendi do diagrama vai vir de uma classe separada da main
	
	private GerenciamentoMemoria gerenciador;
	// pelo diagrama não entendi bem como ele chega aqui mas acho que pela main
	// que tambem deve definir o tamanho da memoria, ou talvez seja o retorno 
	// de uma função e o atributo é estatico
	
	@Override
	public void run() {
		System.out.println("processo" + idThread + "iniciado com sucesso. :)");
		
		//Loop para passar pelo array de operações.
		for(String cadaOperacao: operacoes) {
			try {
				// .slip-operacoes para inicializar as variaveis.
				// estou supondo que elas vem na forma "operação-endereço-valor"
				// e separados por virgula.
				String[] corte = cadaOperacao.split(",");
				char operacao = corte[0].charAt(0);
				// essa linha acima ainda me deixa confuso.
				int index = Integer.parseInt(corte[1]);
				int valor = Integer.parseInt(corte[2]);
				
				// um if para separar as operações.
				if (operacao == 'L') {
					Leitura(index, valor);
				} else if (operacao == 'E') {
					Escrita(index, valor);
				}
				
				
			} catch (Exception e) {
				System.err.println("Erro no processo numero " + idThread);
			}
		}
		
	}
	
	public Processos(GerenciamentoMemoria gerenciador) {
		this.entrada = entrada;
		this.idThread = idThread;
		idThread++;
		this.operacoes = entrada.split(",");
		
		this.gerenciador = gerenciador;
	}
	
	// precisam de um idex da memoria e um valor para receber/enviar
	public void Escrita(int index, int valor) {
		gerenciador.acessarEndereco(index, 'E', valor);
		System.out.println("Processo" + idThread + ": Escreveu " + valor + ": em index" + index + ".'");
	}
	
	public void Leitura(int index, int valor) {
		gerenciador.acessarEndereco(index, 'L', valor);
		System.out.println("Processo" + idThread + ": Leu " + valor + ": do index" + index + ".'");
	}
	
}
