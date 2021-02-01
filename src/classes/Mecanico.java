package classes;

import java.util.Scanner;

public class Mecanico extends Funcionario {
	public Mecanico( String login, String senha ) {
		super(login, senha);
	}//Construtor

	public int acharCliente(List<Cliente> c, String str) {
		for(int i = 0; i < c.tamanho(); i++) {
			if(c.get(i).getNome().compareTo(str) == 0) {
				return i;
			}
		}
		return -1;
	}//Fun��o que valida o nome do cliente 

	//Menu
	public void menu(List<Funcionario> funcs, List<Cliente> c ) {
		Scanner entrada = new Scanner( System.in );
		int opcao, newInt;
		String str;
		do {
			System.out.println("========= Menu Mecanico =========");
			System.out.println("1: Vizualizar todos os clientes\n");
			System.out.println("2: Vizualizar apenas ordens de servi�os aprovadas pelos clientes\n");
			System.out.println("3: Selecionar cliente para executar ordens de servi�o\n\n");
			System.out.println("0: Sair deste Menu\n");

			do {
				System.out.println("Digite qual op��o voce deseja: ");
				opcao =  entrada.nextInt();
				entrada.nextLine();
			}while(opcao < 0 || opcao > 3);

			switch(opcao) {
			case 1:
				if(c.tamanho() > 0 ) {
					System.out.println("==================Clientes====================\n");
					String[] out = c.toList();
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

			case 2:
				int check = 0;
				if(c.tamanho() > 0 ) {
					for( int i = 0; i < c.tamanho(); i ++){
						if(c.get(i).getVeiculo().getHistorico() != null) {
							if(c.get(i).getVeiculo().getHistorico().tamanho() > 0) {
								for(int j=0;j<c.get(i).getVeiculo().getHistorico().tamanho();j++){ //Percore todas as ordens de servi�o do cliente

									if(c.get(i).getVeiculo().getOrdemDeServicoX(j).getStatus() == "aprovado") {//Imprime caso estejam aprovadas
										System.out.println( "Ordem de Servi�o #" + (j+1) +":" );
										System.out.println("Nome do cliente: "+c.get(i).getNome());
										System.out.println(c.get(i).getVeiculo().getOrdemDeServicoX(j).toString());
										check = 1;
									}

								}
							}
						}
						
					}
					if(check != 1) {
						System.out.println("N�o h� ordens de servi�os aprovadas no sistema");
					}
				}
				else {
					System.out.println("N�o h� clientes cadastrados no sistema");
				}
				break;

			case 3:
				System.out.println("Digite o nome do cliente a quem deseja executar as ordens de servi�o: ");
				str = entrada.nextLine();
				int val = acharCliente(c,str);//Procura se o cliente digitado � valido
				if(val != -1 ) {
					if(c.get(val).getVeiculo().getHistorico() != null)
						if(c.get(val).getVeiculo().getHistorico().tamanho() > 0) {
							System.out.println("Os indices das ordens de servicos do cliente vao de [ 1 ... "+ (c.get(val).getVeiculo().getHistorico().tamanho())+" ]\n");
							do {
								System.out.println("Digite o indice da ordem de servico que deseja executar: ");
								newInt = entrada.nextInt();
								entrada.nextLine();
							}while(newInt<1 || newInt>c.get(val).getVeiculo().getHistorico().tamanho()); //Valida o indece da ordem de servi�o
							newInt--;

							//Verifica��o se a ordem de servi�o pode ser alterada
							if(c.get(val).getVeiculo().getOrdemDeServicoX(newInt).getStatus().compareTo( "pendente") == 0) {
								System.out.println("ERRO: Ordens de servi�o pendentes n�o podem ser alteradas.\n\n"); 
							}
							else if(c.get(val).getVeiculo().getOrdemDeServicoX(newInt).getStatus().compareTo("concluido") == 0) {
								System.out.println("ERRO: Ordens de servi�o conclu�das n�o podem ser alteradas.\n\n");
							}
							else {
								menuMecanicoExecutar(c,val,newInt);
							}

						}
						else {
							System.out.println("O cliente n�o possui ordens de servi�o cadastradas");
						}
				}
				else {
					System.out.println("Cliente n�o encontrado");
				}


				break;
			}
		}while(opcao != 0);
	}

	//Menu mecanico executar
	public void menuMecanicoExecutar(List<Cliente> c, int posicao, int index) {
		int opcao;
		int indexPeca,indexServicos;
		String[] out;
		Scanner entrada = new Scanner( System.in );
		do {
			System.out.println("============================== MENU =============================");
			System.out.println("1: Adicionar uma pe�a � esta ordem de servi�o\n");
			System.out.println("2: Remover uma pe�a desta ordem de servi�o\n");
			System.out.println("3: Editar uma pe�a desta ordem de servi�o\n");
			System.out.println("4: Adicionar um servi�o � esta ordem de servi�o\n");
			System.out.println("5: Remover um servi�o desta ordem de servi�o\n");
			System.out.println("6: Editar um servi�o desta ordem de servi�o\n");
			System.out.println("7: Marcar o status desta ordem de servi�o como Executada\n");
			System.out.println("8: Imprimir informa��es sobre esta ordem de servi�o\n\n");
			System.out.println("0: Sair deste Menu" );

			do {
				System.out.println("Digite qual op��o voce deseja: ");
				opcao = entrada.nextInt();
				entrada.nextLine();
			}while(opcao < 0 || opcao > 8);

			switch(opcao) {
			case 1:
				if(c.get(posicao).getVeiculo().getOrdemDeServicoX(index).getPecas() == null){
					List<Peca> aloca = new List<Peca>();
					c.get(posicao).getVeiculo().getOrdemDeServicoX(index).setPecas(aloca);
				}//Aloca pe�a caso ainda n�o tenha sido alocada
				c.get(posicao).getVeiculo().getOrdemDeServicoX(index).addPeca(Peca.criarPeca());//Adiciona pe�a
				System.out.println("Pe�a adicionada\n");
				break;
			case 2:
				if(c.get(posicao).getVeiculo().getOrdemDeServicoX(index).getPecas() != null)
					if(c.get(posicao).getVeiculo().getOrdemDeServicoX(index).getPecas().tamanho()> 0){
						out = c.get(posicao).getVeiculo().getOrdemDeServicoX(index).getPecas().toList();
						for(int i = 0; i < c.get(posicao).getVeiculo().getOrdemDeServicoX(index).getPecas().tamanho(); i++) {
							System.out.println((i+1)+") "+out[i]);
						}//Imprime pe�as e seus respectivos indices
						do {//Valida o indice da peca a ser removida
							System.out.println("Digite o indice da pe�a a ser removida: ");
							indexPeca = entrada.nextInt();
						}while(indexPeca < 1 || indexPeca > c.get(posicao).getVeiculo().getOrdemDeServicoX(index).getPecas().tamanho());
						indexPeca--;
						c.get(posicao).getVeiculo().getOrdemDeServicoX(index).removerPeca(indexPeca);
						System.out.println("Pe�a Removida\n");
					}//Verifica se existem pe�as para serem removidas
					else {
						System.out.println("N�o h� pecas cadastradas na ordem de servi�o\n");
					}

				break;

			case 3:
				if(c.get(posicao).getVeiculo().getOrdemDeServicoX(index).getPecas() != null) 
					if(c.get(posicao).getVeiculo().getOrdemDeServicoX(index).getPecas().tamanho()>0){
						out = c.get(posicao).getVeiculo().getOrdemDeServicoX(index).getPecas().toList();
						for(int i = 0; i < c.get(posicao).getVeiculo().getOrdemDeServicoX(index).getPecas().tamanho(); i++) {
							System.out.println((i+1)+") "+out[i]);
						}
						do {//Valida o indice da pe�a a ser editada
							System.out.println("Digite o indice da pe�a deseja editar ");
							indexPeca = entrada.nextInt();
						}while(indexPeca < 1 || indexPeca > c.get(posicao).getVeiculo().getOrdemDeServicoX(index).getPecas().tamanho());
						indexPeca--;

						c.get(posicao).getVeiculo().getOrdemDeServicoX(index).getPecas().get(indexPeca).menu();//Menu de edi��o � chamado
					}//Verifica se existem pe�as na ordem de servi�os
					else {
						System.out.println("N�o h� pecas cadastradas na ordem de servi�o\n");
					}

				break;

			case 4:
				if(c.get(posicao).getVeiculo().getOrdemDeServicoX(index).getServicos() == null) {
					List<Servico> aloca = new List<Servico>();
					c.get(posicao).getVeiculo().getOrdemDeServicoX(index).setServicos(aloca);
				}//aloca servi�o caso ainda n�o tenha sido alocado
				c.get(posicao).getVeiculo().getOrdemDeServicoX(index).addServico(Servico.criarServico());//cria um novo servi�o
				System.out.println("Servico adicionado\n");
				break;

			case 5:
				if(c.get(posicao).getVeiculo().getOrdemDeServicoX(index).getServicos() != null)
					if(c.get(posicao).getVeiculo().getOrdemDeServicoX(index).getServicos().tamanho() > 0) {
						out = c.get(posicao).getVeiculo().getOrdemDeServicoX(index).getServicos().toList();
						for(int i = 0; i < c.get(posicao).getVeiculo().getOrdemDeServicoX(index).getServicos().tamanho(); i++) {
							System.out.println((i+1)+") "+out[i]);
						}
						do {//Valida indice do servi�o a ser removido
							System.out.println("Digite o indice do servico a ser removido: ");
							indexServicos = entrada.nextInt();
						}while(indexServicos < 1 || indexServicos >c.get(posicao).getVeiculo().getOrdemDeServicoX(index).getServicos().tamanho());
						indexServicos--;
						c.get(posicao).getVeiculo().getOrdemDeServicoX(index).removerServico(indexServicos);
						System.out.println("Servi�o removido\n");
					}//Verifica se existem servi�os para serem removidas
					else {
						System.out.println("N�o h� servicos cadastrados\n");
					}
				break;

			case 6:
				if(c.get(posicao).getVeiculo().getOrdemDeServicoX(index).getServicos() != null)
					if(c.get(posicao).getVeiculo().getOrdemDeServicoX(index).getServicos().tamanho() > 0){
						out = c.get(posicao).getVeiculo().getOrdemDeServicoX(index).getServicos().toList();
						for(int i = 0; i < c.get(posicao).getVeiculo().getOrdemDeServicoX(index).getServicos().tamanho(); i++) {
							System.out.println((i+1)+") "+out[i]);
						}
						do {//Valida indice do servi�o a ser editado
							System.out.println("Digite o indice do servi�o deseja editar ");
							indexServicos = entrada.nextInt();
						}while(indexServicos  < 1 ||indexServicos  > c.get(posicao).getVeiculo().getOrdemDeServicoX(index).getServicos().tamanho());
						indexServicos--;
						c.get(posicao).getVeiculo().getOrdemDeServicoX(index).getServicos().get(indexServicos).menu(); // Chama o menu de edi��o
					}//Verifica se existem servi�os para serem removidas
					else {
						System.out.println("N�o h� servi�os cadastrados");
					}
				break;

			case 7:
				c.get(posicao).getVeiculo().getOrdemDeServicoX(index).setStatus(Status.EXECUTADO);//Seta o status para executado
				System.out.println("Status atualizado para: executado\n");
				break;

			case 8:
				System.out.println(c.get(posicao).getVeiculo().getOrdemDeServicoX(index).toString()); // Imprime a ordem de servi�o
				break;
			default:
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


