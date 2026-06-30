package br.upe;

public class Processos implements Runnable {

	private int          idThread;
	private String[]     operacoes;
	private IGerenciador gerenciador;

	public Processos(int idThread, String entrada, IGerenciador gerenciador) {
		this.idThread    = idThread;
		this.operacoes   = entrada.split(",");
		this.gerenciador = gerenciador;
	}

	@Override
	public void run() {
		System.out.println("Processo " + idThread + " iniciado.");

		for (String cadaOperacao : operacoes) {
			try {
				// formato da FabricaDeEntradas: "endereco-R" ou "endereco-W-valor"
				String[] partes = cadaOperacao.trim().split("-");
				int  endereco   = Integer.parseInt(partes[0]);
				char operacao   = partes[1].charAt(0); // 'R' ou 'W'

				if (operacao == 'R') {
					Leitura(endereco);
				} else if (operacao == 'W') {
					int valor = Integer.parseInt(partes[2]);
					Escrita(endereco, valor);
				}

			} catch (Exception e) {
				System.err.println("Erro no processo " + idThread
						+ " op=[" + cadaOperacao + "]: " + e.getMessage());
			}
		}

		System.out.println("Processo " + idThread + " finalizado.");
	}

	public void Leitura(int endereco) {
		System.out.println("[Thread " + idThread + "] Leitura end=" + endereco);
		gerenciador.read(endereco);
	}

	public void Escrita(int endereco, int valor) {
		System.out.println("[Thread " + idThread + "] Escrita end=" + endereco
				+ " val=" + valor);
		gerenciador.write(endereco, valor);
	}
}