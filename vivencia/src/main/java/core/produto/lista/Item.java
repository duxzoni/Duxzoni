package core.produto.lista;

import java.math.BigDecimal;

import core.produto.Produto;

public class Item implements Comparable<Item>{

	private Produto produto;
	private int quantidade;
	private BigDecimal ultimoValorPago;

	public Item(Produto produto, int quantidade) {
		super();
		this.produto = produto;
		this.quantidade = quantidade;
//		ultimoValorPago = buscaUltimoValorPago();
	}

	private BigDecimal buscaUltimoValorPago() {
		throw new RuntimeException();
//		return null;
	}

	public Produto getProduto() {
		return produto;
	}
	
	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getUltimoValorPago() {
		return ultimoValorPago;
	}

	public void setUltimoValorPago(BigDecimal ultimoValorPago) {
		this.ultimoValorPago = ultimoValorPago;
	}
	
	public int compareTo(Item OutroItem) {
		return produto.compareTo(OutroItem.getProduto());
	}
	
}
