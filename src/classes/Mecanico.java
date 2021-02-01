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
	}//Função que valida o nome do cliente 

	//Menu
	public void menu(List<Funcionario> funcs, List<Cliente> c ) {
		Scanner entrada = new Scanner( System.in );
		int opcao, newInt;
		String str;
		do {
			System.out.println("========= Menu Mecanico =========");
			System.out.println("1: Vizualizar todos os clientes\n");
			System.out.println("2: Vizualizar apenas ordens de serviços aprovadas pelos clientes\n");
			System.out.println("3: Selecionar cliente para executar ordens de serviço\n\n");
			System.out.println("0: Sair deste Menu\n");

			do {
				System.out.println("Digite qual opção voce deseja: ");
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
									System.out.println("Ordem de serviço: #"+ (j+1));
									System.out.println(c.get(i).getVeiculo().getHistorico().get(j).toString());
									System.out.println("\n");
								}
							}
							else {
								System.out.println("O cliente não possui ordens de serviço");
							}
						}
						else System.out.println("O cliente não possui ordens de serviço");
					}
					System.out.println("\n");

					System.out.println("=================================================\n");
				}//Imprime os clientes cadastrados caso existam
				else {
					System.out.println("Não há clientes cadastrados\n");
				}
				break;

			case 2:
				int check = 0;
				if(c.tamanho() > 0 ) {
					for( int i = 0; i < c.tamanho(); i ++){
						if(c.get(i).getVeiculo().getHistorico() != null) {
							if(c.get(i).getVeiculo().getHistorico().tamanho() > 0) {
								for(int j=0;j<c.get(i).getVeiculo().getHistorico().tamanho();j++){ //Percore todas as ordens de serviço do cliente

									if(c.get(i).getVeiculo().getOrdemDeServicoX(j).getStatus() == "aprovado") {//Imprime caso estejam aprovadas
										System.out.println( "Ordem de Serviço #" + (j+1) +":" );
										System.out.println("Nome do cliente: "+c.get(i).getNome());
										System.out.println(c.get(i).getVeiculo().getOrdemDeServicoX(j).toString());
										check = 1;
									}

								}
							}
						}
						
					}
					if(check != 1) {
						System.out.println("Não há ordens de serviços aprovadas no sistema");
					}
				}
				else {
					System.out.println("Não há clientes cadastrados no sistema");
				}
				break;

			case 3:
				System.out.println("Digite o nome do cliente a quem deseja executar as ordens de serviço: ");
				str = entrada.nextLine();
				int val = acharCliente(c,str);//Procura se o cliente digitado é valido
				if(val != -1 ) {
					if(c.get(val).getVeiculo().getHistorico() != null)
						if(c.get(val).getVeiculo().getHistorico().tamanho() > 0) {
							System.out.println("Os indices das ordens de servicos do cliente vao de [ 1 ... "+ (c.get(val).getVeiculo().getHistorico().tamanho())+" ]\n");
							do {
								System.out.println("Digite o indice da ordem de servico que deseja executar: ");
								newInt = entrada.nextInt();
								entrada.nextLine();
							}while(newInt<1 || newInt>c.get(val).getVeiculo().getHistorico().tamanho()); //Valida o indece da ordem de serviço
							newInt--;

							//Verificação se a ordem de serviço pode ser alterada
							if(c.get(val).getVeiculo().getOrdemDeServicoX(newInt).getStatus().compareTo( "pendente") == 0) {
								System.out.println("ERRO: Ordens de serviço pendentes não podem ser alteradas.\n\n"); 
							}
							else if(c.get(val).getVeiculo().getOrdemDeServicoX(newInt).getStatus().compareTo("concluido") == 0) {
								System.out.println("ERRO: Ordens de serviço concluídas não podem ser alteradas.\n\n");
							}
							else {
								menuMecanicoExecutar(c,val,newInt);
							}

						}
						else {
							System.out.println("O cliente não possui ordens de serviço cadastradas");
						}
				}
				else {
					System.out.println("Cliente não encontrado");
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
			System.out.println("1: Adicionar uma peça à esta ordem de serviço\n");
			System.out.println("2: Remover uma peça desta ordem de serviço\n");
			System.out.println("3: Editar uma peça desta ordem de serviço\n");
			System.out.println("4: Adicionar um serviço à esta ordem de serviço\n");
			System.out.println("5: Remover um serviço desta ordem de serviço\n");
			System.out.println("6: Editar um serviço desta ordem de serviço\n");
			System.out.println("7: Marcar o status desta ordem de serviço como Executada\n");
			System.out.println("8: Imprimir informações sobre esta ordem de serviço\n\n");
			System.out.println("0: Sair deste Menu" );

			do {
				System.out.println("Digite qual opção voce deseja: ");
				opcao = entrada.nextInt();
				entrada.nextLine();
			}while(opcao < 0 || opcao > 8);

			switch(opcao) {
			case 1:
				if(c.get(posicao).getVeiculo().getOrdemDeServicoX(index).getPecas() == null){
					List<Peca> aloca = new List<Peca>();
					c.get(posicao).getVeiculo().getOrdemDeServicoX(index).setPecas(aloca);
				}//Aloca peça caso ainda não tenha sido alocada
				c.get(posicao).getVeiculo().getOrdemDeServicoX(index).addPeca(Peca.criarPeca());//Adiciona peça
				System.out.println("Peça adicionada\n");
				break;
			case 2:
				if(c.get(posicao).getVeiculo().getOrdemDeServicoX(index).getPecas() != null)
					if(c.get(posicao).getVeiculo().getOrdemDeServicoX(index).getPecas().tamanho()> 0){
						out = c.get(posicao).getVeiculo().getOrdemDeServicoX(index).getPecas().toList();
						for(int i = 0; i < c.get(posicao).getVeiculo().getOrdemDeServicoX(index).getPecas().tamanho(); i++) {
							System.out.println((i+1)+") "+out[i]);
						}//Imprime peças e seus respectivos indices
						do {//Valida o indice da peca a ser removida
							System.out.println("Digite o indice da peça a ser removida: ");
							indexPeca = entrada.nextInt();
						}while(indexPeca < 1 || indexPeca > c.get(posicao).getVeiculo().getOrdemDeServicoX(index).getPecas().tamanho());
						indexPeca--;
						c.get(posicao).getVeiculo().getOrdemDeServicoX(index).removerPeca(indexPeca);
						System.out.println("Peça Removida\n");
					}//Verifica se existem peças para serem removidas
					else {
						System.out.println("Não há pecas cadastradas na ordem de serviço\n");
					}

				break;

			case 3:
				if(c.get(posicao).getVeiculo().getOrdemDeServicoX(index).getPecas() != null) 
					if(c.get(posicao).getVeiculo().getOrdemDeServicoX(index).getPecas().tamanho()>0){
						out = c.get(posicao).getVeiculo().getOrdemDeServicoX(index).getPecas().toList();
						for(int i = 0; i < c.get(posicao).getVeiculo().getOrdemDeServicoX(index).getPecas().tamanho(); i++) {
							System.out.println((i+1)+") "+out[i]);
						}
						do {//Valida o indice da peça a ser editada
							System.out.println("Digite o indice da peça deseja editar ");
							indexPeca = entrada.nextInt();
						}while(indexPeca < 1 || indexPeca > c.get(posicao).getVeiculo().getOrdemDeServicoX(index).getPecas().tamanho());
						indexPeca--;

						c.get(posicao).getVeiculo().getOrdemDeServicoX(index).getPecas().get(indexPeca).menu();//Menu de edição é chamado
					}//Verifica se existem peças na ordem de serviços
					else {
						System.out.println("Não há pecas cadastradas na ordem de serviço\n");
					}

				break;

			case 4:
				if(c.get(posicao).getVeiculo().getOrdemDeServicoX(index).getServicos() == null) {
					List<Servico> aloca = new List<Servico>();
					c.get(posicao).getVeiculo().getOrdemDeServicoX(index).setServicos(aloca);
				}//aloca serviço caso ainda não tenha sido alocado
				c.get(posicao).getVeiculo().getOrdemDeServicoX(index).addServico(Servico.criarServico());//cria um novo serviço
				System.out.println("Servico adicionado\n");
				break;

			case 5:
				if(c.get(posicao).getVeiculo().getOrdemDeServicoX(index).getServicos() != null)
					if(c.get(posicao).getVeiculo().getOrdemDeServicoX(index).getServicos().tamanho() > 0) {
						out = c.get(posicao).getVeiculo().getOrdemDeServicoX(index).getServicos().toList();
						for(int i = 0; i < c.get(posicao).getVeiculo().getOrdemDeServicoX(index).getServicos().tamanho(); i++) {
							System.out.println((i+1)+") "+out[i]);
						}
						do {//Valida indice do serviço a ser removido
							System.out.println("Digite o indice do servico a ser removido: ");
							indexServicos = entrada.nextInt();
						}while(indexServicos < 1 || indexServicos >c.get(posicao).getVeiculo().getOrdemDeServicoX(index).getServicos().tamanho());
						indexServicos--;
						c.get(posicao).getVeiculo().getOrdemDeServicoX(index).removerServico(indexServicos);
						System.out.println("Serviço removido\n");
					}//Verifica se existem serviços para serem removidas
					else {
						System.out.println("Não há servicos cadastrados\n");
					}
				break;

			case 6:
				if(c.get(posicao).getVeiculo().getOrdemDeServicoX(index).getServicos() != null)
					if(c.get(posicao).getVeiculo().getOrdemDeServicoX(index).getServicos().tamanho() > 0){
						out = c.get(posicao).getVeiculo().getOrdemDeServicoX(index).getServicos().toList();
						for(int i = 0; i < c.get(posicao).getVeiculo().getOrdemDeServicoX(index).getServicos().tamanho(); i++) {
							System.out.println((i+1)+") "+out[i]);
						}
						do {//Valida indice do serviço a ser editado
							System.out.println("Digite o indice do serviço deseja editar ");
							indexServicos = entrada.nextInt();
						}while(indexServicos  < 1 ||indexServicos  > c.get(posicao).getVeiculo().getOrdemDeServicoX(index).getServicos().tamanho());
						indexServicos--;
						c.get(posicao).getVeiculo().getOrdemDeServicoX(index).getServicos().get(indexServicos).menu(); // Chama o menu de edição
					}//Verifica se existem serviços para serem removidas
					else {
						System.out.println("Não há serviços cadastrados");
					}
				break;

			case 7:
				c.get(posicao).getVeiculo().getOrdemDeServicoX(index).setStatus(Status.EXECUTADO);//Seta o status para executado
				System.out.println("Status atualizado para: executado\n");
				break;

			case 8:
				System.out.println(c.get(posicao).getVeiculo().getOrdemDeServicoX(index).toString()); // Imprime a ordem de serviço
				break;
			default:
				break;
			}

		}while(opcao != 0);
	}

	//Impressão
	@Override
	public String toString() {
		return super.toString();
	}

}


