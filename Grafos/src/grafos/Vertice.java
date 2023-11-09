/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package grafos;

/**
 *
 * @author humberto e douglas
 */
public class Vertice {
	private int vertice;
	private Vertice antecessor;
	private int tempoDescoberta;
	private int tempoFinalizacao;
	private int cor;
	private boolean visitado;

	public Vertice(int v) {
		this.vertice = v;
	}

	public int id() {
		return vertice;
	}

	public void setarVertice(int vertice) {
		this.vertice = vertice;
	}

	public int getTempoDescoberta() {
		return tempoDescoberta;
	}

	public void setTempoDescoberta(int tempoDescoberta) {
		this.tempoDescoberta = tempoDescoberta;
	}

	public int getTempoFinalizacao() {
		return tempoFinalizacao;
	}

	public void setTempoFinalizacao(int tempoFinalizacao) {
		this.tempoFinalizacao = tempoFinalizacao;
	}

	public int getCor() {
		return cor;
	}

	public void setCor(int cor) {
		this.cor = cor;
	}

	public boolean isVisitado() {
		return visitado;
	}

	public void setVisitado(boolean visitado) {
		this.visitado = visitado;
	}

	public Vertice getAntecessor() {
		return antecessor;
	}

	public void setAntecessor(Vertice antecessor) {
		this.antecessor = antecessor;
	}

	@Override
	public String toString() {
		return "Vertice [" + vertice + "]";
	}

}
