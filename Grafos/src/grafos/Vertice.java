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

	public Vertice(int v) {
		this.vertice = v;
	}

	public int id() {
		return vertice;
	}

	public void setarVertice(int vertice) {
		this.vertice = vertice;
	}

	@Override
	public String toString() {
		return "Vertice [" + vertice + "]";
	}

	 @Override
	    public boolean equals(Object obj) {
	        if (this == obj) {
	            return true;
	        }
	        if (obj == null || getClass() != obj.getClass()) {
	            return false;
	        }
	        Vertice otherVertice = (Vertice) obj;
	        return this.id() == otherVertice.id();
	    }

}
