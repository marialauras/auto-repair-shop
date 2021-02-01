package classes;

import java.util.Calendar;
import java.util.Scanner;

public class OrdemDeServico {
	private String motivo;
	private Status status;
	private Tipo tipo;
	private Calendar dataAbertura;
	private Calendar dataFechamento;
	private double precoPecas, precoMaoDeObra, precoTotal;
	private List<Peca> pecas;
	private List<Servico> servicos;


	//Construtores
	public OrdemDeServico() {}

	public OrdemDeServico(OrdemDeServico o) {
		motivo = o.motivo;
		tipo = o.tipo;
		status = o.status;
		dataAbertura = o.dataAbertura;
		dataFechamento = o.dataFechamento;
		precoPecas = o.precoPecas;
		precoMaoDeObra = o.precoMaoDeObra;
		precoTotal = o.precoTotal;
	}

	public OrdemDeServico(String motivo, Tipo tipo, Status status, Calendar dataAbertura, Calendar dataFechamento,double precoPecas, double precoMaoDeObra, double precoTotal) {
		this.motivo = motivo;
		this.tipo = tipo;
		this.status = status;
		this.dataAbertura = dataAbertura;
		this.dataFechamento = dataFechamento;
		this.precoPecas = precoPecas;
		this.precoMaoDeObra = precoMaoDeObra;
		this.precoTotal = precoTotal;  
	}


	//Getters
	public String getMotivo() {
		return motivo;
	}

	public String getTipo() {
		return tipo.getTipoName();
	}

	public String getStatus() {
		return status.getStatusName();
	}

	public Calendar getDataAbertura() {
		return dataAbertura;
	}

	public Calendar getDataFechamento() {
		return dataFechamento;
	}

	public double getPrecoPecas() {
		atualizarPrecoPecas();
		return precoPecas;
	}

	public double getPrecoMaoDeObra() {
		atualizarPrecoServico();
		return precoMaoDeObra;
	}


	public double getPrecoTotal() {
		atualizarPrecoTotal();
		return precoTotal;
	}

	public List<Peca> getPecas() {
		return pecas;
	}

	public List<Servico> getServicos() {
		return servicos;
	}


	//Setters
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void setDataAbertura(Calendar dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public void setDataFechamento(Calendar dataFechamento) {
		this.dataFechamento = dataFechamento;
	}

	public void setPrecoPecas(double precoPecas) {
		this.precoPecas = precoPecas;
	}

	public void setPrecoMaoDeObra(double precoMaoDeObra) {
		this.precoMaoDeObra = precoMaoDeObra;
	}

	public void setPrecoTotal(double precoTotal) {
		this.precoTotal = precoTotal;
	}

	public void setPecas(List<Peca> pecas) {
		this.pecas = pecas;
	}

	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}



	//Manipulando Pecas 
	public void addPeca(Peca p ) {
		pecas.add(p);
		atualizarPrecoPecas();
	}
	public void removerPeca(int index ) {
		pecas.delete(index);
		atualizarPrecoPecas();
	}


	//Manipulando Servi�os
	public void addServico(Servico s ) {
		servicos.add(s);
		atualizarPrecoServico();
	}
	public void removerServico(int index ){
		servicos.delete(index);
		atualizarPrecoServico();
	}


