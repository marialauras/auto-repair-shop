package classes;

public enum Status{
	APROVADO(1, "aprovado"),
	PENDENTE(2, "pendente"),
	EXECUTADO(3, "executado"),
	CONCLUIDO(4, "concluido");
	
	private final int opcao;
	private final String nome;
	
	//Construtor
	Status( int opcao, String nome ){
		this.opcao = opcao;
		this.nome = nome;
	}
	//Getters
	public String getStatusName() {
		return this.nome;
	}
	public int getStatusNum() {
		return this.opcao;
	}
}
