package classes;

import java.util.Scanner;

public class Cliente extends Pessoa {
	public Cliente( String nome, String cpf, String endereco, String telefone ) {
		super( nome, cpf,endereco,telefone);
	}
	private Veiculo veiculo;


	//Construtor vazio
	public Cliente() {}


	//Getters  	
	public Veiculo getVeiculo() {
		return veiculo;
	}


	//Setters
	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	//Criação de cliente
	public static Cliente criarCliente() {
		Scanner entrada = new Scanner( System.in );
		Cliente c = new Cliente();
		String str;

		System.out.println("Digite nome do cliente:");
		str = entrada.nextLine();
		c.setNome(str);

		System.out.println("Digite o CPF do cliente:");
		str = entrada.nextLine();
		c.setCpf(str);

		System.out.println("Digite o endereço do cliente:");
		str = entrada.nextLine();
		c.setEndereco(str);

		System.out.println("Digite o telefone do cliente:");
		str = entrada.nextLine();
		c.setTelefone(str);

		c.setVeiculo(Veiculo.criarVeiculo());

		return c;
	}//Função que retorna um cliente alocado



	//Impressão
	@Override
	public String toString() {
		return super.toString() + (veiculo == null ? "\nnão há veiculos cadastrados" :("\n"+ veiculo.toString()));
	}

}
