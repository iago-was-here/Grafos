package tipoSolucoes;

import grafos.Grafo;
import problemasNpCompleto.CaixeiroViajanteForcaBruta;

public class TentativaeErro {
	public TentativaeErro(int tipoProblema, Grafo grafo) {
		switch (tipoProblema) {
		case 1: {
			CaixeiroViajanteForcaBruta caixeiroViajante = new CaixeiroViajanteForcaBruta(grafo);
			caixeiroViajante.resolver();
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + tipoProblema);
		}
	}
}
