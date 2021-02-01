package classes;

import java.util.Scanner;

public abstract class Pessoa {
	private String nome;
	private String cpf;
	private String endereco;
	private String telefone;


	//Construtores
	public Pessoa() {}

	public Pessoa(Pessoa p) {
		this.nome = p.nome;
		this.cpf = p.cpf;
		this.endereco = p.endereco;
		this.telefone = p.telefone;
	}

	public Pessoa(String nome, String cpf, String endereco, String telefone) {
		this.nome = nome;
		this.cpf = cpf;
		this.endereco = endereco;
		this.telefone = telefone;
	}


	//Getters
	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}

	public String getEndereco() {
		return endereco;
	}

	public String getTelefone() {
		return telefone;
	}



	//Setters
	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	//Menu de edição de dados
	public void editarDadosPessoais() {
		Scanner entrada = new Scanner( System.in );
		int opcao;
		do {
			System.out.println("==================== EDITAR DADOS PESSOAIS ====================\n");
			System.out.println("1: Editar nome\n");
			System.out.println("2: Editar cpf\n");
			System.out.println("3: Editar endereco\n");
			System.out.println("4: Editar telefone\n");
			System.out.println("5: Visualizar informações\n\n");
			System.out.println("0: Sair do menu\n");

			do {
				System.out.println("Digite qual opção voce deseja: ");
				opcao =  entrada.nextInt();
				entrada.nextLine();
			}while(opcao < 0 || opcao > 5);

			String str;
			switch(opcao) {
			case 1:
				System.out.println("Digite o novo nome:");
				str = entrada.nextLine();
				setNome(str);
				break;

			case 2:
				System.out.println("Digite o novo cpf:");
				str = entrada.nextLine();
				setCpf(str);
				break;

			case 3:
				System.out.println("Digite o novo endereço:");
				str = entrada.nextLine();
				setEndereco(str);
				break;

			case 4:
				System.out.println("Digite o novo telefone:");
				str = entrada.nextLine();
				setTelefone(str);
				break;

			case 5:
				System.out.println(toString());
				break;

			default:
				break;

			}
		}while(opcao != 0);

	}


	//Impressão
	@Override
	public String toString() {

		return "Dados Pessoais:\n"
				+ "nome=" + nome + ", \ncpf=" + cpf + ", \nendereco=" + endereco + ", \ntelefone=" + telefone;
	}

}



