package classes;

public enum Tipo {
	ORCAMENTO(1, "orcamento"),
	MANUTENCAO(2, "manutencao");

	private final int opcao;
	private final String nome;
	
	//Construtor
	Tipo( int opcao, String nome ){
		this.opcao = opcao;
		this.nome = nome;
	}
	//Getters
	public String getTipoName() {
		return this.nome;
	}
	public int getTipoNum() {
		return this.opcao;
	}

}