	//Criar ordem de servi�o
	public static OrdemDeServico criarOrdem() {
		String str;
		int tip;
		int dia,mes,ano;
		OrdemDeServico o = new OrdemDeServico();
		Scanner entrada = new Scanner( System.in );

		System.out.println("Digite o motivo para a ordem de servi�o:");
		str = entrada.nextLine();
		o.setMotivo(str);

		boolean aprova = false;
		do {
			System.out.println("Digite o nome do tipo da ordem de servi�o:");
			for(Tipo m : Tipo.values()) { 
				System.out.println(m.getTipoNum()+") ....."+m.getTipoName());
			}

			str = entrada.nextLine();

			for(Tipo m : Tipo.values()) { 
				if(m.getTipoName().compareTo(str) == 0)
					aprova=true;
			}
		}while(aprova == false );
		o.setTipo(Tipo.valueOf(str.toUpperCase()));

		aprova = false;
		do{
			System.out.println("Digite o nome do status da ordem de servi�o ");
			for(Status m : Status.values()) { 
				System.out.println(m.getStatusNum()+") ....."+m.getStatusName());
			}
			str = entrada.nextLine();

			for(Status m : Status.values()) { 
				if(m.getStatusName().compareTo(str) == 0)
					aprova=true;
			}
		}while(aprova == false);
		o.setStatus(Status.valueOf(str.toUpperCase()));

		System.out.println("Digite o dia da data de abertura ");
		dia= entrada.nextInt();

		System.out.println("Digite o mes da data de abertura ");
		mes= entrada.nextInt();

		System.out.println("Digite o ano da data de abertura ");
		ano= entrada.nextInt();

		Calendar data = Calendar.getInstance();
		data.set(ano, mes, dia);

		o.setDataAbertura(data);

		System.out.println(o.toString());

		return o;


	}

	//Atualizar pre�o pe�as
	public void atualizarPrecoPecas() {

		if(pecas == null)
			return;
		else if(pecas.tamanho()> 0) {
			precoPecas=0;
			for(int i = 0; i < pecas.tamanho(); i++)
			{
				precoPecas += pecas.get(i).getPreco();
			}
		}
		else {
			precoPecas = 0;
		}
	}

	//Atualizar preco m�o de obra
	public void atualizarPrecoServico() {
		if(servicos == null)
			return;
		else if(servicos.tamanho()> 0) {
			precoMaoDeObra=0;
			for(int i = 0; i < servicos.tamanho(); i++)
			{
				precoMaoDeObra += servicos.get(i).getPreco();
			}
		}
		else {
			precoMaoDeObra = 0;
		}
	}

	//Atualizar preco total
	public void atualizarPrecoTotal() {
		precoTotal = getPrecoPecas() + getPrecoMaoDeObra();
	}

