package core.produto;

public enum TipoProduto {
	CERVEJA("Cerveja"),
	CHOCOLATE("Chocolate"),
	ENERGETICO("Energético"),
	IOGURTE("Iogurte"),
	REFRIGERANTE("Refrigerante"),
	SALGADINHO("Salgadinho"),
	OUTROS("Outros");
	

	private final String nome;
	
	TipoProduto(String nome){
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}
	
	@Override
	public String toString() {
		return getNome();
	}
}
