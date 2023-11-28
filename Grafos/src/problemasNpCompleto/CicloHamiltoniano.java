package problemasNpCompleto;

import java.util.ArrayList;
import grafos.Aresta;
import grafos.Grafo;
import grafos.Vertice;

public class CicloHamiltoniano {
	private Double custoCaminho;
	private ArrayList<Aresta> melhorCaminho;
	private Grafo grafo;

	public CicloHamiltoniano(Grafo grafo) {
		this.setGrafo(grafo);
	}

	public Double getCustoCaminho() {
		return custoCaminho;
	}

	public void setCustoCaminho(Double custoCaminho) {
		this.custoCaminho = custoCaminho;
	}

	public ArrayList<Aresta> getCaminhoMaisCurto() {
		return melhorCaminho;
	}

	public void setCaminhoMaisCurto(ArrayList<Aresta> melhorCaminho) {
		this.melhorCaminho = melhorCaminho;
	}

	public Grafo getGrafo() {
		return grafo;
	}

	public void setGrafo(Grafo grafo) {
		this.grafo = grafo;
	}

	public void melhorCaminho() {
		ArrayList<Vertice> visitados = new ArrayList<Vertice>();
		for (Vertice vertice : this.grafo.vertices()) {
			if (!visitados.contains(vertice)) {
				this.melhorCaminho = backtracking(this.melhorCaminho, visitados, vertice);
			}
		}

		this.calculaCustoCaminho(melhorCaminho);

		System.out.println(this.custoCaminho);
		for (Aresta aresta : melhorCaminho) {
			System.out.print(aresta.origem() + " - ");
		}

	}

	private ArrayList<Aresta> backtracking(ArrayList<Aresta> melhorCaminho, ArrayList<Vertice> visitados,
			Vertice vertice) {
		for (Vertice adjacente : this.grafo.adjacentesDe(vertice)) {
			if (!visitados.contains(adjacente)) {
				visitados.add(adjacente);
				if (this.grafo.existeAresta(vertice, adjacente)) {
					melhorCaminho.addAll(this.grafo.arestasEntre(vertice, adjacente));
				}

				backtracking(melhorCaminho, visitados, adjacente);
			}
		}
		return melhorCaminho;
	}

	private void calculaCustoCaminho(ArrayList<Aresta> melhorCaminho) {
		for (Aresta aresta : melhorCaminho) {
			this.custoCaminho += aresta.peso();
		}
	}
}
