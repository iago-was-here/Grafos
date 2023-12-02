package classes;

import java.util.List;
import java.util.Scanner;

import grafos.Grafo;
import grafos.TipoDeRepresentacao;
import grafos.Vertice;
import tipoSolucoes.AlgoritmoGeneticoTSP;
import tipoSolucoes.TentativaeErro;

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

		escolheMetodoNP(algoritmos);
		// escolheMetodoGrafos(algoritmos);

		scanner.close();
	}

	private static void escolheMetodoNP(AlgoritmosEmGrafos algoritmo) {
		@SuppressWarnings({ "resource", "unused" })
		Scanner scanner = new Scanner(System.in);
		@SuppressWarnings("unused")
		int metodo;
		Grafo grafo = algoritmo.getGrafo();
		System.out.println("Entre com o numero equivalente ao tipo de solução que deseja usar: ");
		System.out.println("1: Tentativa e erro");
		System.out.println("2: Algoritmo genético");
		metodo = scanner.nextInt();

		switch (metodo) {
		case 1: {
			@SuppressWarnings("unused")
			TentativaeErro tentativaErro = new TentativaeErro(1, grafo);
			break;
		}
		case 2: {
			int tamanhoPopulacao = 50;
			double taxaCrossover = 0.8;
			double taxaMutacao = 0.02;
			int numeroGeracoes = 1000;

			AlgoritmoGeneticoTSP ag = new AlgoritmoGeneticoTSP(algoritmo.getGrafo(), tamanhoPopulacao, taxaCrossover,
					taxaMutacao, numeroGeracoes);
			List<Vertice> melhorCaminho = ag.resolverTSP();

			System.out.println("Custo do Melhor Caminho: " + ag.calcularCusto(melhorCaminho));
			System.out.println("Melhor Caminho: " + melhorCaminho);

			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + metodo);
		}

	}

	@SuppressWarnings("unused")
	private static void escolheMetodoGrafos(AlgoritmosEmGrafos algoritmo) {
		Scanner scanner = new Scanner(System.in);
		int metodo;
		Grafo grafo = algoritmo.getGrafo();

		System.out.println("Insira o numero referente ao metodo que deseja executar: ");
		System.out.println("1: " + Metodos.ARVORE_GERADORA_MINIMA);
		System.out.println("2: " + Metodos.BUSCA_EM_lARGURA);
		System.out.println("3: " + Metodos.BUSCA_EM_PROFUNDIDADE);
		System.out.println("4: " + Metodos.CAMINHO_MINIMO);
		System.out.println("5: " + Metodos.FLUXO_MAXIMO);

		metodo = scanner.nextInt();
		try {
			switch (metodo) {
			case 1: {
				System.out.println(algoritmo.agmUsandoKruskall(grafo));
				break;
			}
			case 2: {
				System.out.println("Insira um vertice inicial para busca: ");
				int verticeInicial = scanner.nextInt();

				algoritmo.buscaEmLargura(grafo.vertices().get(verticeInicial));
				break;
			}
			case 3: {
				System.out.println("Insira um vertice inicial para busca: ");
				int verticeInicial = scanner.nextInt();

				algoritmo.buscaEmProfundidade(grafo.vertices().get(verticeInicial));
				break;
			}
			case 4: {
				System.out.println("Insira um vertice de origem: ");
				int origem = scanner.nextInt();
				System.out.println("Insira um vertice de destino: ");
				int destino = scanner.nextInt();

				System.out.println(
						algoritmo.menorCaminho(grafo, grafo.vertices().get(origem), grafo.vertices().get(destino)));
				break;
			}
			case 5: {
				System.out.println("Insira um vertice de origem: ");
				int origem = scanner.nextInt();
				System.out.println("Insira um vertice de destino: ");
				int destino = scanner.nextInt();
				// System.out.println("Fluxo Máximo: "
				// + algoritmo.fluxoMaximo(grafo, grafo.vertices().get(origem),
				// grafo.vertices().get(destino)));
				break;
			}
			default:
				System.out.println("Entrada inválida");
			}
		} catch (Exception e) {

		}

		scanner.close();
	}

}
