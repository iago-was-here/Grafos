package tipoSolucoes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import classes.MatrizAdjacencia;
import grafos.Grafo;
import grafos.Vertice;

public class AlgoritmoGeneticoTSP {
	private MatrizAdjacencia matrizAdjacencia;
	private int tamanhoPopulacao;
	private double taxaCrossover;
	private double taxaMutacao;
	private int numeroGeracoes;

	public AlgoritmoGeneticoTSP(Grafo grafo, int tamanhoPopulacao, double taxaCrossover,
			double taxaMutacao, int numeroGeracoes) {
		this.matrizAdjacencia = (MatrizAdjacencia) grafo;
		this.tamanhoPopulacao = tamanhoPopulacao;
		this.taxaCrossover = taxaCrossover;
		this.taxaMutacao = taxaMutacao;
		this.numeroGeracoes = numeroGeracoes;
	}

	public List<Vertice> resolverTSP() {
		List<List<Vertice>> populacao = gerarPopulacaoInicial();

		for (int geracao = 0; geracao < numeroGeracoes; geracao++) {
			populacao = selecionarEvoluir(populacao);
		}

		return obterMelhorCaminho(populacao);
	}

	private List<List<Vertice>> gerarPopulacaoInicial() {
		List<List<Vertice>> populacao = new ArrayList<>();

		for (int i = 0; i < tamanhoPopulacao; i++) {
			List<Vertice> caminho = new ArrayList<>(matrizAdjacencia.vertices());
			Collections.shuffle(caminho);
			populacao.add(caminho);
		}

		return populacao;
	}

	private List<List<Vertice>> selecionarEvoluir(List<List<Vertice>> populacao) {
		List<List<Vertice>> novaPopulacao = new ArrayList<>();

		while (novaPopulacao.size() < tamanhoPopulacao) {
			List<Vertice> pai1 = selecionarPai(populacao);
			List<Vertice> pai2 = selecionarPai(populacao);

			List<Vertice> filho = crossover(pai1, pai2);
			if (Math.random() < taxaMutacao) {
				mutacao(filho);
			}

			novaPopulacao.add(filho);
		}

		return novaPopulacao;
	}

	private List<Vertice> selecionarPai(List<List<Vertice>> populacao) {
		int torneioSize = 5;
		List<List<Vertice>> torneio = new ArrayList<>();

		for (int i = 0; i < torneioSize; i++) {
			int randomIndex = (int) (Math.random() * populacao.size());
			torneio.add(populacao.get(randomIndex));
		}

		return obterMelhorCaminho(torneio);
	}

	private List<Vertice> crossover(List<Vertice> pai1, List<Vertice> pai2) {
		int pontoCrossover1 = (int) (Math.random() * pai1.size());
		int pontoCrossover2 = (int) (Math.random() * pai1.size());

		int pontoInicial = Math.min(pontoCrossover1, pontoCrossover2);
		int pontoFinal = Math.max(pontoCrossover1, pontoCrossover2);

		List<Vertice> filho = new ArrayList<>(pai1.subList(pontoInicial, pontoFinal + 1));

		for (Vertice gene : pai2) {
			if (!filho.contains(gene)) {
				filho.add(gene);
			}
		}

		return filho;
	}

	private void mutacao(List<Vertice> caminho) {
		int pontoMutacao1 = (int) (Math.random() * caminho.size());
		int pontoMutacao2 = (int) (Math.random() * caminho.size());

		Collections.swap(caminho, pontoMutacao1, pontoMutacao2);
	}

	private List<Vertice> obterMelhorCaminho(List<List<Vertice>> populacao) {
		int melhorIndice = 0;
		double melhorCusto = calcularCusto(populacao.get(0));

		for (int i = 1; i < populacao.size(); i++) {
			double custo = calcularCusto(populacao.get(i));
			if (custo < melhorCusto) {
				melhorCusto = custo;
				melhorIndice = i;
			}
		}

		return populacao.get(melhorIndice);
	}

	public double calcularCusto(List<Vertice> caminho) {
		double custo = 0;

		for (int i = 0; i < caminho.size() - 1; i++) {
			custo += matrizAdjacencia.getPeso(caminho.get(i), caminho.get(i + 1));
		}

		custo += matrizAdjacencia.getPeso(caminho.get(caminho.size() - 1), caminho.get(0)); // Voltar ao início

		return custo;
	}
}
