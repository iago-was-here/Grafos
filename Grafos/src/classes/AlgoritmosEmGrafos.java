package classes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import grafos.Aresta;
import grafos.Grafo;
import grafos.TipoDeRepresentacao;
import grafos.Vertice;
import tools.FileManager;

public class AlgoritmosEmGrafos implements grafos.AlgoritmosEmGrafos {

	private static final int BRANCO = 0;
	private static final int CINZA = 1;
	private static final int PRETO = 2;
	private Grafo grafo;

	@Override
	public Grafo carregarGrafo(String path, TipoDeRepresentacao tipoRepresentacao) {
		ArrayList<String> conteudoArquivo = new ArrayList<String>();

		conteudoArquivo = lerArquivo(path);

		String primeiraLinhaArquivo = conteudoArquivo.get(0);

		int numeroDeVertices = Integer.parseInt(primeiraLinhaArquivo);
		LinkedHashMap<String, Double> infosGrafo = criarInfosGrafo(conteudoArquivo);

		switch (tipoRepresentacao) {
		case MATRIZ_DE_ADJACENCIA: {
			this.grafo = new MatrizAdjacencia(numeroDeVertices, infosGrafo);
			return null;
		}
		case MATRIZ_DE_INCIDENCIA: {
			this.grafo = new MatrizIncidencia(numeroDeVertices, infosGrafo);
			System.out.println(this.grafo.toString());
			return null;
		}
		case LISTA_DE_ADJACENCIA: {
			this.grafo = new ListaAdjacencia(numeroDeVertices, infosGrafo);
			return null;
		}
		default:
			return null;
		}
	}

	@Override
	public Collection<Aresta> buscaEmLargura(Vertice verticeInicial) {
		if (this.grafo == null || verticeInicial == null) {
			return new ArrayList<>();
		}

		Queue<Vertice> fila = new LinkedList<>();
		ArrayList<Vertice> visitados = new ArrayList<>();
		ArrayList<Aresta> arestasEncontradas = new ArrayList<>();

		int tempo = 0;

		fila.add(verticeInicial);
		visitados.add(verticeInicial);

		while (!fila.isEmpty()) {
			Vertice verticeAtual = fila.poll();
			tempo++;
			for (Vertice vizinho : this.grafo.adjacentesDe(verticeAtual)) {
				if (!visitados.contains(vizinho)) {
					fila.add(vizinho);
					visitados.add(vizinho);

					System.out.println("Pai: [" + +verticeAtual.id() + "]" + " - Vertice: [" + vizinho.id()
							+ "] - Tempo de Descoberta: - " + tempo);

					arestasEncontradas.addAll(this.grafo.arestasEntre(verticeAtual, vizinho));
				}
			}
		}

		return arestasEncontradas;
	}

	@Override
	public Collection<Aresta> buscaEmProfundidade(Vertice verticeInicial) {
		int numeroDeVertices = this.grafo.numeroDeVertices();
		int tempo = 0;
		Vertice antecessorPadrao = new Vertice(-1);

		for (int i = 0; i < numeroDeVertices; i++) {
			this.grafo.vertices().get(i).setCor(BRANCO);
			this.grafo.vertices().get(i).setAntecessor(antecessorPadrao);
		}

		for (Vertice vertice : this.grafo.adjacentesDe(verticeInicial)) {
			if (vertice.getCor() == BRANCO) {
				tempo = this.visitaDfs(vertice, tempo);
			}
		}

		System.out.println("Arestas de Arvores");
		for (Aresta aresta : this.arestasDeArvore(this.grafo)) {
			System.out.println(aresta.toString());
		}

		System.out.println();
		System.out.println("Arestas de Retorno");
		for (Aresta aresta : this.arestasDeRetorno(this.grafo)) {
			System.out.println(aresta.toString());
		}

		System.out.println();
		System.out.println("Arestas de Avanço");
		for (Aresta aresta : this.arestasDeAvanco(this.grafo)) {
			System.out.println(aresta.toString());
		}

		System.out.println();
		System.out.println("Arestas de Cruzamento");
		for (Aresta aresta : this.arestasDeCruzamento(this.grafo)) {
			System.out.println(aresta.toString());
		}
		return null;
	}

