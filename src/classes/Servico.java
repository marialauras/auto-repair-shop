package classes;

import java.util.Scanner;

public class Servico {
	private String nome;
	private double preco;

	//Destrutor
	public Servico() {}

	//Construtores
	public Servico(Servico s) {
		nome = s.nome;
		preco = s.preco;
	}

	public Servico(String nome, double preco) {
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
			System.out.println("============================== MENU SERVICO =============================\n");
			System.out.println("1: Editar o nome deste servico\n");
			System.out.println("2: Editar o preço deste servico\n");
			System.out.println("3: Imprimir informações sobre este servico\n\n");
			System.out.println("0: Sair deste Menu\n");

			do {
				System.out.println("Digite qual opção voce deseja: ");
				opcao =  entrada.nextInt();
				entrada.nextLine();
			}while(opcao < 0 || opcao > 3);

			String nome;
			double preco;
			switch(opcao) {
			case 1:
				System.out.println("Digite o novo nome deste servico: ");
				nome = entrada.nextLine();
				setNome(nome);
				break;

			case 2:
				System.out.println("Digite o novo preco deste servico: ");
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


	public static Servico criarServico(){

		Servico s = new Servico();
		Scanner entrada = new Scanner( System.in );

		String nome;
		double preco;

		System.out.println("Digite o nome do servico: ");
		nome = entrada.nextLine();
		s.setNome(nome);

		System.out.println("2: Digite preço do servico: ");
		preco = entrada.nextDouble();
		s.setPreco(preco);

		return s;

	}

	//Impressão
	public String toString() {
		return  "Serviço:"+nome + "........................ R$:" + preco;
	}
}
