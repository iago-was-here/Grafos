package classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;

import grafos.Aresta;
import grafos.Grafo;
import grafos.TipoDeRepresentacao;
import grafos.Vertice;
import tools.FileManager;

public class AlgoritmosEmGrafos implements grafos.AlgoritmosEmGrafos {

	@Override
	public Grafo carregarGrafo(String path, TipoDeRepresentacao representationType) throws Exception {
		ArrayList<String> fileContent = new ArrayList<String>();

		fileContent = readFile(path);

		int vertexAmount = Integer.parseInt(fileContent.get(0));
		LinkedHashMap<String, Integer> graphInfo = getGraphInfo(fileContent);

		switch (representationType) {
		case MATRIZ_DE_ADJACENCIA: {
			createAdjacencyMatrix(vertexAmount, graphInfo);
		}
		case MATRIZ_DE_INCIDENCIA: {

		}
		case LISTA_DE_ADJACENCIA: {

		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + representationType);
		}
	}

	@Override
	public Collection<Aresta> buscaEmLargura(Grafo g) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Aresta> buscaEmProfundidade(Grafo g) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Aresta> menorCaminho(Grafo g, Vertice origem, Vertice destino) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existeCiclo(Grafo g) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Collection<Aresta> agmUsandoKruskall(Grafo g) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double custoDaArvoreGeradora(Grafo g, Collection<Aresta> arestas) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean ehArvoreGeradora(Grafo g, Collection<Aresta> arestas) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Aresta> caminhoMaisCurto(Grafo g, Vertice origem, Vertice destino) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double custoDoCaminho(Grafo g, ArrayList<Aresta> arestas, Vertice origem, Vertice destino) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean ehCaminho(ArrayList<Aresta> arestas, Vertice origem, Vertice destino) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Collection<Aresta> arestasDeArvore(Grafo g) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Aresta> arestasDeRetorno(Grafo g) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Aresta> arestasDeAvanco(Grafo g) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Aresta> arestasDeCruzamento(Grafo g) {
		// TODO Auto-generated method stub
		return null;
	}

	protected static ArrayList<String> readFile(String filePath) {
		ArrayList<String> fileContent = new ArrayList<String>();
		FileManager fileManager = new FileManager();

		try {
			fileContent = fileManager.stringReader(filePath);

			return fileContent;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Houve um erro ao ler o arquivo, por favor tente novamete.");
			return null;
		}
	}

	protected static LinkedHashMap<String, Integer> getGraphInfo(ArrayList<String> fileContent) {
		LinkedHashMap<String, Integer> graphInfo = new LinkedHashMap<String, Integer>();

		for (int i = 1; i < fileContent.size(); i++) {
			String lineContent = fileContent.get(i);
			String[] vertexInfo = lineContent.split(" ");

			for (int j = 1; j < vertexInfo.length; j++) {
				String[] vertexInfoSplited = vertexInfo[j].split("-");
				int destiny = Integer.parseInt(vertexInfoSplited[0]);
				int weight = Integer.parseInt(vertexInfoSplited[1].replace(";", ""));

				graphInfo.put("Vertice[" + (i - 1) + "] Aresta[" + j + "] Destino", destiny);
				graphInfo.put("Vertice[" + (i - 1) + "] Aresta[" + j + "] Peso", weight);
			}
		}

		return graphInfo;
	}

	protected static int[][] createAdjacencyMatrix(int vertexAmount, LinkedHashMap<String, Integer> graphInfo) {
		int[][] graphMatrix = new int[vertexAmount][vertexAmount];
		System.out.println(graphInfo.toString());
		for (int i = 0; i < vertexAmount; i++) {
			for (int j = 0; j < vertexAmount; j++) {

			}
		}

		return graphMatrix;
	}

}
