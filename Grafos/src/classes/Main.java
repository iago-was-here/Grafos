package classes;

import java.util.Scanner;

import grafos.TipoDeRepresentacao;

public class Main {
	public static void main(String[] args) {
		int tipoRepresentacao;
		String caminhoArquivo;
		AlgoritmosEmGrafos algoritmos = new AlgoritmosEmGrafos();

		Scanner scanner = new Scanner(System.in);

		System.out.println("Insira o caminho do arquivo de testes que deseja ler: ");
		caminhoArquivo = scanner.nextLine();

		System.out.println("Insira o numero referente a estrutura desejada: ");
		System.out.println("1: " + TipoDeRepresentacao.LISTA_DE_ADJACENCIA);
		System.out.println("2: " + TipoDeRepresentacao.MATRIZ_DE_ADJACENCIA);
		System.out.println("3: " + TipoDeRepresentacao.MATRIZ_DE_INCIDENCIA);
		tipoRepresentacao = scanner.nextInt();

		try {
			switch (tipoRepresentacao) {
			case 1: {
				algoritmos.carregarGrafo(caminhoArquivo, TipoDeRepresentacao.LISTA_DE_ADJACENCIA);
				break;
			}
			case 2: {
				algoritmos.carregarGrafo(caminhoArquivo, TipoDeRepresentacao.MATRIZ_DE_ADJACENCIA);
				break;
			}
			case 3: {
				algoritmos.carregarGrafo(caminhoArquivo, TipoDeRepresentacao.MATRIZ_DE_INCIDENCIA);
				break;
			}
			default:
				System.out.println("Entre com uma estrutura válida!");
			}

		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
			System.out.println("Houve um erro ao carregar o grafo, por favor tente novamente");
		}

		scanner.close();
	}

}
