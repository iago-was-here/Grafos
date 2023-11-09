package classes;

import java.util.ArrayDeque;
import java.util.Queue;

import grafos.Aresta;
import grafos.Grafo;
import grafos.Vertice;

public class FluxoMaximo {
	public double fordFulkerson(Grafo grafo, Vertice origem, Vertice destino) {
		double fluxoMaximo = 0;
		double[] peso = new double[grafo.numeroDeVertices()];

		while (true) {
			double caminhoAumentante = encontrarCaminhoAumentante(grafo, origem, destino, peso);
			if (caminhoAumentante == 0) {
				break;
			}

			fluxoMaximo += caminhoAumentante;

			Vertice atual = origem;
			while (!atual.equals(destino)) {
				Vertice anterior = peso[atual.id()] >= 0 ? grafo.adjacentesDe(atual).get(0)
						: grafo.adjacentesDe(atual).get(1);
				peso[atual.id()] += caminhoAumentante;
				peso[anterior.id()] -= caminhoAumentante;
				atual = anterior;
			}
		}

		return fluxoMaximo;
	}

	private static double encontrarCaminhoAumentante(Grafo grafo, Vertice origem, Vertice destino, double[] peso) {
		int numVertices = grafo.numeroDeVertices();
		boolean[] visitado = new boolean[numVertices];
		double[] capacidadeMinima = new double[numVertices];
		capacidadeMinima[origem.id()] = Double.MAX_VALUE;

		Queue<Vertice> fila = new ArrayDeque<>();
		fila.add(origem);
		visitado[origem.id()] = true;

		while (!fila.isEmpty()) {
			Vertice atual = fila.poll();

			for (Vertice vizinho : grafo.adjacentesDe(atual)) {
				if (!visitado[vizinho.id()] && peso[vizinho.id()] < capacidadeMinima[vizinho.id()]) {
					capacidadeMinima[vizinho.id()] = Math.min(capacidadeMinima[atual.id()],
							grafo.arestasEntre(atual, vizinho).get(0).peso());
					if (vizinho.equals(destino)) {
						return capacidadeMinima[destino.id()];
					}
					fila.add(vizinho);
					visitado[vizinho.id()] = true;
				}
			}
		}

		return 0;
	}
}
