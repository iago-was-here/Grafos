package problemasNpCompleto;

import java.util.ArrayList;

import classes.MatrizAdjacencia;
import grafos.Grafo;
import grafos.Vertice;

public class CaixeiroViajanteForcaBruta {
	private Double melhorCusto;
	private ArrayList<Vertice> melhorCaminho;
	private MatrizAdjacencia grafo;

	public CaixeiroViajanteForcaBruta(Grafo grafo) {
		this.grafo = (MatrizAdjacencia) grafo;
		this.melhorCusto = Double.MAX_VALUE;
		this.melhorCaminho = new ArrayList<>();
	}

	public Double getMelhorCusto() {
		return melhorCusto;
	}

	public ArrayList<Vertice> getMelhorCaminho() {
		return melhorCaminho;
	}

	public void resolver() {
		ArrayList<Vertice> vertices = new ArrayList<>(grafo.vertices());
		permutacao(vertices, 0);
		System.out.println("Melhor Custo: " + melhorCusto);
		System.out.println("Melhor Caminho: " + melhorCaminho);
	}

	private void permutacao(ArrayList<Vertice> vertices, int indice) {
		if (indice == vertices.size() - 1) {
			double custoAtual = calcularCusto(vertices);
			if (custoAtual < melhorCusto) {
				melhorCusto = custoAtual;
				melhorCaminho = new ArrayList<>(vertices);
			}
		}

		for (int i = indice; i < vertices.size(); i++) {
			swap(vertices, indice, i);
			permutacao(vertices, indice + 1);
			swap(vertices, indice, i);
		}
	}

	private void swap(ArrayList<Vertice> vertices, int i, int j) {
		Vertice temp = vertices.get(i);
		vertices.set(i, vertices.get(j));
		vertices.set(j, temp);
	}

	private double calcularCusto(ArrayList<Vertice> caminho) {
		double custo = 0.0;
		for (int i = 0; i < caminho.size() - 1; i++) {
			custo += grafo.getPeso(caminho.get(i), caminho.get(i + 1));
		}
		custo += grafo.getPeso(caminho.get(caminho.size() - 1), caminho.get(0)); // Voltar ao ponto inicial
		return custo;
	}
}
