package core.produto.caixa;

import java.math.BigDecimal;

import core.produto.Estoque;
import core.produto.lista.Item;
import core.produto.lista.ListaDeCompras;

public class Caixa {

	private BigDecimal valor;
	private static Caixa instancia;
	
	private Caixa() {
		valor = new BigDecimal(0);
	}
	
	public static Caixa getInstance() {
		instancia = instancia == null? new Caixa(): instancia;
		
		return instancia;
	}
	
	public void  atualizaValorEmCaixa(Estoque estoque){
		ListaDeCompras listaDeCompras = estoque.geraListaDeCompras();
		BigDecimal somaLista = new BigDecimal(0);
		
		for (Item item : listaDeCompras.itens())
			somaLista.add(item.getProduto().getPrecoVenda().multiply(new BigDecimal(item.getQuantidade())));
		
		valor.add(somaLista);
	}
	
	public BigDecimal getValor() {
		return valor;
	}
	
	public Caixa copy() {
		Caixa copy = new Caixa();
		copy.valor.add(this.valor);
		return copy;
	}
	
}
