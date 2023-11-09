/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package grafos;

import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author humberto e douglas
 */
public interface AlgoritmosEmGrafos {

    /**
     * Carrega grafo do arquivo texto. O formato será definido do site da disciplina
     * @param path
     * @return um objeto grafo com as informações representadas no arquivo.
     * @throws java.lang.Exception Caminho inválido ou árquivo fora do padrão.
     */
    public Grafo carregarGrafo(String path, TipoDeRepresentacao t) throws Exception;

    /**
     * Realiza busca em largura no grafo 
     * @param g Grafo
     * @return as arestas da árvore resultante
     */
    public Collection<Aresta> buscaEmLargura (Vertice verticeInicial);
    
    /**
     * Realiza a busca em profundidade no grafo
     * @param g Grafo
     * @return as arestas da floresta resultante
     */
    public Collection<Aresta> buscaEmProfundidade (Vertice verticeInicial);
    
    /**
     * Função que indica o menor caminho entre dois pontos. Nesta função, é
     * considerada a quantidade de arestas entre origem e destino. Não são
     * considerados os pesos das arestas.
     * @return um vetor com as arestas quem fazem o menor caminho entre a origem
     * e o destino.
     * @throws java.lang.Exception Caso não exista caminho, uma exception é lançada.
     */
    public ArrayList<Aresta> menorCaminho(Grafo g, Vertice origem, Vertice destino) throws Exception;

    /**
     * Verifica se existe ciclo no grafo.
     * @param g Grafo.
     * @return True, se existe ciclo, False, em caso contrário.
     */
    public boolean existeCiclo(Grafo g);

    
    /**
     * Retorna a árvore geradora mínima utilizando o algoritmo Kruskall.
     * @param g O grafo.
     * @return Retorna a árvore geradora mínima utilizando o algoritmo Kruscall.
     */
    public Collection<Aresta> agmUsandoKruskall(Grafo g);
    
    /**
     * Calcula o custo de uma árvore geradora.
     * @param arestas As arestas que compoem a árvore geradora.
     * @param g O grafo.
     * @return O custo da árvore geradora.
     * @throws java.lang.Exception Se a árvore apresentada não é geradora do grafo.
     */
    public double custoDaArvoreGeradora(Grafo g, Collection<Aresta> arestas) throws Exception;
    
    /**
     * Testa se a árvore é geradora.
     * @param g
     * @param arestas
     * @return True, se a árvore é árvore geradora, False, caso contrário.
     */
    public boolean ehArvoreGeradora( Grafo g, Collection<Aresta> arestas );
    
    /**
     * Retorna (em ordem) as arestas que compoem o caminho mais curto 
     * entre um par de vértices. Esta função considera o pesa das arestas
     * para composição do caminho mais curto.
     * @param g
     * @param origem
     * @param destino
     * @return As arestas (em ordem) do caminho mais curto.
     */
    public ArrayList<Aresta> caminhoMaisCurto( Grafo g, Vertice origem, Vertice destino );
    
    /**
     * Dado um caminho, esta função calcula o custo do caminho.
     * @param arestas
     * @param g
     * @param origem
     * @param destino
     * @return o custo da caminho.
     * @throws java.lang.Exception Se a sequencia apresentada não é um caminho
     * entre origem e destino.
     */
    public double custoDoCaminho( Grafo g, ArrayList<Aresta> arestas, Vertice origem, Vertice destino ) throws Exception;
    
    /**
     * Verifica se a sequencia de arestas é caminho entre oriem e destino.
     * @param arestas
     * @param origem
     * @param destino
     * @return True, se é caminho. False, caso contrário.
     */
    public boolean ehCaminho( ArrayList<Aresta> arestas, Vertice origem, Vertice destino );
    
    /**
     * Arestas de arvore.
     * @param g
     * @return As arestas de arvore do grafo g.
     */
    public Collection<Aresta> arestasDeArvore(Grafo g);
    
    /**
     * Arestas de retorno.
     * @param g
     * @return As arestas de retorno do grafo g.
     */
    public Collection<Aresta> arestasDeRetorno(Grafo g);
    
    /**
     * Arestas de avanço.
     * @param g
     * @return As arestas de avanço do grafo g.
     */
    public Collection<Aresta> arestasDeAvanco(Grafo g);
    
    /**
     * Arestas de cruzamento.
     * @param g
     * @return As arestas de cruzamento do grafo g.
     */
    public Collection<Aresta> arestasDeCruzamento(Grafo g);

    
}
