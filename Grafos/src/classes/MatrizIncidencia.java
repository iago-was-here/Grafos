package classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;

import grafos.Aresta;
import grafos.Vertice;

public class MatrizIncidencia implements grafos.Grafo {

	private Aresta[][] matrizIncidencia;

	public MatrizIncidencia(int numeroDeVertices, LinkedHashMap<String, Double> infosGrafo) {
		double qntArestas = infosGrafo.get("Quantidade Arestas");
		
		this.matrizIncidencia = new Aresta[numeroDeVertices][(int) qntArestas];

		for (int i = 0; i < numeroDeVertices; i++) {
			Vertice origem = new Vertice(i);
			for (int j = 0; j < (int) qntArestas; j++) {
				Boolean existeArestaSaida = infosGrafo.containsKey("Vertice[" + i + "] Aresta[" + j + "] Destino");
				Boolean existeArestaEntrada = infosGrafo.containsKey("Vertice[" + j + "] Aresta[" + i + "] Destino");

				if (existeArestaSaida) {
					double infoDestino = infosGrafo.get("Vertice[" + i + "] Aresta[" + j + "] Destino");
					double infoPeso = infosGrafo.get("Vertice[" + i + "] Aresta[" + j + "] Peso");
					Vertice destino = new Vertice((int) infoDestino);
					this.adicionarAresta(origem, destino, infoPeso);
				}

				if (existeArestaEntrada) {

					Vertice origemIncidente = new Vertice(j);
					double infoDestino = infosGrafo.get("Vertice[" + j + "] Aresta[" + i + "] Destino");
					double infoPeso = infosGrafo.get("Vertice[" + j + "] Aresta[" + i + "] Peso");
					Vertice destino = new Vertice((int) infoDestino);
					
					this.adicionarArestaIncidente(origemIncidente, destino, infoPeso);
				}
			}
		}
	}

	@Override
	public void adicionarAresta(Vertice origem, Vertice destino) {
		this.matrizIncidencia[origem.id()][destino.id()] = new Aresta(origem, destino);
	}

	@Override
	public void adicionarAresta(Vertice origem, Vertice destino, double peso) {
		this.matrizIncidencia[origem.id()][destino.id()] = new Aresta(origem, destino, peso);

	}

	public void adicionarArestaIncidente(Vertice origem, Vertice destino, double peso) {
		this.matrizIncidencia[destino.id()][origem.id()] = new Aresta(origem, destino, peso);
	}

	@Override
	public boolean existeAresta(Vertice origem, Vertice destino) {
		Aresta existe = this.matrizIncidencia[origem.id()][destino.id()];
		if (existe != null) {
			return true;
		}
		return false;
	}

	@Override
	public int grauDoVertice(Vertice vertice) throws Exception {
		int tamanhoLinhaMatriz = this.matrizIncidencia[vertice.id()].length;
		int grauDoVertice = 0;

		for (int i = 0; i < tamanhoLinhaMatriz; i++) {
			if (this.matrizIncidencia[vertice.id()][i] != null) {
				grauDoVertice++;
			}

		}

		return grauDoVertice;
	}

	@Override
	public int numeroDeVertices() {
		return this.matrizIncidencia.length;
	}

	@Override
	public int numeroDeArestas() {
		int numeroArestas = 0;
		int numeroDeVertices = this.numeroDeVertices();

		for (int i = 0; i < numeroDeVertices; i++) {
			Vertice origem = new Vertice(i);
			for (int j = 0; j < numeroDeVertices; j++) {
				Vertice destino = new Vertice(j);
				Boolean existeAresta = this.existeAresta(origem, destino);
				if (existeAresta) {
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

		for (int i = linhaMatriz; i < numeroDeVertices; i++) {
			Vertice origem = new Vertice(i);
			for (int j = 0; j < numeroDeVertices; j++) {
				Vertice destino = new Vertice(j);
				Boolean existeAresta = this.existeAresta(origem, destino);
				if (existeAresta) {
					Vertice adjacente = new Vertice(j);
					adjacentes.add(adjacente);
				}
			}
		}

		return adjacentes;
	}

	@Override
	public void setarPeso(Vertice origem, Vertice destino, double peso) throws Exception {
		this.matrizIncidencia[origem.id()][destino.id()].setarPeso(peso);
	}

	@Override
	public ArrayList<Aresta> arestasEntre(Vertice origem, Vertice destino) {
		return null;
	}

	@Override
	public ArrayList<Vertice> vertices() {
		ArrayList<Vertice> vertices = new ArrayList<Vertice>();
		int numeroDeVertices = this.numeroDeVertices();

		for (int i = 0; i < numeroDeVertices; i++) {
			Vertice vertice = new Vertice(i);
			vertices.add(vertice);
		}

		return vertices;
	}

	@Override
	public String toString() {
		return "MatrizIncidencia [" + Arrays.deepToString(this.matrizIncidencia) + "]";
	}

}
