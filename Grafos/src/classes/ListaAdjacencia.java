package classes;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import grafos.Aresta;
import grafos.Vertice;

public class ListaAdjacencia implements grafos.Grafo {

	private ArrayList<Aresta>[] listaAdjacencia;

	@SuppressWarnings("unchecked")
	public void iniciarLista(int numeroDeVertices) {
		this.listaAdjacencia = new ArrayList[numeroDeVertices];
		for (int i = 0; i < numeroDeVertices; i++) {
			this.listaAdjacencia[i] = new ArrayList<Aresta>();
		}
	}

	public ListaAdjacencia(int numeroDeVertices, LinkedHashMap<String, Double> infosGrafo) {

		iniciarLista(numeroDeVertices);

		for (int i = 0; i < numeroDeVertices; i++) {
			Vertice origem = new Vertice(i);
			for (int j = 0; j < numeroDeVertices; j++) {
				Boolean existeAresta = infosGrafo.containsKey("Vertice[" + i + "] Aresta[" + j + "] Destino");

				if (existeAresta) {
					double infoDestino = infosGrafo.get("Vertice[" + i + "] Aresta[" + j + "] Destino");
					double infoPeso = infosGrafo.get("Vertice[" + i + "] Aresta[" + j + "] Peso");
					Vertice destino = new Vertice((int) infoDestino);

					this.adicionarAresta(origem, destino, infoPeso);
				}
			}
		}
	}

	@Override
	public void adicionarAresta(Vertice origem, Vertice destino) {
		Aresta aresta = new Aresta(origem, destino);
		this.listaAdjacencia[origem.id()].add(aresta);
	}

	@Override
	public void adicionarAresta(Vertice origem, Vertice destino, double peso) {
		Aresta aresta = new Aresta(origem, destino, peso);
		this.listaAdjacencia[origem.id()].add(aresta);
	}

	@Override
	public boolean existeAresta(Vertice origem, Vertice destino) {
		ArrayList<Aresta> arestas = this.listaAdjacencia[origem.id()];

		for (Aresta aresta : arestas) {
			if (aresta.origem().id() == origem.id() && aresta.destino().id() == destino.id()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int grauDoVertice(Vertice vertice) throws Exception {
		int grauDoVertice = 0;
		int numeroDeVertices = this.numeroDeVertices();

		for (int i = 0; i < numeroDeVertices; i++) {
			int qntArestas = this.listaAdjacencia[i].size();
			for (int j = 0; j < qntArestas; j++) {
				Aresta aresta = this.listaAdjacencia[i].get(j);
				if (aresta.destino().id() == vertice.id()) {
					grauDoVertice++;
				}

				if (aresta.origem().id() == vertice.id()) {
					grauDoVertice++;
				}
			}
		}
		return grauDoVertice;
	}

	@Override
	public int numeroDeVertices() {
		return this.listaAdjacencia.length;
	}

	@Override
	public int numeroDeArestas() {
		int numeroArestas = 0;
		int tamanhoLista = this.listaAdjacencia.length;

		for (int i = 0; i < tamanhoLista; i++) {
			numeroArestas = this.listaAdjacencia[i].size() + numeroArestas;
		}

		return numeroArestas;
	}

	@Override
	public ArrayList<Vertice> adjacentesDe(Vertice vertice) {
		ArrayList<Vertice> adjacentes = new ArrayList<Vertice>();
		int linha = vertice.id();

		this.listaAdjacencia[linha].forEach((aresta) -> {
			adjacentes.add(aresta.destino());
		});

		return adjacentes;
	}

	@Override
	public void setarPeso(Vertice origem, Vertice destino, double peso) throws Exception {
		Aresta arestaAux = new Aresta(origem, destino);
		this.listaAdjacencia[origem.id()].forEach((aresta) -> {
			if (aresta.equals(arestaAux)) {
				aresta.setarPeso(peso);
			}
		});
	}

	@Override
	public ArrayList<Aresta> arestasEntre(Vertice origem, Vertice destino) {
		ArrayList<Aresta> arestasEntre = new ArrayList<Aresta>();
		this.listaAdjacencia[origem.id()].forEach((aresta)->{
			if (aresta.destino().equals(destino)) {
	            arestasEntre.add(aresta);
	        }
		});
		return arestasEntre;
	}

	@Override
	public ArrayList<Vertice> vertices() {
		ArrayList<Vertice> vertices = new ArrayList<Vertice>();

		for (int i = 0; i < this.numeroDeVertices(); i++) {
			this.listaAdjacencia[i].forEach((aresta) -> {
				if (!vertices.contains(aresta.origem())) {
					vertices.add(aresta.origem());
				}
			});
		}

		return vertices;
	}

	@Override
	public String toString() {
		return "ListaAdjacencia [" + Arrays.deepToString(listaAdjacencia) + "]";
	}

}
