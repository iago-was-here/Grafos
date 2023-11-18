package classes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Queue;
import grafos.Aresta;
import grafos.Grafo;
import grafos.TipoDeRepresentacao;
import grafos.Vertice;
import tools.FileManager;

class AlgoritmosEmGrafos implements grafos.AlgoritmosEmGrafos {
	private Grafo grafo;

	public Grafo getGrafo() {
		return this.grafo;
	}

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

					System.out.println(vizinho + "- Pai: " + verticeAtual + " - Tempo de descoberta: - " + tempo);

					arestasEncontradas.addAll(this.grafo.arestasEntre(verticeAtual, vizinho));
				}
			}
		}

		return arestasEncontradas;
	}

	@Override
	public Collection<Aresta> buscaEmProfundidade(Vertice verticeInicial) {
		int tempo[] = { 0 };
		ArrayList<Vertice> visitados = new ArrayList<Vertice>();
		ArrayList<Aresta> arestasEncontradas = new ArrayList<>();

		for (Vertice vertice : this.grafo.vertices()) {
			if (!visitados.contains(vertice)) {
				visitaRecursivamente(vertice, visitados, tempo);
			}
			tempo[0]++;
			System.out.println(vertice + "- Tempo de finalização: " + tempo[0]);
		}

		return null;
	}

	@Override
	public ArrayList<Aresta> menorCaminho(Grafo g, Vertice origem, Vertice destino) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existeCiclo(Grafo g) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Collection<Aresta> agmUsandoKruskall(Grafo g) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double custoDaArvoreGeradora(Grafo g, Collection<Aresta> arestas) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean ehArvoreGeradora(Grafo g, Collection<Aresta> arestas) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Aresta> caminhoMaisCurto(Grafo g, Vertice origem, Vertice destino) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double custoDoCaminho(Grafo g, ArrayList<Aresta> arestas, Vertice origem, Vertice destino) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean ehCaminho(ArrayList<Aresta> arestas, Vertice origem, Vertice destino) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Collection<Aresta> arestasDeArvore(Grafo g) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Aresta> arestasDeRetorno(Grafo g) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Aresta> arestasDeAvanco(Grafo g) {
		return null;
	}

	@Override
	public Collection<Aresta> arestasDeCruzamento(Grafo g) {
		// TODO Auto-generated method stub
		return null;
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

	private void visitaRecursivamente(Vertice vertice, ArrayList<Vertice> visitados, int[] tempo) {
		for (Vertice adjacente : this.grafo.adjacentesDe(vertice)) {
			if (!visitados.contains(adjacente)) {
				visitados.add(adjacente);
				tempo[0]++;
				System.out.println(adjacente + "- Tempo de descoberta: " + tempo[0]);
				visitaRecursivamente(adjacente, visitados, tempo);
			}
		}
	}
}