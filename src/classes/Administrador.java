package classes;

import java.util.Scanner;

public class Administrador extends Funcionario {
	public Administrador ( String login, String senha ) {
		super(login, senha);
	}//Construtor 
	
	public int valida(String login, String tipo, List<Funcionario> funcs) {
		for( int i = 0; i < funcs.tamanho(); i++) {
			if(funcs.get(i).getLogin().compareTo(login) ==0 && funcs.get(i).getClass().getName().compareTo("classes."+tipo)==0)
				return i;
		}
		return -1;
	}// Função que valida o login e tipo do funcionario

	public void menu(List<Funcionario> funcs,  List<Cliente> c) {
		int opcao;
		String login;
		do {
			Scanner entrada = new Scanner( System.in );
			System.out.println("========= Menu Adm =========");
			System.out.println("1: Editar dados vendedores\n");
			System.out.println("2: Editar dados mecanicos\n ");
			System.out.println("0: Sair do menu\n");
			do {
				System.out.println("Digite qual opção voce deseja: ");
				opcao =  entrada.nextInt();
				entrada.nextLine();
			}while(opcao < 0 || opcao > 2);
			
			if( opcao == 1 ) {
				System.out.println("Digite o login do vendedor que deseja alterar");
				 login = entrada.nextLine();
				 int val = valida(login,"Vendedor",funcs);
				 if(val != -1 ) 
					 funcs.get(val).editarDados();//Edita dados do Vendedor
				 else
					 System.out.println("Vendedor não encontrado");
			}else if( opcao == 2){
				System.out.println("Digite o login do vendedor que deseja alterar");
				 login = entrada.nextLine();
				 int val = valida(login,"Mecanico",funcs);
				 if(val != -1 ) 
					 funcs.get(val).editarDados();//Edita dados do Mecanico
				 else
					 System.out.println("Mecanico não encontrado");
			}
		}while(opcao != 0);	
	}

	//Impressão
	@Override
	public String toString() {
		return super.toString();
	}

}
