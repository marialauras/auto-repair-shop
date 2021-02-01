package classes;

import java.util.Scanner;

public class Peca {
	private String nome;
	private double preco;


	//Construtores
	public Peca() {}

	public Peca(Peca p) {
		nome = p.nome;
		preco = p.preco;
	}

	public Peca(String nome, double preco) {
		this.nome = nome;
		this.preco = preco;
	}

	//Getters
	public String getNome() {
		return nome;
	}

	public double getPreco() {
		return preco;
	}

	//Setters
	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	//Menu
	public void menu() {
		int opcao;
		Scanner entrada = new Scanner( System.in );
		do {
			System.out.println("============================== MENU PE�A =============================\n");
			System.out.println("1: Editar o nome desta pe�a\n");
			System.out.println("2: Editar o pre�o desta pe�a\n");
			System.out.println("3: Imprimir informa��es sobre esta pe�a\n\n");
			System.out.println("0: Sair deste Menu\n");

			do {
				System.out.println("Digite qual op��o voce deseja: ");
				opcao =  entrada.nextInt();
				entrada.nextLine();
			}while(opcao < 0 || opcao > 3);

			String nome;
			double preco;
			switch(opcao) {
			case 1:
				System.out.println("Digite o novo nome desta pe�a: ");
				nome = entrada.nextLine();
				setNome(nome);
				break;

			case 2:
				System.out.println("Digite o novo preco desta pe�a: ");
				preco = entrada.nextDouble();
				setPreco(preco);
				break;

			case 3:
				System.out.println(toString());
				break;

			default:
				break;	
			}
		}while(opcao != 0);
	}

	//Adicionar Pe�a
	public static Peca criarPeca(){

		Peca p = new Peca();
		Scanner entrada = new Scanner( System.in );

		String nome;
		double preco;

		System.out.println("Digite o nome da peca: ");
		nome = entrada.nextLine();
		p.setNome(nome);

		System.out.println("2: Digite pre�o da pe�a: ");
		preco = entrada.nextDouble();
		p.setPreco(preco);

		return p;

	}

	//Impress�o
	public String toString() {
		return  "Pe�a: "+nome + " ............R$:" + preco;
	}

}
