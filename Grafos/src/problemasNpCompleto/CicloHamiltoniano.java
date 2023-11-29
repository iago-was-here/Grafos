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
		this.melhorCaminho = new ArrayList<Aresta>();
		this.custoCaminho = 0.0;
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
				visitados.add(vertice);
				this.custoCaminho = 0.0;
				this.melhorCaminho = backtracking(this.melhorCaminho, visitados, vertice);
				this.calculaCustoCaminho(melhorCaminho);
			}
		}

		System.out.println(this.custoCaminho);
		System.out.println(melhorCaminho.toString());

	}

	private ArrayList<Aresta> backtracking(ArrayList<Aresta> melhorCaminho, ArrayList<Vertice> visitados,
			Vertice vertice) {

		if (melhorCaminho.size() == this.grafo.numeroDeVertices() - 1) {
			Vertice origemCaminho = melhorCaminho.get(0).origem();
			if (this.grafo.existeAresta(vertice, origemCaminho)) {
				melhorCaminho.addAll(this.grafo.arestasEntre(vertice, origemCaminho));
			}
			return melhorCaminho;
		}

		ArrayList<Vertice> adjacentes = this.grafo.adjacentesDe(vertice);
		for (Vertice adjacente : adjacentes) {
			if (!visitados.contains(adjacente)) {
				visitados.add(adjacente);
				if (this.grafo.existeAresta(vertice, adjacente)) {
					melhorCaminho.addAll(this.grafo.arestasEntre(vertice, adjacente));
					backtracking(melhorCaminho, visitados, adjacente);
				}

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
