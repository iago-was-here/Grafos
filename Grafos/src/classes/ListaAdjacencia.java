package classes;

import java.util.ArrayList;
import java.util.List;

import grafos.Aresta;
import grafos.Grafo;
import grafos.Vertice;

public class ListaAdjacencia implements Grafo {

	private List<Aresta> ListaAdjacencia;

	public ListaAdjacencia() {

	}

	@Override
	public void adicionarAresta(Vertice origem, Vertice destino) throws Exception {
		Aresta aresta = new Aresta(origem, destino);
		this.ListaAdjacencia.add(aresta);
	}

	@Override
	public void adicionarAresta(Vertice origem, Vertice destino, double peso) throws Exception {
		Aresta aresta = new Aresta(origem, destino, peso);
		this.ListaAdjacencia.add(aresta);
	}

	@Override
	public boolean existeAresta(Vertice origem, Vertice destino) {
		Aresta arestaAux = new Aresta(origem, destino);
		Boolean existeAresta = this.ListaAdjacencia.contains(arestaAux);

		if (existeAresta) {
			return true;
		}
		return false;
	}

	@Override
	public int grauDoVertice(Vertice vertice) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int numeroDeVertices() {
		return this.ListaAdjacencia.size();
	}

	@Override
	public int numeroDeArestas() {
		int numeroArestas = 0;
		int tamanhoLista = this.ListaAdjacencia.size();

		return numeroArestas;
	}

	@Override
	public ArrayList<Vertice> adjacentesDe(Vertice vertice) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setarPeso(Vertice origem, Vertice destino, double peso) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<Aresta> arestasEntre(Vertice origem, Vertice destino) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Vertice> vertices() {
		// TODO Auto-generated method stub
		return null;
	}

}
