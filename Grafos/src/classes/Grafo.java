package classes;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import grafos.Aresta;
import grafos.Vertice;

public class Grafo implements grafos.Grafo {

	private LinkedHashMap<String, Object> graph = new LinkedHashMap<String, Object>();

	@Override
	public void adicionarAresta(Vertice origem, Vertice destino) throws Exception {
		Aresta aresta = new Aresta(origem, destino);

		this.graph.put("Vertice[" + origem + "]", aresta);
	}

	@Override
	public void adicionarAresta(Vertice origem, Vertice destino, double peso) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean existeAresta(Vertice origem, Vertice destino) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int grauDoVertice(Vertice vertice) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int numeroDeVertices() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int numeroDeArestas() {
		// TODO Auto-generated method stub
		return 0;
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