	//Menu ordem de servico
	public void menuOrdemDeServico() {
		int opcao,dia,mes,ano;
		Scanner entrada = new Scanner( System.in );
		do {
			System.out.println( "============================== MENU ORDEM DE SERVI�O =============================\n");
			System.out.println( "1: Editar o motivo desta ordem de servi�o\n");
			System.out.println("2: Mudar o tipo desta ordem de servi�o para Or�amento\n");
			System.out.println("3: Mudar o tipo desta ordem de servi�o para Manuten��o\n");
			System.out.println("4: Mudar o status desta ordem de servi�o para Pendente\n");
			System.out.println("5: Mudar o status desta ordem de servi�o para Aprovada\n");
			System.out.println( "6: Mudar o status desta ordem de servi�o para Executada\n");
			System.out.println("7: Mudar o status desta ordem de servi�o para Conclu�da\n");
			System.out.println("8: Editar a data de abertura desta ordem de servi�o\n");
			System.out.println("9: Editar a data de fechamento desta ordem de servi�o\n");
			System.out.println("10: Imprimir informa��es sobre esta ordem de servi�o\n\n");
			System.out.println( "0: Sair deste Menu");

			do {
				System.out.println("Digite qual op��o voce deseja: ");
				opcao =  entrada.nextInt();
				entrada.nextLine();
			}while(opcao < 0 || opcao > 10);

			String str;
			switch(opcao) {
			case 1:
				System.out.println("Digite o novo motivo da ordem de servico");
				str = entrada.nextLine();
				setMotivo(str);
				break;

			case 2:
				setTipo(Tipo.ORCAMENTO);
				setStatus(Status.PENDENTE);
				System.out.println("A ordem de servi�o foi mudada para Or�amento e seu status agora � Pendente.");
				break;

			case 3:
				setTipo(Tipo.MANUTENCAO);
				setStatus(Status.APROVADO);
				System.out.println("A ordem de servi�o foi mudada para Manuten��o e seu status agora � Aprovada.");
				break;

			case 4:
				setStatus(Status.PENDENTE);
				System.out.println( "O status da ordem de servi�o foi mudado para Pendente.");
				break;

			case 5:
				setStatus(Status.APROVADO);
				System.out.println("O status da ordem de servi�o foi mudado para Aprovada.");
				break;

			case 6:
				setStatus(Status.EXECUTADO);
				System.out.println("O status da ordem de servi�o foi mudado para Executada.");
				break;

			case 7:
				setStatus(Status.CONCLUIDO);
				System.out.println("O status da ordem de servi�o foi mudado para Conclu�da.");
				break;

			case 8:
				System.out.println("Digite a nova data de abertura desta ordem de servi�o: ");

				System.out.println("Digite o dia da data de abertura ");
				dia= entrada.nextInt();

				System.out.println("Digite o mes da data de abertura ");
				mes= entrada.nextInt();

				System.out.println("Digite o ano da data de abertura ");
				ano= entrada.nextInt();

				Calendar data = Calendar.getInstance();
				data.set(ano, mes, dia);

				setDataAbertura(data);

				setDataFechamento(data);

				break;

			case 9:
				System.out.println("Digite a nova data de fechamento desta ordem de servi�o: "); 
				System.out.println("Digite o dia da data de fechamento  ");
				dia= entrada.nextInt();

				System.out.println("Digite o dia o mes da data de fechamento  ");
				mes= entrada.nextInt();

				System.out.println("Digite o dia o ano da data de fechamento  ");
				ano= entrada.nextInt();

				Calendar dataf = Calendar.getInstance();
				dataf.set(ano, mes, dia);

				setDataFechamento(dataf);
				break;

			case 10:
				System.out.println(toString());
				break;

			default:
				break;
			}
		}while(opcao != 0);
	}



	//Impress�es
	public String imprimePecas(){
		String out="";
		if(pecas.tamanho() == 0) {
			out = "n�o h� pecas cadastradas,\n";
		}
		else {
			String[] p = pecas.toList();
			for(int i = 0; i < pecas.tamanho(); i++) {
				out += (p[i] + "\n");
			}
		}
		return out;
	}

	public String imprimeServico(){
		String out="";
		if(servicos.tamanho() == 0) {
			out = "n�o h� servicos cadastradas,\n";
		}
		else {
			String[] p = servicos.toList();
			for(int i = 0; i < servicos.tamanho(); i++) {
				out += (p[i] + ",\n");
			}
		}
		return out;
	}

	@Override
	public String toString() {
		return "OrdemDeServico: \nmotivo= " + motivo 
				+ ", \ntipo= " + tipo.getTipoName()
				+ ", \nstatus= " + status.getStatusName()
				+ ", \ndataAbertura= " +  dataAbertura.get(Calendar.DAY_OF_MONTH)+"/"+ dataAbertura.get(Calendar.MONTH) +"/"+ dataAbertura.get(Calendar.YEAR) 
				+ ",\ndataFechamento= " + (dataFechamento == null ? "n�o definida" :(dataFechamento.get(Calendar.DAY_OF_MONTH) +"/"+ dataFechamento.get(Calendar.MONTH) +"/"+ dataFechamento.get(Calendar.YEAR)))
				+ ", \nprecoPecas= " + getPrecoPecas()+ ", \nprecoMaoDeObra= " + getPrecoMaoDeObra() + ", \nprecoTotal= " + getPrecoTotal()
				+ ", \npecas= " + (pecas == null  ? "n�o h� pecas cadastradas,\n" : imprimePecas())
				+ "servicos= " + (servicos == null ? "n�o h� servicos cadastrados" : imprimeServico() );
	}



}
