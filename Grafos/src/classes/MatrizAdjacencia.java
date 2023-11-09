package classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;

import grafos.Aresta;
import grafos.Vertice;

public class MatrizAdjacencia implements grafos.Grafo {

	private Aresta[][] matrizAdjacencia;

	public MatrizAdjacencia(int numeroDeVertices, LinkedHashMap<String, Double> infosGrafo) {
		this.matrizAdjacencia = new Aresta[numeroDeVertices][numeroDeVertices];

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
		this.matrizAdjacencia[origem.id()][destino.id()] = new Aresta(origem, destino);
	}

	@Override
	public void adicionarAresta(Vertice origem, Vertice destino, double peso) {
		this.matrizAdjacencia[origem.id()][destino.id()] = new Aresta(origem, destino, peso);
	}

	@Override
	public boolean existeAresta(Vertice origem, Vertice destino) {
		Aresta existe = this.matrizAdjacencia[origem.id()][destino.id()];
		if (existe != null) {
			return true;
		}
		return false;
	}

	@Override
	public int grauDoVertice(Vertice vertice) throws Exception {
		int numeroDeVertices = this.numeroDeVertices();
		int grauDoVertice = 0;

		for (int i = 0; i < numeroDeVertices; i++) {
			Vertice origem = new Vertice(i);
			for (int j = 0; j < numeroDeVertices; j++) {

				Vertice destino = new Vertice(j);

				if (this.existeAresta(origem, destino) && origem.id() == vertice.id()) {
					grauDoVertice++;
				}

				if (this.existeAresta(destino, origem) && origem.id() == vertice.id()) {
					grauDoVertice++;
				}
			}
		}

		return grauDoVertice;
	}

	@Override
	public int numeroDeVertices() {
		return this.matrizAdjacencia.length;
	}

	@Override
	public int numeroDeArestas() {
		int numeroArestas = 0;
		int numeroDeVertices = this.numeroDeVertices();

		for (int i = 0; i < numeroDeVertices; i++) {
			Vertice origem = new Vertice(i);
			for (int j = 0; j < numeroDeVertices; j++) {
				Vertice destino = new Vertice(j);

				if (this.existeAresta(origem, destino)) {
					numeroArestas++;
				}
			}
		}
		return numeroArestas;
	}

	@Override
	public ArrayList<Vertice> adjacentesDe(Vertice vertice) {
		ArrayList<Vertice> adjacentes = new ArrayList<Vertice>();
		int linhaMatriz = vertice.id();
		int numeroDeVertices = this.numeroDeVertices();

		for (int i = 0; i < numeroDeVertices; i++) {
			if (this.matrizAdjacencia[linhaMatriz][i] != null) {
				adjacentes.add(this.matrizAdjacencia[linhaMatriz][i].destino());
			}
		}

		return adjacentes;
	}

	@Override
	public void setarPeso(Vertice origem, Vertice destino, double peso) throws Exception {
		this.matrizAdjacencia[origem.id()][destino.id()].setarPeso(peso);
	}

	@Override
	public ArrayList<Aresta> arestasEntre(Vertice origem, Vertice destino) {
		ArrayList<Aresta> arestas = new ArrayList<>();

		if (this.matrizAdjacencia[origem.id()][destino.id()] != null) {
			arestas.add(this.matrizAdjacencia[origem.id()][destino.id()]);
		}

		return arestas;
	}

	@Override
	public ArrayList<Vertice> vertices() {
		ArrayList<Vertice> vertices = new ArrayList<Vertice>();
		int numeroDeVertices = this.numeroDeVertices();

		for (int i = 0; i < numeroDeVertices; i++) {
			for (int j = 0; j < numeroDeVertices; j++) {
				if (this.matrizAdjacencia[i][j] != null) {
					vertices.add(this.matrizAdjacencia[i][j].origem());
				}
			}
		}

		return vertices;
	}

	@Override
	public String toString() {
		return "Matriz de Adjacencia =" + Arrays.deepToString(this.matrizAdjacencia) + "]";
	}

}
