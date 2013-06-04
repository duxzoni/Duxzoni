package core.produto;

import java.math.BigDecimal;

public class Produto {

	private String nome;
	private Integer quantidadeNecessaria;
	private BigDecimal precoVenda;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getQuantidadeNecessaria() {
		return quantidadeNecessaria;
	}
	public void setQuantidadeNecessaria(Integer quantidadeNecessaria) {
		this.quantidadeNecessaria = quantidadeNecessaria;
	}
	public BigDecimal getPrecoVenda() {
		return precoVenda;
	}
	public void setPrecoVenda(BigDecimal precoVenda) {
		this.precoVenda = precoVenda;
	}
	
}
