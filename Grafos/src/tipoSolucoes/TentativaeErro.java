package tipoSolucoes;

import grafos.Grafo;
import problemasNpCompleto.CicloHamiltoniano;

public class TentativaeErro {
	public TentativaeErro(int tipoProblema, Grafo grafo) {
		switch (tipoProblema) {
		case 1: {
			CicloHamiltoniano caixeiroViajante = new CicloHamiltoniano(grafo);
			caixeiroViajante.melhorCaminho();
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + tipoProblema);
		}
	}
}
