package classes;

import java.util.Scanner;

public class Veiculo {

	private String modelo;
	private String placa;
	private double quilometragem;
	private List<OrdemDeServico> historico;


	//Construtores
	public Veiculo() {}

	public Veiculo(Veiculo v) {
		this.modelo = v.modelo;
		this.placa = v.placa;
		this.quilometragem = v.quilometragem;
	}

	public Veiculo(String modelo, String placa, double quilometragem) {
		this.modelo = modelo;
		this.placa = placa;
		this.quilometragem = quilometragem;
	}


	//Getters
	public String getModelo() {
		return modelo;
	}
	public String getPlaca() {
		return placa;
	}
	public double getQuilometragem() {
		return quilometragem;
	}
	public List<OrdemDeServico> getHistorico() {
		return historico;
	}
	public OrdemDeServico getOrdemDeServicoX(int index) {
		return historico.get(index);
	}


	//Setters
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public void setQuilometragem(double quilometragem) {
		this.quilometragem = quilometragem;
	}
	public void setHistorico(List<OrdemDeServico> historico) {
		this.historico = historico;
	}
	public void addOrdem(OrdemDeServico o) {
		this.historico.add(o);
	}


	//Menu
	public void menuVeiculo() {
		int opcao;
		Scanner entrada = new Scanner( System.in );
		do {
			System.out.println("============================== MENU VEICULO =============================\n");
			System.out.println("1: Editar o modelo deste veículo\n");
			System.out.println("2: Editar a placa deste veículo\n");
			System.out.println("3: Editar a quilometragem deste veículo\n");
			System.out.println("4: Imprimir informações sobre este veículo\n\n");
			System.out.println("0: Sair deste Menu\n");

			do {
				System.out.println("Digite qual opção voce deseja: ");
				opcao =  entrada.nextInt();
				entrada.nextLine();
			}while(opcao < 0 || opcao > 4);

			String nome;
			double quilometragem;
			switch(opcao) {
			case 1:
				System.out.println("Digite o novo modelo deste veículo:");
				nome = entrada.nextLine();
				setModelo(nome);
				break;

			case 2:
				System.out.println("Digite a nova placa deste veículo ");
				nome = entrada.nextLine();
				setPlaca(nome);
				break;

			case 3:
				System.out.println("Digite a nova quilometragem deste veículo ");
				quilometragem = entrada.nextDouble();
				setQuilometragem(quilometragem);
				break;

			case 4:
				System.out.println(toString());
				break;

			
			default:
				break;	
			}
		}while(opcao != 0);

	}

	//Criar veículo
	public static Veiculo criarVeiculo() {
		String nome;
		double quilometragem;
		Veiculo v = new Veiculo();
		Scanner entrada = new Scanner( System.in );

		System.out.println("Digite o modelo deste veículo:");
		nome = entrada.nextLine();
		v.setModelo(nome);


		System.out.println("Digite a placa deste veículo ");
		nome = entrada.nextLine();
		v.setPlaca(nome);


		System.out.println("Digite a quilometragem deste veículo ");
		quilometragem = entrada.nextDouble();
		v.setQuilometragem(quilometragem);	
		
		return v;
	}


	//Impressão
	public String toString() {
		return "Veiculo [modelo=" + modelo + ", placa=" + placa + ", quilometragem=" + quilometragem + "]";
	}


}
