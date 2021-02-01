package classes;

import java.util.Scanner;

public abstract class Funcionario extends Pessoa {
	public Funcionario( String nome, String cpf, String endereco, String telefone ) {
		super( nome, cpf,endereco,telefone);
		this.login="(invalido)";
		this.senha="(invalido)";
	}//Construtor
	private String login;
    private String senha;
    
    
    
    //Construtores
    public Funcionario(Funcionario f) {
 		login = f.login;
 		senha = f.senha;
 	}
    
    public Funcionario(String login, String senha) {
    	
    	this.setNome("Não expecificado");
    	this.setCpf("Não expecificado");
    	this.setEndereco("Não expecificado");
    	this.setTelefone("Não expecificado");
    	
		this.login = login;
		this.senha = senha;
	}
    
    
	//Setters
	public String getLogin() {
		return login;
	}
	public String getSenha() {
		return senha;
	}

    
	//Gettters
	public void setLogin(String login) {
		this.login = login;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	//Menu
	public abstract void menu(List<Funcionario> funcs, List<Cliente> clientes);
	
	//Editar dadados de login
	public void editarDadosLogin() {
			Scanner entrada = new Scanner( System.in);
			int opcao;
			do {
				System.out.println("================Editar Dados ==================\n");
				System.out.println("1: Editar login\n");
				System.out.println("2: Editar senha\n");
				System.out.println("3: Visualizar dados\n\n");
				System.out.println("0: Sair do menu\n");
				do {
					System.out.println("Digite qual opção voce deseja: ");
					opcao =  entrada.nextInt();
					entrada.nextLine();
				}while(opcao < 0 || opcao > 3);
				
				if(opcao == 1 ) {
					String login;
					System.out.println("Digite o novo login: ");
					login = entrada.nextLine();
					setLogin(login);
				}
				else if( opcao == 2) {
					String senha;
					System.out.println("Digite a nova senha: ");
					senha = entrada.nextLine();
					setSenha(senha);
				}
				else if( opcao == 3) {
					System.out.println(toString());
				}
					
			}while(opcao !=0);

	}
	
	
	//Menu de edição de dados
	public void editarDados() {
		Scanner entrada = new Scanner( System.in );
		int opcao;
		do {
			System.out.println("================Editar Dados ==================\n");
			System.out.println("1: Editar dados Pessoais\n");
			System.out.println("2: Editar dados de Login\n\n");
			System.out.println("0: Sair do Menu\n\n");

			do {
				System.out.println("Digite qual opção voce deseja: ");
				opcao = entrada.nextInt();
				entrada.nextLine();
			}while(opcao < 0 || opcao > 2);
			
			if(opcao == 1 ) {
				super.editarDadosPessoais();
				
			}
			else if(opcao == 2 ) {
				editarDadosLogin();
			}
		}while( opcao != 0);
	}		
	//Impressão
	@Override
	public String toString() {
		return super.toString()+"\nDados do sistema: [login=" + login + ", senha=" + senha + "]";
	}
	
    

}
