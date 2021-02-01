package classes;

import java.util.Calendar;
import java.util.Scanner;

public class Vendedor extends Funcionario{
	public Vendedor( String login, String senha ){
		super(login, senha);
	}

	//Valida nome de um cliente dentro da lista de clientes
	public int acharCliente(List<Cliente> c, String str) {
		for(int i = 0; i < c.tamanho(); i++) {
			if(c.get(i).getNome().compareTo(str) == 0) {
				return i;
			}
		}
		return -1;
	}

	public void menu(List<Funcionario> funcs,List<Cliente> c) {
		int opcao, newInt, val, check=0,dia,mes,ano;
		String[] out;
		String str;
		Scanner entrada = new Scanner( System.in );

		if( c == null )
			c = new List<Cliente>(); //Aloca a lista de clientes caso n�o tenha sido alocado

		do {
			System.out.println("========= Menu Vendedor =========");
			System.out.println("1: Cadastrar um novo cliente e seu ve�culo\n");
			System.out.println("2: Vizualizar todos os clientes\n");
			System.out.println("3: Vizualizar apenas ordens de servi�os pendentes de aprova��o pelos clientes\n");
			System.out.println("4: Vizualizar apenas ordens de servi�os executadas\n");
			System.out.println("5: Selecionar cliente para gerar uma nova ordem de servi�o\n");
			System.out.println("6: Selecionar cliente para remover uma ordem de servi�o\n");
			System.out.println("7: Marcar uma ordem de servi�o como Aprovada\n");
			System.out.println("8: Realizar o fechamento de uma ordem de servi�o Executada\n");
			System.out.println("9: Editar uma ordem de servi�o\n");
			System.out.println("10: Editar informa��es de um cliente\n");
			System.out.println("11: Editar informa��es de um ve�culo\n\n");
			System.out.println("0: Sair deste Menu");

			do {
				System.out.println("Digite qual op��o voce deseja: ");
				opcao =  entrada.nextInt();
				entrada.nextLine();
			}while(opcao < 0 || opcao > 11);

			switch(opcao) {
			case 1:
				c.add(Cliente.criarCliente());//Executa menu para criar cliente e salva em c( array de clientes)
				break;
			case 2:
				if(c.tamanho() > 0 ) {
					System.out.println("==================Clientes====================\n");
					out = c.toList();
					for( int i = 0; i < c.tamanho(); i ++) {
						System.out.println("Cliente-"+ (i+1) + ": ");
						System.out.println(out[i]);
						System.out.println("\n");
						if(c.get(i).getVeiculo().getHistorico() != null ) {
							if(c.get(i).getVeiculo().getHistorico().tamanho() > 0) {
								
								for(int j = 0; j < c.get(i).getVeiculo().getHistorico().tamanho(); j++) {
									System.out.println("Ordem de servi�o: #"+ (j+1));
									System.out.println(c.get(i).getVeiculo().getHistorico().get(j).toString());
									System.out.println("\n");
								}
							}
							else {
								System.out.println("O cliente n�o possui ordens de servi�o");
							}
						}
						else System.out.println("O cliente n�o possui ordens de servi�o");
					}
					System.out.println("\n");

					System.out.println("=================================================\n");
				}//Imprime os clientes cadastrados caso existam
				else {
					System.out.println("N�o h� clientes cadastrados\n");
				}
				break;

			case 3:		
				if(c.tamanho() > 0) {
					for(int i=0 ; i<c.tamanho() ; i++){
						if(c.get(i).getVeiculo().getHistorico() != null)
							if(c.get(i).getVeiculo().getHistorico().tamanho() > 0){
								for(int j=0;j<c.get(i).getVeiculo().getHistorico().tamanho();j++) { 
									if(c.get(i).getVeiculo().getOrdemDeServicoX(j).getStatus().compareTo("pendente") == 0){
										System.out.println("Ordem de Servi�o #" + (j+1) + ":" );
										System.out.println("Nome do cliente: "+c.get(i).getNome());
										System.out.println(c.get(i).getVeiculo().getOrdemDeServicoX(j).toString());
										check = 1;
									}//Imprime todas as ordens de servi�os pendentes
								}
							}

						
					}
					if(check != 1){
						System.out.println("Nenhum cliente n�o possui ordens de servi�o cadastradas com o status pendente \n");
						check = 0;
					}
				}
				else {
					System.out.println("N�o h� clientes cadastrados no sistema.\n");
				}
				break;
			case 4:
				if(c.tamanho() > 0) {
					for(int i=0 ; i<c.tamanho() ; i++)
						if(c.get(i).getVeiculo().getHistorico() != null)
							if(c.get(i).getVeiculo().getHistorico().tamanho() > 0){
								for(int j=0;j<c.get(i).getVeiculo().getHistorico().tamanho();j++) {
									if(c.get(i).getVeiculo().getOrdemDeServicoX(j).getStatus().compareTo("executado") == 0){
										System.out.println("Ordem de Servi�o #" + (j+1) + ":" );
										System.out.println("Nome do cliente: "+c.get(i).getNome());
										System.out.println(c.get(i).getVeiculo().getOrdemDeServicoX(j).toString());
										check = 1;
									}//Imprime todas as ordens de servi�os pendentes
								}
							}
					if(check != 1) {
						System.out.println(" O sistema n�o possui Ordens de Servi�o Executadas.\n");
						check = 0;
					}	
				}
				else {
					System.out.println("N�o h� clientes cadastrados no sistema.\n");
				}
				break;

			case 5:
				if(c.tamanho() > 0) {
					System.out.println("Digite o nome do cliente a quem deseja gerar nova ordem de servico: ");
					str = entrada.nextLine();
					val = acharCliente(c,str); //Verifica se o cliente � valido
					if(val != -1 ) {
						if(c.get(val).getVeiculo().getHistorico() == null) {
							List<OrdemDeServico> aloca = new List<OrdemDeServico>();
							c.get(val).getVeiculo().setHistorico(aloca);
						}
						c.get(val).getVeiculo().getHistorico().add(OrdemDeServico.criarOrdem());//Executa o menu para criar ordem caso o cliente seja encontrado
					}
					else {
						System.out.println("Cliente n�o encontrado\n");
					}
				}else System.out.println("N�o h� clientes cadastrados no sistema\n");
				break;

			case 6:
				if(c.tamanho() > 0) {
					System.out.println("Digite o nome do cliente a quem deseja excluir ordem de servico: ");
					str = entrada.nextLine();
					val = acharCliente(c,str); //Verifica se o cliente � valido
					if(val != -1)
						if(c.get(val).getVeiculo().getHistorico() != null) 
							if(c.get(val).getVeiculo().getHistorico().tamanho() > 0){
								System.out.println("Os indices das ordens de servicos do cliente vao de [ 1 ... "+ (c.get(val).getVeiculo().getHistorico().tamanho())+" ]\n");
								do {//Valida indice da ordem de servico 
									System.out.println("Digite o indice da ordem de servico que deseja apagar: ");
									newInt = entrada.nextInt();
									entrada.nextLine();
								}while(newInt<1 || newInt>(c.get(val).getVeiculo().getHistorico().tamanho()));
								newInt--;
								c.get(val).getVeiculo().getHistorico().delete(newInt);//Apaga a ordem selecionada
								System.out.println("Ordem de servi�o apagada com sucesso\n");
							}
							else {
								System.out.println("Cliente n�o possui ordens de servico\n");
							}
						else System.out.println("Cliente n�o encontrado");
				}
				else
					System.out.println("N�o h� clientes cadastrados no sistema\n");
				break;
			case 7:
				if(c.tamanho() > 0) {
					System.out.println("Digite o nome do cliente a quem deseja que ordem de servico seja marcada como aprovada: ");
					str = entrada.nextLine();
					val = acharCliente(c,str);//Verifica se o cliente � valodo
					if(val != -1) 
						if (c.get(val).getVeiculo().getHistorico() != null) 
							if(c.get(val).getVeiculo().getHistorico().tamanho() > 0){
								System.out.println("Os indices das ordens de servicos do cliente vao de [ 1 ... : "+ (c.get(val).getVeiculo().getHistorico().tamanho())+" ]\n");
								do {//valida a posi��o da ordem de servico
									System.out.println("Digite o indice da ordem de servico que deseja marcar como aprovada: ");
									newInt = entrada.nextInt();
									entrada.nextLine();
								}while(newInt<1 || newInt>(c.get(val).getVeiculo().getHistorico().tamanho()));
								newInt--;
								//faz a verifica��o se � posivel marcar a ordem como aprovada
								if(c.get(val).getVeiculo().getHistorico().get(newInt).getStatus() =="executada")
									System.out.println("ERRO: Ordens de servi�o executadas j� foram aprovadas.\n\n");
								else if(c.get(val).getVeiculo().getHistorico().get(newInt).getStatus() == "concluido")
									System.out.println( "ERRO: Ordens de servi�o conclu�das j� foram aprovadas./n/n");
								else {  
									c.get(val).getVeiculo().getHistorico().get(newInt).setStatus(Status.APROVADO);
									System.out.println("O status da ordem de servi�os foi alterado para aprovado com sucesso\n");
								}
							}
							else {
								System.out.println("Cliente n�o possui ordens de servico\n");
							}
						else System.out.println("Cliente n�o encontrado");
				}
				else
					System.out.println("N�o h� clientes cadastrados no sistema\n");

				break;

			case 8:
				if(c.tamanho() > 0) {
					System.out.println("Digite o nome do cliente que deseja realizar o fechamento de uma ordem de servi�o Executada: ");
					str = entrada.nextLine();
					val = acharCliente(c,str);//Verifica se o cliente � valido

					if(val != -1) 
						if(c.get(val).getVeiculo().getHistorico() != null)
							if( c.get(val).getVeiculo().getHistorico().tamanho() > 0){
								for(int j=0;j<c.get(val).getVeiculo().getHistorico().tamanho();j++) {
									if(c.get(val).getVeiculo().getOrdemDeServicoX(j).getStatus().compareTo("executado") == 0){
										System.out.println("Ordem de Servi�o #" + (j+1) + ":" );
										System.out.println(c.get(val).getVeiculo().getOrdemDeServicoX(j).toString());
										check = 1;
									}//Imprime as ordens de servi�o com status de executado
								}
								if(check == 1) {
									do {
										System.out.println("Digite o indice da ordem que deseja marcar como encerrada: \n");
										newInt = entrada.nextInt();
									}while(newInt<1 || newInt > c.get(val).getVeiculo().getHistorico().tamanho());
									newInt--;
									//Verifica se � possivel marcar como encerrada
									if(c.get(val).getVeiculo().getOrdemDeServicoX(newInt).getStatus() == "pendente")
										System.out.println("ERRO: Ordens de servi�o pendentes n�o podem ter o fechamento realizado.\n\n");
									else if(c.get(val).getVeiculo().getOrdemDeServicoX(newInt).getStatus() == "aprovado")
										System.out.println("ERRO: Ordens de servi�o aprovadas precisam ser executadas antes de ter o fechamento realizado.\n\n");
									else if(c.get(val).getVeiculo().getOrdemDeServicoX(newInt).getStatus() == "concluido")
										System.out.println("ERRO: Ordens de servi�o conclu�das j� tiveram o fechamento realizado.\n\n");
									else{

										System.out.println("Digite o dia da data de fechamento ");
										dia= entrada.nextInt();

										System.out.println("Digite o mes da data de fechamento ");
										mes= entrada.nextInt();

										System.out.println("Digite o ano da data de fechamento ");
										ano= entrada.nextInt();

										Calendar data = Calendar.getInstance();
										data.set(ano, mes, dia);

										c.get(val).getVeiculo().getOrdemDeServicoX(newInt).setDataFechamento(data);
										c.get(val).getVeiculo().getOrdemDeServicoX(newInt).setStatus(Status.CONCLUIDO);
										check = 0;
									} 
								}
								else{
									System.out.println(" Esse ve�culo n�o possui Ordens de Servi�o Executadas.\n");
								}


							}
							else {
								System.out.println(" Cliente n�o possui historico de ordens de servi�o");
							}
						else System.out.println("Cliente n�o encontrado");
				}
				else {
					System.out.println("N�o h� clientes cadastrados no sistema.\n");
				}
				break;
			case 9:
				if(c.tamanho() > 0) {
					System.out.println("Digite o nome do cliente que deseja editar:  ");
					str = entrada.nextLine();
					val = acharCliente(c,str);//Verifica se o cliente � valido

					if(val != -1)
						if(c.get(val).getVeiculo().getHistorico() != null)
							if(c.get(val).getVeiculo().getHistorico().tamanho()>0){
								for(int j=0;j<c.get(val).getVeiculo().getHistorico().tamanho();j++) {
									System.out.println("Ordem de Servi�o #" + (j+1) + ":" );
									System.out.println(c.get(val).getVeiculo().getOrdemDeServicoX(j).toString());
								}
								do {//Verifica se o indice da ordem � valido
									System.out.println("Digite o indice da ordem que deseja editar \n");
									newInt = entrada.nextInt();
								}while(newInt<1 || newInt >c.get(val).getVeiculo().getHistorico().tamanho());
								newInt--;

								c.get(val).getVeiculo().getHistorico().get(newInt).menuOrdemDeServico();//chama o menu de edi��o da ordem de servico

							}
							else {
								System.out.println("Cliente n�o possui ordens de servi�o");
							}
						else System.out.println("Cliente n�o encontrado");

				}
				else {
					System.out.println("N�o h� clientes cadastrados no sistema.\n");
				}
				break;

			case 10:
				if(c.tamanho() > 0) {
					System.out.println("Digite o nome do cliente que deseja editar:  ");
					str = entrada.nextLine();
					val = acharCliente(c,str);//valida cliente

					if(val != -1){
						c.get(val).editarDadosPessoais();//chama menu de edi��o dos dados do cliente
					}else {
						System.out.println("Cliente n�o encontrado");
					}

				}
				else {
					System.out.println("N�o h� clientes cadastrados no sistema.\n");
				}
				break;

			case 11:
				if(c.tamanho() > 0) {
					System.out.println("Digite o nome do cliente que deseja editar o veiculo:  ");
					str = entrada.nextLine();
					val = acharCliente(c,str);//valida cliente

					if(val != -1){
						c.get(val).getVeiculo().menuVeiculo();//Faz a edi��o dos dados do veiculo do cliente
					}else {
						System.out.println("Cliente n�o encontrado");
					}

				}
				else {
					System.out.println("N�o h� clientes cadastrados no sistema.\n");
				}
				break;

			}


		}while(opcao != 0);



	}

	//Impress�o
	@Override
	public String toString() {
		return super.toString();
	}

}
