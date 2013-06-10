package core.produto;

import java.io.Serializable;
import java.math.BigDecimal;

public class Produto implements Serializable, Comparable<Produto>{

	private static final long serialVersionUID = 2L;
	private String nome;
	private Integer quantidadeNecessaria;
	private BigDecimal precoVenda;

	public Produto(String nome, Integer quantidadeNecessaria, BigDecimal precoVenda) {
		super();
		this.nome = nome;
		this.quantidadeNecessaria = quantidadeNecessaria;
		this.precoVenda = precoVenda;
	}
		
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

	public int compareTo(Produto outroProduto) {
		return getNome().compareTo(outroProduto.getNome());
	}
	
	@Override
	public boolean equals(Object obj) {
		Produto produto = (Produto)obj;
		return getNome().equals(produto.getNome());
	}
	
	@Override
	public int hashCode() {
		return getNome().hashCode();
	}
	
	@Override
	public String toString() {
		return getNome();
	}
	
}