	@Override
	public ArrayList<Aresta> menorCaminho(Grafo g, Vertice origem, Vertice destino) throws Exception {
		Map<Vertice, Double> distancia = new HashMap<>();
		Map<Vertice, Aresta> arestaAnterior = new HashMap<>();
		PriorityQueue<Vertice> filaDePrioridade = new PriorityQueue<>(
				(v1, v2) -> Double.compare(distancia.get(v1), distancia.get(v2)));

		for (Vertice vertice : grafo.vertices()) {
			if (vertice.equals(origem)) {
				distancia.put(vertice, 0.0);
			} else {
				distancia.put(vertice, Double.MAX_VALUE);
			}
			filaDePrioridade.offer(vertice);
		}

		while (!filaDePrioridade.isEmpty()) {
			Vertice u = filaDePrioridade.poll();

			for (Vertice v : grafo.adjacentesDe(u)) {
				Aresta aresta = grafo.arestasEntre(u, v).get(0);
				double pesoAresta = aresta.peso();

				if (distancia.get(v) > distancia.get(u) + pesoAresta) {
					distancia.put(v, distancia.get(u) + pesoAresta);
					arestaAnterior.put(v, aresta);

					filaDePrioridade.remove(v);
					filaDePrioridade.offer(v);
				}
			}
		}

		ArrayList<Aresta> caminhoMinimo = new ArrayList<>();
		Vertice verticeAtual = destino;
		while (verticeAtual != null && arestaAnterior.containsKey(verticeAtual)) {
			Aresta aresta = arestaAnterior.get(verticeAtual);
			caminhoMinimo.add(aresta);
			verticeAtual = aresta.origem();
		}

		Collections.reverse(caminhoMinimo);

		return caminhoMinimo;
	}

	@Override
	public boolean existeCiclo(Grafo g) {
		Set<Vertice> visitados = new HashSet<>();
		Set<Vertice> pilhaDeRecursao = new HashSet<>();

		for (Vertice vertice : grafo.vertices()) {
			if (!visitados.contains(vertice)) {
				if (dfsDetectaCiclo(vertice, visitados, pilhaDeRecursao)) {
					return true;
				}
			}
		}

		return false;
	}

	@Override
	public Collection<Aresta> agmUsandoKruskall(Grafo g) {
		ArrayList<Aresta> arestasAGM = new ArrayList<>();
		Set<Vertice> conjuntoDe = new HashSet<>();

		ArrayList<Aresta> todasArestas = new ArrayList<>();
		for (Vertice origem : grafo.vertices()) {
			for (Vertice destino : grafo.adjacentesDe(origem)) {
				todasArestas.addAll(grafo.arestasEntre(origem, destino));
			}
		}

		Collections.sort(todasArestas, Comparator.comparingDouble(Aresta::peso));

		for (Aresta aresta : todasArestas) {
			Vertice origem = aresta.origem();
			Vertice destino = aresta.destino();

			if (!conjuntoDe.contains(origem) || !conjuntoDe.contains(destino)) {
				arestasAGM.add(aresta);
				conjuntoDe.add(origem);
				conjuntoDe.add(destino);
			}
		}

		return arestasAGM;
	}

	@Override
	public double custoDaArvoreGeradora(Grafo g, Collection<Aresta> arestas) throws Exception {
		double custoTotal = 0.0;

		for (Aresta aresta : arestas) {
			custoTotal += aresta.peso();
		}

		return custoTotal;
	}

	@Override
	public boolean ehArvoreGeradora(Grafo g, Collection<Aresta> arestas) {
		int numVertices = grafo.numeroDeVertices();
		int numArestas = arestas.size();

		if (numArestas != numVertices - 1) {
			return false; // A quantidade de arestas não é a esperada para uma Árvore Geradora
		}

		Set<Vertice> visitados = new HashSet<>();

		for (Aresta aresta : arestas) {
			Vertice origem = aresta.origem();
			Vertice destino = aresta.destino();

			if (!visitados.contains(origem)) {
				visitados.add(origem);
			}

			if (!visitados.contains(destino)) {
				visitados.add(destino);
			} else {
				return false; // Contém um vértice repetido, portanto, não é uma Árvore Geradora
			}
		}

		if (visitados.size() == numVertices) {
			return true; // É uma Árvore Geradora válida
		}

		return false;
	}

	@Override
	public ArrayList<Aresta> caminhoMaisCurto(Grafo g, Vertice origem, Vertice destino) {
		return null;
	}

	@Override
	public double custoDoCaminho(Grafo g, ArrayList<Aresta> arestas, Vertice origem, Vertice destino) throws Exception {
		double custo = 0.0;
		Vertice atual = origem;

		for (Aresta aresta : arestas) {
			if (!aresta.origem().equals(atual)) {
				throw new Exception("Arestas não formam um caminho válido.");
			}

			custo += aresta.peso();
			atual = aresta.destino();
		}

		if (!atual.equals(destino)) {
			throw new Exception("O último vértice do caminho não é o destino desejado.");
		}

		return custo;
	}

