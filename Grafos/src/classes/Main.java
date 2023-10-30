package classes;

import java.util.ArrayList;
import java.util.Scanner;

import grafos.TipoDeRepresentacao;
import tools.FileManager;

public class Main {
	public static void main(String[] args) {
		int representantionType;
		String filePath;
		AlgoritmosEmGrafos algorithms = new AlgoritmosEmGrafos();

		Scanner scanner = new Scanner(System.in);

		System.out.println("Insira o caminho do arquivo de testes que deseja ler: ");
		filePath = scanner.nextLine();

		System.out.println("Insira o numero referente a estrutura desejada: ");
		System.out.println("1: " + TipoDeRepresentacao.LISTA_DE_ADJACENCIA);
		System.out.println("2: " + TipoDeRepresentacao.MATRIZ_DE_ADJACENCIA);
		System.out.println("3: " + TipoDeRepresentacao.MATRIZ_DE_INCIDENCIA);
		representantionType = scanner.nextInt();

		try {
			algorithms.carregarGrafo(filePath, TipoDeRepresentacao.MATRIZ_DE_ADJACENCIA);
		} catch (Exception e) {
			// TODO: handle exception
		}

		scanner.close();
	}

}
