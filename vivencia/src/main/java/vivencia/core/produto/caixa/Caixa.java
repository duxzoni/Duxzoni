package vivencia.core.produto.caixa;

import java.io.Serializable;
import java.math.BigDecimal;

import vivencia.core.produto.Estoque;
import vivencia.core.produto.lista.Item;
import vivencia.core.produto.lista.ListaDeCompras;

public class Caixa implements Serializable{

	private static final long serialVersionUID = 13L;
	private BigDecimal valor;
	private static Caixa instancia;
	
	private Caixa() {
		valor = new BigDecimal(0);
	}
	
	public static Caixa getInstance() {
		instancia = instancia == null? new Caixa(): instancia;
		
		return instancia;
	}
	
	public void  atualizaValorEmCaixa(Estoque estoque, ListaDeCompras listaDeCompras){
		BigDecimal somaLista = new BigDecimal(0);
		
		for (Item item : listaDeCompras.itens())
			somaLista = somaLista.add(item.getProduto().getPrecoVenda().multiply(new BigDecimal(item.getQuantidade())));
		
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

	public void setValor(BigDecimal valor) {
		this.valor = valor;
		
	}
	
}