	@Override
	public boolean ehCaminho(ArrayList<Aresta> arestas, Vertice origem, Vertice destino) {
		if (arestas.isEmpty()) {
			return false; // Um caminho deve conter pelo menos uma aresta.
		}

		if (!arestas.get(0).origem().equals(origem)) {
			return false; // O primeiro vértice do caminho deve ser o vértice de origem.
		}

		Vertice atual = origem;

		for (Aresta aresta : arestas) {
			if (!grafo.existeAresta(aresta.origem(), aresta.destino())) {
				return false; // Aresta inexistente no grafo.
			}

			if (!aresta.origem().equals(atual)) {
				return false; // Aresta não começa no vértice atual.
			}

			atual = aresta.destino();
		}

		return atual.equals(destino);
	}

	@Override
	public Collection<Aresta> arestasDeArvore(Grafo g) {
		if (this.grafo == null) {
			return new ArrayList<>();
		}

		Queue<Vertice> fila = new LinkedList<>();
		Set<Vertice> visitados = new HashSet<>();
		ArrayList<Aresta> arestasArvore = new ArrayList<>();

		for (Vertice vertice : this.grafo.vertices()) {
			if (!visitados.contains(vertice)) {
				fila.add(vertice);
				visitados.add(vertice);

				while (!fila.isEmpty()) {
					Vertice verticeAtual = fila.poll();
					for (Vertice vizinho : this.grafo.adjacentesDe(verticeAtual)) {
						if (!visitados.contains(vizinho)) {
							fila.add(vizinho);
							visitados.add(vizinho);

							arestasArvore.addAll(this.grafo.arestasEntre(verticeAtual, vizinho));
						}
					}
				}
			}
		}

		return arestasArvore;
	}

	@Override
	public Collection<Aresta> arestasDeRetorno(Grafo g) {
		if (this.grafo == null) {
			return new ArrayList<>();
		}

		Set<Aresta> arestasDeRetorno = new HashSet<>();
		Set<Vertice> visitados = new HashSet<>();
		Set<Vertice> pilhaDeRecursao = new HashSet<>();

		for (Vertice vertice : grafo.vertices()) {
			if (!visitados.contains(vertice)) {
				dfsArestasDeRetorno(vertice, visitados, pilhaDeRecursao, arestasDeRetorno);
			}
		}

		return new ArrayList<>(arestasDeRetorno);
	}

	@Override
	public Collection<Aresta> arestasDeAvanco(Grafo g) {
		if (this.grafo == null) {
			return new ArrayList<>();
		}

		Set<Aresta> arestasDeAvanco = new HashSet<>();
		Set<Vertice> visitados = new HashSet<>();

		for (Vertice vertice : grafo.vertices()) {
			if (!visitados.contains(vertice)) {
				dfsArestasDeAvanco(vertice, visitados, arestasDeAvanco);
			}
		}

		return new ArrayList<>(arestasDeAvanco);
	}

	public double fluxoMaximo(Grafo g, Vertice origem, Vertice destino) {
		FluxoMaximo fluxoMaximo = new FluxoMaximo();
		return fluxoMaximo.fordFulkerson(g, origem, destino);
	}

	@Override
	public Collection<Aresta> arestasDeCruzamento(Grafo g) {
		if (this.grafo == null) {
			return new ArrayList<>();
		}

		Set<Aresta> arestasDeCruzamento = new HashSet<>();
		Set<Vertice> visitados = new HashSet<>();
		Map<Vertice, Vertice> pai = new HashMap<>();

		for (Vertice vertice : grafo.vertices()) {
			if (!visitados.contains(vertice)) {
				dfsArestasDeCruzamento(vertice, visitados, pai, arestasDeCruzamento);
			}
		}

		return new ArrayList<>(arestasDeCruzamento);
	}

	public Grafo getGrafo() {
		return this.grafo;
	}

	private static ArrayList<String> lerArquivo(String caminhoArquivo) {
		ArrayList<String> conteudoArquivo = new ArrayList<String>();
		FileManager gerenciadorArquivos = new FileManager();

		try {
			conteudoArquivo = gerenciadorArquivos.stringReader(caminhoArquivo);

			return conteudoArquivo;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Houve um erro ao ler o arquivo, por favor tente novamete.");
			return null;
		}
	}

	private static LinkedHashMap<String, Double> criarInfosGrafo(ArrayList<String> conteudoArquivo) {
		LinkedHashMap<String, Double> infosGrafo = new LinkedHashMap<String, Double>();
		double qntArestas = 0;

		for (int i = 1; i < conteudoArquivo.size(); i++) {

			String conteudoLinha = conteudoArquivo.get(i);
			String[] infosVertice = conteudoLinha.split(" ");

			for (int j = 1; j < infosVertice.length; j++) {

				String[] infosVerticeDivido = infosVertice[j].split("-");
				double destino = Integer.parseInt(infosVerticeDivido[0]);
				double peso = Integer.parseInt(infosVerticeDivido[1].replace(";", ""));

				infosGrafo.put("Vertice[" + (i - 1) + "] Aresta[" + j + "] Destino", destino);
				infosGrafo.put("Vertice[" + (i - 1) + "] Aresta[" + j + "] Peso", peso);
				qntArestas++;
			}
		}

		infosGrafo.put("Quantidade Arestas", qntArestas);
		return infosGrafo;
	}

