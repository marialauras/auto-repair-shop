package classes;

import java.util.Scanner;

public class Login {
	List<Funcionario> funcionarios;
	List<Cliente> clientes = new List<Cliente>();

	public Login() {}

	public Login(List<Funcionario> func) {
		this.funcionarios = func;
	}

	public int procuraFuncionario(String login, String senha) {

		for( int i=0; i < funcionarios.tamanho(); i++) {

			if( funcionarios.get(i).getLogin().compareTo(login) ==0 && funcionarios.get(i).getSenha().compareTo(senha)==0) {

				return i;
			}
		}
		return -1;
	}//Função que valida o login e senha dentro do array de funcionarios

	public void login() {
		Scanner entrada = new Scanner( System.in );
		int opcao;	
		
		do {
			System.out.println("================================== TELA DE LOGIN ==================================\n");
			System.out.println("|  1.Fazer login                                                                  |\n");
			System.out.println("|  0.Sair do sistema                                                              |\n");
			System.out.println("===================================================================================\n");


			do {
				System.out.println("Digite qual opção voce deseja: ");
				opcao =  entrada.nextInt();
				entrada.nextLine();
			}while(opcao < 0 || opcao > 1);

			if( opcao == 1 )
			{
				String login;
				String senha;
				System.out.println("Digite seu login: ");
				login = entrada.nextLine();
				System.out.println("Digite sua senha: ");
				senha = entrada.nextLine();

				int encontrado = procuraFuncionario(login, senha);

				if( encontrado  == -1 ) {
					System.out.println("Funcionario não encontrado");
				}
				else{
					funcionarios.get(encontrado).menu(funcionarios,clientes);
				}


			}
			else {
				System.out.println("Você saiu do sistema");
			}
		}while(opcao != 0);
	}
}