	private boolean dfsDetectaCiclo(Vertice vertice, Set<Vertice> visitados, Set<Vertice> pilhaDeRecursao) {
		visitados.add(vertice);
		pilhaDeRecursao.add(vertice);

		for (Vertice vizinho : grafo.adjacentesDe(vertice)) {
			if (!visitados.contains(vizinho)) {
				if (dfsDetectaCiclo(vizinho, visitados, pilhaDeRecursao)) {
					return true;
				}
			} else if (pilhaDeRecursao.contains(vizinho)) {
				return true;
			}
		}

		pilhaDeRecursao.remove(vertice);
		return false;
	}

	private void dfsArestasDeRetorno(Vertice vertice, Set<Vertice> visitados, Set<Vertice> pilhaDeRecursao,
			Set<Aresta> arestasDeRetorno) {
		visitados.add(vertice);
		pilhaDeRecursao.add(vertice);

		for (Vertice vizinho : grafo.adjacentesDe(vertice)) {
			if (!visitados.contains(vizinho)) {
				dfsArestasDeRetorno(vizinho, visitados, pilhaDeRecursao, arestasDeRetorno);
			} else if (pilhaDeRecursao.contains(vizinho)) {
				// Aresta que forma um ciclo (aresta de retorno)
				Aresta aresta = grafo.arestasEntre(vertice, vizinho).get(0);
				if (!arestasDeRetorno.contains(aresta)) {
					arestasDeRetorno.add(aresta);
				}

			}
		}

		pilhaDeRecursao.remove(vertice);
	}

	private void dfsArestasDeAvanco(Vertice vertice, Set<Vertice> visitados, Set<Aresta> arestasDeAvanco) {
		visitados.add(vertice);

		for (Vertice vizinho : grafo.adjacentesDe(vertice)) {
			if (!visitados.contains(vizinho)) {
				// Aresta que avança na direção da busca
				Aresta aresta = grafo.arestasEntre(vertice, vizinho).get(0);
				if (!arestasDeAvanco.contains(aresta)) {
					arestasDeAvanco.add(aresta);
				}

				dfsArestasDeAvanco(vizinho, visitados, arestasDeAvanco);
			}
		}
	}

	private void dfsArestasDeCruzamento(Vertice vertice, Set<Vertice> visitados, Map<Vertice, Vertice> pai,
			Set<Aresta> arestasDeCruzamento) {
		visitados.add(vertice);

		for (Vertice vizinho : grafo.adjacentesDe(vertice)) {
			if (!visitados.contains(vizinho)) {
				// Aresta que conecta duas subárvores distintas
				Aresta aresta = grafo.arestasEntre(vertice, vizinho).get(0);
				if (!arestasDeCruzamento.contains(aresta)) {
					arestasDeCruzamento.add(aresta);
				}

				pai.put(vizinho, vertice);
				dfsArestasDeCruzamento(vizinho, visitados, pai, arestasDeCruzamento);
			} else if (!vizinho.equals(pai.get(vertice))) {
				// Aresta que forma um ciclo, mas não é de avanço
				Aresta aresta = grafo.arestasEntre(vertice, vizinho).get(0);
				if (!arestasDeCruzamento.contains(aresta)) {
					arestasDeCruzamento.add(aresta);
				}
			}
		}
	}

	private int visitaDfs(Vertice vertice, int tempo) {
		if (vertice.getCor() == BRANCO) {
			vertice.setCor(CINZA);
			tempo++;
			vertice.setTempoDescoberta(tempo);
			ArrayList<Vertice> adjacentes = this.grafo.adjacentesDe(vertice);

			System.out.println(vertice.toString() + " - Tempo de Descoberta: " + vertice.getTempoDescoberta());
			if (adjacentes != null) {
				for (Vertice adjacente : adjacentes) {

					if (adjacente.getCor() == BRANCO) {
						adjacente.setAntecessor(vertice);

						tempo = visitaDfs(adjacente, tempo);
					}
				}

				vertice.setCor(PRETO);
				vertice.setTempoFinalizacao(tempo);
				System.out.println(vertice.toString() + " - Tempo de Finalização: " + vertice.getTempoFinalizacao());
			}
		}

		return tempo;
	}
}
